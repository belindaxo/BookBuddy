package ui;

import java.io.FileNotFoundException;

// Main class for BookBuddy application
public class Main {
    // Initializes and runs instance of BookBuddy app
    public static void main(String[] args) {
        try {
            BookBuddyApp bookBuddy = new BookBuddyApp();
            bookBuddy.runBookBuddy();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot run application.");
        }
    }
}
