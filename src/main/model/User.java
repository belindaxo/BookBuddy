package model;

// Represents a user of the application
import java.util.ArrayList;


public class User {
    private ArrayList<Book> bookshelf;

    public User(ArrayList<Book> bookshelf) {
        this.bookshelf = new ArrayList<>();
    }

    //TODO
    // methods for managing bookshelf, journal, and tracker

    // MODIFIES: this
    // EFFECTS: adds a new book to bookshelf
    public void addBook(Book book) {
        this.bookshelf.add(book);
    }




}
