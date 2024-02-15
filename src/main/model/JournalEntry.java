package model;

// Represents a single journal entry that has a book title, content, ratings, and quotes
public class JournalEntry {
    private String bookTitle;
    private String content;
    private String quote;

    public JournalEntry(String bookTitle, String content, String quote) {
        this.bookTitle =  bookTitle;
        this.content = content;
        this.quote = quote;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getContent() {
        return content;
    }

    public String getQuote() {
        return quote;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

}


