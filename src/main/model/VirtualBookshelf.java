package model;

import java.util.ArrayList;

// Represents a users collection of books added to their bookshelf
public class VirtualBookshelf {
    private ArrayList<Book> books;

    public VirtualBookshelf() {
        this.books = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds book to bookshelf
    public void addBook(Book book) {
        books.add(book);
    }
    //TODO
    // if list contains book (all fields identical), don't add book, otherwise add


    // EFFECTS: returns the number of books in bookshelf that are read
    public int readCount() {
        int count = 0;
        for (Book book : books) {
            if (book.getStatus().equals("read")) {
                count++;
            }
        }
        return count;
    }

    //TODO
    // readBooksList, inProgressBooksList, unreadBooksList


    // EFFECTS: returns the number of books in bookshelf that are in progress
    public int inProgressCount() {
        int count = 0;
        for (Book book: books) {
            if (book.getStatus().equals("in progress")) {
                count++;
            }
        }
        return count;
    }


    // EFFECTS: returns the number of books in bookshelf that are unread
    public int unreadCount() {
        int count = 0;
        for (Book book: books) {
            if (book.getStatus().equals("unread")) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: returns list of all books in bookshelf.
    public ArrayList<Book> getBooks() {
        return books;
    }

    // EFFECTS: returns a list of books in bookshelf that match specified genre
    public ArrayList<Book> getBooksByGenre(String g) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (g.equalsIgnoreCase(b.getGenre())) {
                temp.add(b);
            }
        }
        return temp;
    }

    // REQUIRES: pc = "short" OR "medium" OR "long"
    // EFFECTS: returns a list of books in bookshelf that match specified length
    public ArrayList<Book> getBooksByPageCount(String pc) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (pc.equalsIgnoreCase("short") && (b.getPageCount() <= 350)) {
                temp.add(b);
            } else if (pc.equalsIgnoreCase("medium")
                    && (b.getPageCount() > 350)
                    && (b.getPageCount() <= 650)) {
                temp.add(b);
            } else if (pc.equalsIgnoreCase("long") && (b.getPageCount() > 650)) {
                temp.add(b);
            }
        }
        return temp;
    }

    // EFFECTS: returns a list of books in bookshelf that match specified author
    public ArrayList<Book> getBooksByAuthor(String a) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if ((b.getAuthor().toLowerCase()).contains(a.toLowerCase())) {
                temp.add(b);
            }
        }
        return temp;
    }

}




