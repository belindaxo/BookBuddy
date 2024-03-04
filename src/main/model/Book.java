package model;

import org.json.JSONObject;
import persistence.Writeable;

// Represents a book in a user's collection that has a title, author, genre, page count, and read status
public class Book implements Writeable {
    private String title;
    private String author;
    private String genre;
    private int pageCount;
    private String status; // read, unread, in progress
    private JournalEntry entry;


    // EFFECTS: constructs book with given title, author, genre, page count, and initial status set to "unread"
    public Book(String title, String author, String genre, int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
        this.status = "unread";
    }

    // getters and setters:
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public String getStatus() {
        return status;
    }

    // MODIFIES: this
    // EFFECTS: sets status of book to "read"
    public void setStatusRead() {
        this.status = "read";
    }

    // MODIFIES: this
    // EFFECTS: sets status of book to "in progress"
    public void setStatusInProgress() {
        this.status = "in progress";
    }

    // MODIFIES: this
    // EFFECTS: sets status of book to "read"
    public void setStatusUnread() {
        this.status = "unread";
    }

    public void addJournalEntry(String content, int rating) {
        this.entry = new JournalEntry(content, rating);
    }

    // EFFECTS: returns string representation of a book
    public String toString() {
        return title + " by " + author + " (" + genre + "), " + pageCount + " pages, " + "status: " + status;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("page count", pageCount);
        json.put("status", status);
        return json;
    }


}

