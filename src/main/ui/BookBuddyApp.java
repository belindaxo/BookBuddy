package ui;

import model.*;

import java.util.Scanner;

// BookBuddy application
public class BookBuddyApp {
    private final VirtualBookshelf bookshelf;
    private final ReadingJournal journal;
    private final ReadingTracker tracker;
    private final RecommendationSystem rec;
    private final Scanner input;

    // EFFECTS: Initializes the BookBuddyApp instance with an empty bookshelf, journal,
    //          tracker, recommendation system, and initializes input scanner
    public BookBuddyApp() {
        bookshelf = new VirtualBookshelf();
        journal = new ReadingJournal();
        tracker = new ReadingTracker();
        rec = new RecommendationSystem(bookshelf);
        input = new Scanner(System.in);
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
                + "\n1. Access/modify your virtual bookshelf"
                + "\n2. Open reading tracker"
                + "\n3. Add a journal entry"
                + "\n4. Get a book recommendation");
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
                addJournalEntry();
                break;
            case 4:
                getRecommendation();
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
                + "\n1. Add a book" + "\n2. View bookshelf");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            addBook();
        } else if (choice == 2) {
            viewBookshelf();
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
        if (choice == 1) {
            addGoal();
        } else if (choice == 2) {
            logPages();
        } else if (choice == 3) {
            viewGoalSummary();
        }
    }

    // REQUIRES: valid String inputs
    // MODIFIES: this.journal
    // EFFECTS: adds journal entry for user-inputted book, content, and quote
    public void addJournalEntry() {
        System.out.println("What book would you like to create an entry for?");
        String title = input.nextLine();
        System.out.println("Write your notes below:");
        String content = input.nextLine();
        System.out.println("Write down a memorable quote below:");
        String quote = input.nextLine();
        journal.addEntry(new JournalEntry(title, content, quote));
        System.out.println("Your entry for " + title + " has been added to your journal.");
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
