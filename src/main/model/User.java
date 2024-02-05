package model;

import java.util.ArrayList;

import java.util.Map;

// Represents a user if
public class User {
    private String username;
    private String password;
    private ArrayList<Book> bookshelf;
    private ReadingTracker readingTracker;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //TODO
    // methods for managing bookshelf, journal, and tracker

}
