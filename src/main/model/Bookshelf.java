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
    //TODO
    // if list contains book (all fields identical), don't add book, otherwise add

    // REQUIRES: non-empty ArrayList<Book>
    // EFFECTS: counts the number of books in bookshelf that are read
    public int readCount() {
        int count = 0;
        for (Book book : bookshelf) {
            if (book.getStatus().equals("read")) {
                count++;
            }
        }
        return count;
    }

    //TODO
    // readBooksList, inProgressBooksList, unreadBooksList

    // REQUIRES: non-empty ArrayList<Book>
    // EFFECTS: counts the number of books in bookshelf that are in progress
    public int inProgressCount() {
        int count = 0;
        for (Book book: bookshelf) {
            if (book.getStatus().equals("in progress")) {
                count++;
            }
        }
        return count;
    }

    // REQUIRES: non-empty ArrayList<Book>
    // EFFECTS: counts the number of books in bookshelf that are unread
    public int unreadCount() {
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

    public ArrayList<Book> getBooksByGenre(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : bookshelf) {
            if (pref.equalsIgnoreCase(b.getGenre())) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Book> getBooksByPageCount(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : bookshelf) {
            if (pref.equalsIgnoreCase("short") && (b.getPageCount() <= 350)) {
                temp.add(b);
            } else if (pref.equalsIgnoreCase("medium") && (b.getPageCount() > 350) && (b.getPageCount() <= 650)) {
                temp.add(b);
            } else if (pref.equalsIgnoreCase("long") && (b.getPageCount() > 650)) {
                temp.add(b);
            }
        }
        return temp;
    }

    public ArrayList<Book> getBooksByAuthor(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : bookshelf) {
            if ((b.getAuthor().toLowerCase()).contains(pref.toLowerCase())) {
                temp.add(b);
            }
        }
        return temp;
    }

}




