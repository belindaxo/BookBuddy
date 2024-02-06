package model;

import java.util.ArrayList;

// Represents a users collection of books added to their bookshelf
public class Bookshelf {
    private ArrayList<Book> bookshelf;

    public Bookshelf() {
        bookshelf = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds book to bookshelf
    public void addBook(Book book) {
        this.bookshelf.add(book);
    }

    // REQUIRES: non-empty ArrayList<Book>
    // MODIFIES: this
    // EFFECTS: counts the number of books in bookshelf that are read
    public int readBooksCount() {
        int count = 0;
        for (Book book : bookshelf) {
            if (book.getStatus().equals("read")) {
                count++;
            }
        }
        return count;
    }

    // REQUIRES: non-empty ArrayList<Book>
    // MODIFIES: this
    // EFFECTS: counts the number of books in bookshelf that are in progress
    public int inProgressBooksCount() {
        int count = 0;
        for (Book book: bookshelf) {
            if (book.getStatus().equals("in progress")) {
                count++;
            }
        }
        return count;
    }

    // REQUIRES: non-empty ArrayList<Book>
    // MODIFIES: this
    // EFFECTS: counts the number of books in bookshelf that are unread
    public int unreadBooksCount() {
        int count = 0;
        for (Book book: bookshelf) {
            if (book.getStatus().equals("unread")) {
                count++;
            }
        }
        return count;
    }


    public ArrayList<Book> getBookshelf() {
        return bookshelf;
    }
}
