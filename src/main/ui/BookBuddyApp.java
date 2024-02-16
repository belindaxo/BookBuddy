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
            System.out.println("Welcome to BookBuddy! What would you like to do?"
                            + "\n1. Add a book to your bookshelf"
                            + "\n2. View your bookshelf"
                            + "\n3. Log reading activity"
                            + "\n4. Add a journal entry"
                            + "\n5. Get a book recommendation");
            int option = input.nextInt();
            input.nextLine();
            if (option == 1) {
                addBook();
                break;
            } else if (option == 2) {
                viewBookshelf();
            } else if (option == 3) {
                logPages();
                break;
            }
            run = false;
            System.out.println("Thank you for using BookBuddy. Have a nice day!");
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
        input.nextLine();
    }

    public void viewBookshelf() {
        System.out.println(bookshelf.getBooks().toString());
    }

    public void logPages() {
        System.out.println("How many pages have you read?");
        int pages = input.nextInt();
        tracker.addPagesRead(pages);
        System.out.println(pages + " pages have been added. "
                + "\nWould you like to view your current goal progress? (yes/no)");
        String choice = input.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println(tracker.goalSummary());
        } else {
            System.out.println("Returning to main menu.");
        }
        input.nextLine();

    }




}
