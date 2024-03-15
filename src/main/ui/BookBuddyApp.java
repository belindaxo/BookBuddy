package ui;

import model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

// BookBuddy application
public class BookBuddyApp {
    private VirtualBookshelf bookshelf;
    private ReadingTracker tracker;
    private final RecommendationSystem rec;
    private final Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/bookshelf.json";

    // EFFECTS: Initializes the BookBuddyApp instance with an empty bookshelf, journal,
    //          tracker, recommendation system, and initializes input scanner
    public BookBuddyApp() throws FileNotFoundException {
        bookshelf = new VirtualBookshelf();
        tracker = new ReadingTracker();
        rec = new RecommendationSystem(bookshelf);
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: runs main loop of application, displays main menu, and processes user input,
    //          until user chooses to exit, closes scanner upon exit
    public void runBookBuddy() {
        boolean run = true;
        while (run) {
            mainMenu();
            run = processUserInput();
        }
        input.close();
    }

    // EFFECTS: displays main menu options
    public void mainMenu() {
        System.out.println("Welcome to BookBuddy! What would you like to do?"
                + "\n1. Access your virtual bookshelf"
                + "\n2. Open reading tracker"
                + "\n3. Access your reading journal"
                + "\n4. Get a book recommendation"
                + "\n5. Save bookshelf"
                + "\n6. Load bookshelf");
    }

    // REQUIRES: integer input that matches one of the valid options
    // MODIFIES: this, bookshelf, journal, tracker, or rec depending on user input
    // EFFECTS: processes user main menu selection, returns true to continue loop, false to exit
    public boolean processUserInput() {
        int option = input.nextInt();
        input.nextLine();
        switch (option) {
            case 1:
                virtualBookshelf();
                break;
            case 2:
                readingTracker();
                break;
            case 3:
                readingJournal();
                break;
            case 4:
                getRecommendation();
                break;
            case 5:
                saveBookshelf();
                break;
            case 6:
                loadBookshelf();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return nextAction();
    }

    // REQUIRES: integer input matches valid options
    // EFFECTS: returns true if user chooses to return to main menu, false if they choose to close application
    public boolean nextAction() {
        System.out.println("1. Return to main menu \n2. close application");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            return true;
        } else {
            System.out.println("Thank you for using BookBuddy! Have a nice day.");
            return false;
        }
    }

    // REQUIRES: valid type inputs for book's title, author, genre, and page count
    // MODIFIES: this.bookshelf
    // EFFECTS: creates new book with inputted details to bookshelf and displays confirmation message
    public void addBook() {
        boolean continueAdding = true;
        while (continueAdding) {
            System.out.println("What is the title?");
            String title = input.nextLine();
            System.out.println("Who is the author?");
            String author = input.nextLine();
            System.out.println("What is the genre?");
            String genre = input.nextLine();
            System.out.println("What is the page count?");
            int pc = input.nextInt();
            input.nextLine();

            bookshelf.addBook(new Book(title, author, genre, pc));
            System.out.println(title + " by " + author + " has been added to your bookshelf.");

            continueAdding = addAnotherBook();
        }
    }

    public boolean addAnotherBook() {
        System.out.println("Would you like to add another book? \n1. yes \n2. no");
        int choice = input.nextInt();
        input.nextLine();
        return choice == 1;
    }

    // EFFECTS: prints a current list of book titles in bookshelf
    public void viewBookshelf() {
        System.out.println(bookshelf.getBookTitles());
    }

    // REQUIRES: valid integer input (1 or 2) for choice to view bookshelf or add book
    // MODIFIES: this, this.bookshelf if user chooses to add another book
    // EFFECTS: processes user's choice to either add a book or view bookshelf
    public void virtualBookshelf() {
        System.out.println("Welcome to your virtual bookshelf. What would you like to do?"
                + "\n1. Add a book" + "\n2. View bookshelf" + "\n3. Add a book rating" + "\n4. Update book status");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                viewBookshelf();
                break;
            case 3:
                rateBook();
                break;
            case 4:
                updateBookStatus();
                break;
            default:
                System.out.println("invalid option");
        }
    }


    private void loadBookshelf() {
        try {
            bookshelf = jsonReader.read();
            System.out.println("Loading bookshelf: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }

    }

    private void saveBookshelf() {
        try {
            jsonWriter.open();
            jsonWriter.write(bookshelf);
            jsonWriter.close();
            System.out.println("Saved bookshelf to: " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, unable to save:  " + JSON_STORE);
        }
    }

    public void updateBookStatus() {
        Book selectedBook = selectBookFromShelf();
        System.out.println("Current status: " + selectedBook.getStatus());
        System.out.println("Change status to: \n1. read \n2. in progress \n3. unread");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                selectedBook.setStatus("read");
                System.out.println(selectedBook.getTitle() + " has been marked as " + selectedBook.getStatus());
                break;
            case 2:
                selectedBook.setStatus("in progress");
                System.out.println(selectedBook.getTitle() + " has been marked as " + selectedBook.getStatus());
                break;
            case 3:
                selectedBook.setStatus("unread");
                System.out.println(selectedBook.getTitle() + " has been marked as " + selectedBook.getStatus());
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    // REQUIRES: valid integer input representing number of pages read
    // MODIFIES: this.tracker
    // EFFECTS: logs specified number of pages in the reading tracker, displays confirmation message
    public void logPages() {
        System.out.println("How many pages have you read?");
        int pages = input.nextInt();
        tracker.addPagesRead(pages);
        System.out.println(pages + " pages have been added.");
    }

    // EFFECTS: displays summary of progress for current reading goal
    public void viewGoalSummary() {
        System.out.println(tracker.goalSummary());
    }

    // REQUIRES: valid integer input of number of pages read
    // MODIFIES: this.tracker
    // EFFECTS: sets new reading goal of specified number of pages, displays confirmation message
    public void addGoal() {
        System.out.println("How many pages would you like read?");
        int goal = input.nextInt();
        tracker.setReadingGoal(goal);
        System.out.println("A new reading goal of " + goal + " pages has been set.");
    }

    // REQUIRES: valid integer input for choice numbers
    // MODIFIES: this, this.tracker if user chooses to set goal or log pages
    // EFFECTS: processes user input related to functions for the reading tracker
    public void readingTracker() {
        System.out.println("Welcome to the reading tracker! What would you like to do?"
                + "\n1. Set reading goal" + "\n2. Log reading activity" + "\n3. View goal summary");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                addGoal();
                break;
            case 2:
                logPages();
                break;
            case 3:
                viewGoalSummary();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void readingJournal() {
        System.out.println("Welcome to your reading journal! What would you like to do?"
                + "\n1. Add/edit entry" + "\n2. View journal");
        int choice = input.nextInt();
        input.nextLine();
        switch (choice) {
            case 1:
                updateJournalEntry();
                break;
            case 2:
                viewReadingJournal();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    public void viewReadingJournal() {
        List<JournalEntry> allEntries = bookshelf.getAllEntries();
        if (allEntries.isEmpty()) {
            System.out.println("Your journal is empty.");
        } else {
            for (Book b : bookshelf.getBooks()) {
                JournalEntry entry = b.getEntry();
                System.out.println("Book: " + b.getTitle());
                System.out.println("Content: " + entry.getContent());
            }
        }
    }

    // REQUIRES: valid integer inputs
    // MODIFIES: this.journal
    // EFFECTS: adds journal entry for selected book and written content
    public void updateJournalEntry() {
        Book selectedBook = selectBookFromShelf();
        if (selectedBook != null) {
            System.out.println("Enter your notes:");
            String content = input.nextLine();
            selectedBook.addContent(content);
            System.out.println("Journal entry for " + selectedBook.getTitle() + " has been updated.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void rateBook() {
        Book selectedBook = selectBookFromShelf();
        if (selectedBook != null) {
            System.out.println("Enter rating from 1 to 5:");
            int rating = input.nextInt();
            input.nextLine();
            setBookRating(selectedBook, rating);
        } else {
            System.out.println("Book not found.");
        }
    }

    public void setBookRating(Book book, int rating) {
        Rating bookRating;
        switch (rating) {
            case 1:
                bookRating = Rating.ONE_STAR;
                break;
            case 2:
                bookRating = Rating.TWO_STARS;
                break;
            case 3:
                bookRating = Rating.THREE_STARS;
                break;
            case 4:
                bookRating = Rating.FOUR_STARS;
                break;
            case 5:
                bookRating = Rating.FIVE_STARS;
                break;
            default:
                bookRating = Rating.UNRATED;
        }
        book.setRating(bookRating);
        System.out.println("Rating updated for " + book.getTitle() + ": " + book.getRating());
    }

    public Book selectBookFromShelf() {
        List<Book> books = bookshelf.getBooks();
        if (books.isEmpty()) {
            System.out.println("Your bookshelf is currently empty.");
            return null;
        }

        System.out.println("Select a book:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ": " + books.get(i).getTitle());
        }

        int selection = input.nextInt();
        input.nextLine();

        return books.get(selection - 1);
    }

    // EFFECTS: processes user's choice for rec type,
    //          continues until user does not want to receive further recs
    public void getRecommendation() {
        boolean continueRec = true;
        while (continueRec) {
            System.out.println("Get recommendation based on: "
                    + "\n1. Genre \n2. Book length \n3. Surprise me!");
            int choice = input.nextInt();
            input.nextLine();
            continueRec = processUserInputRec(choice);
        }
    }

    // REQUIRES: valid integer input for choices
    // EFFECTS: processes user's rec based on preference choice of genre, page count, or random,
    //          returns result of askForAnotherRec to see if user wants to get another rec
    public boolean processUserInputRec(int choice) {
        switch (choice) {
            case 1:
                getRecByGenre();
                break;
            case 2:
                getRecByPageCount();
                break;
            case 3:
                getRandomRec();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
        return askForAnotherRec();
    }

    // REQUIRES: valid integer input based on choices
    // EFFECTS: returns true if user wants another recommendation, false otherwise
    private boolean askForAnotherRec() {
        System.out.println("Would you like to generate another recommendation? \n1. yes \n2. no");
        int choice = input.nextInt();
        input.nextLine();
        return choice == 1;
    }

    // REQUIRES: valid String input for user's preferred genre
    // EFFECTS: processes user input of genre preference, returns book recommendation, prints confirmation message
    public void getRecByGenre() {
        System.out.println("What is your genre of choice?");
        String genre = input.nextLine();
        System.out.println("Generating a book with genre: " + genre + "...");
        Book book = rec.recBookByGenre(genre);
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
    }

    // REQUIRES: valid String input for length preference ("long" OR "medium" OR "short")
    // EFFECTS: processes choice of book length preference, returns book recommendation, displays confirmation message
    public void getRecByPageCount() {
        System.out.println("Would you like a short, medium, or long read?");
        String length = input.nextLine();
        System.out.println("Generating a " + length + "-length book...");
        Book book = rec.recBookByPageCount(length);
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
    }

    // EFFECTS: returns random book recommendation, displays confirmation message
    public void getRandomRec() {
        System.out.println("Generating a surprise book from your collection...");
        Book book = rec.recRandomBook();
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
    }
}
