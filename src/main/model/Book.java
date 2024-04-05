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
    private Rating rating;


    // EFFECTS: constructs book with given title, author, genre, page count, and initial status set to "unread"
    public Book(String title, String author, String genre, int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
        this.status = "unread";
        this.entry = new JournalEntry();
        this.rating = Rating.UNRATED;
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
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
        EventLog.getInstance().logEvent(new Event("Status has been updated."));
    }

    public JournalEntry getEntry() {
        return this.entry;
    }

    public void setEntry(JournalEntry entry) {
        this.entry = entry;
    }

    public void addContent(String content) {
        this.entry.setContent(content);
    }

    public boolean isEntryEmpty() {
        return this.entry.getContent().isEmpty();
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
        EventLog.getInstance().logEvent(new Event("Rating has been updated."));
    }

    // EFFECTS: returns string representation of a book
    public String toString() {
        return this.title + " by " + this.author
                + "\nGenre: " + this.genre
                + "\nPage count: " + this.pageCount
                + "\nStatus: " + this.status
                + "\nRating: " + this.rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("pageCount", this.pageCount);
        json.put("status", status);
        json.put("rating", rating.name());
        json.put("entry", entry.toJson());
        return json;
    }


}

