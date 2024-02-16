package ui;

import model.*;

import java.util.Scanner;

public class BookBuddyApp {
    private VirtualBookshelf bookshelf;
    private ReadingJournal journal;
    private ReadingTracker tracker;
    private RecommendationSystem rec;
    private Scanner input;

    public BookBuddyApp() {
        bookshelf = new VirtualBookshelf();
        journal = new ReadingJournal();
        tracker = new ReadingTracker();
        rec = new RecommendationSystem(bookshelf);
        input = new Scanner(System.in);
    }

    public void runBookBuddy() {
        boolean run = true;
        while (run) {
            mainMenu();
            int option = input.nextInt();
            input.nextLine();
            if (option == 1) {
                addBook();
            } else if (option == 2) {
                viewBookshelf();
            } else if (option == 3) {
                addGoal();
            } else if (option == 4) {
                logPages();
            } else if (option == 5) {
                addJournalEntry();
            } else if (option == 6) {
                getRecommendation();
            } else {
                System.out.println("Invalid input. Please try again.");
                runBookBuddy();
            }
        }
    }

    public void mainMenu() {
        System.out.println("Welcome to BookBuddy! What would you like to do?"
                + "\n1. Add a book to your bookshelf"
                + "\n2. View your bookshelf"
                + "\n3. Create a reading goal"
                + "\n4. Log reading activity"
                + "\n5. Add a journal entry"
                + "\n6. Get a book recommendation");
    }

    public void nextAction() {
        System.out.println("1. Return to main menu \n2. close application");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            runBookBuddy();
        } else {
            System.out.println("Thank you for using BookBuddy! Have a nice day.");
        }
    }

    public void addBook() {
        System.out.println("What is the title?");
        String title = input.nextLine();
        System.out.println("Who is the author?");
        String author = input.nextLine();
        System.out.println("What is the genre?");
        String genre = input.nextLine();
        System.out.println("What is the page count?");
        int pc = input.nextInt();
        bookshelf.addBook(new Book(title, author, genre, pc));
        System.out.println(title + " by " + author + " has been added to your bookshelf.");
        System.out.println("Would you like to add another book? \n1. yes \n2. no");
        int choice = input.nextInt();
        input.nextLine();
        if (choice == 1) {
            addBook();
        } else {
            nextAction();
        }
    }

    public void viewBookshelf() {
        System.out.println(bookshelf.getBookTitles());
        nextAction();
    }

    public void logPages() {
        System.out.println("How many pages have you read?");
        int pages = input.nextInt();
        tracker.addPagesRead(pages);
        System.out.println(pages + " pages have been added. "
                + "\nWould you like to view your current goal progress? \n1. yes \n2. no");
        int choice = input.nextInt();
        if (choice == 1) {
            viewGoalSummary();
        } else {
            nextAction();
        }
    }

    public void viewGoalSummary() {
        System.out.println(tracker.goalSummary());
        nextAction();
    }

    public void addGoal() {
        System.out.println("How many pages would you like read?");
        int goal = input.nextInt();
        tracker.setReadingGoal(goal);
        System.out.println("A new reading goal of " + goal + " pages has been set.");
        nextAction();
    }

    public void addJournalEntry() {
        System.out.println("What book would you like to create an entry for?");
        String title = input.nextLine();
        System.out.println("Write your notes below:");
        String content = input.nextLine();
        System.out.println("Write down a memorable quote below:");
        String quote = input.nextLine();
        journal.addEntry(new JournalEntry(title, content, quote));
        System.out.println("Your entry for " + title + " has been added to your journal.");
        nextAction();
    }

    public void getRecommendation() {
        System.out.println("Get recommendation based on: \n1. Genre \n2. Book length \n3. Author \n4. Surprise me!");
        int choice = input.nextInt();
        if (choice == 1) {
            getRecByGenre();
        } else if (choice == 2) {
            getRecByPageCount();
        } else if (choice == 3) {
            getRecByAuthor();
        } else if (choice == 4) {
            getRandomRec();
        }
    }

    public void getRecByGenre() {
        System.out.println("What is your genre of choice?");
        String genre = input.nextLine();
        System.out.println("Generating a book with genre: " + genre + "...");
        Book book = rec.recBookByGenre(genre);
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
        System.out.println("Would you like to generate another recommendation? \n1. yes \n2. no");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            getRecommendation();
        } else {
            nextAction();
        }
    }

    public void getRecByPageCount() {
        System.out.println("Would you like a short, medium, or long read?");
        String length = input.nextLine();
        System.out.println("Generating a " + length + "-length book...");
        Book book = rec.recBookByPageCount(length);
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
        System.out.println("Would you like to generate another recommendation? \n1. yes \n2. no");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            getRecommendation();
        } else {
            nextAction();
        }
    }

    public void getRecByAuthor() {
        System.out.println("Which author would you like to read?");
        String author = input.nextLine();
        System.out.println("Generating a book by " + author + "...");
        Book book = rec.recBookByPageCount(author);
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
        System.out.println("Would you like to generate another recommendation? \n1. yes \n2. no");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            getRecommendation();
        } else {
            nextAction();
        }
    }

    public void getRandomRec() {
        System.out.println("Generating a surprise book from your collection...");
        Book book = rec.recRandomBook();
        System.out.println("Recommendation: " + book.getTitle() + " by " + book.getAuthor());
        System.out.println("Would you like to generate another recommendation? \n1. yes \n2. no");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            getRecommendation();
        } else {
            nextAction();
        }
    }
}
