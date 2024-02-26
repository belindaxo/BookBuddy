package model;

// Represents a single journal entry that has a book title, content, ratings, and quotes
public class JournalEntry {
    private Book book;
    private String content;

    public JournalEntry(Book book, String content) {
        this.book =  book;
        this.content = content;
    }

    public Book getBook() {
        return this.book;
    }

    public String getBookTitle() {
        return this.book.getTitle();
    }

    public String getContent() {
        return content;
    }


}


