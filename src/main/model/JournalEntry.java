package model;

// Represents a single journal entry that has a book title and content
public class JournalEntry {
    private String content;
    private int rating;

    public JournalEntry(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

    public String getContent() {
        return this.content;
    }

    public int getRating() {
        return this.rating;
    }


}


