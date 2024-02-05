package model;

// Represents a book in a user's collection that has a title, author, genre,
// publication year, and page count.
public class Book {
    private String title;
    private String author;
    private String genre;
    private int pageCount;

    // Constructor
    // EFFECTS: constructs book with given title, author, genre, publication year, and page count
    public Book(String title, String author, String genre, int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
    }

    // setters:
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    // getters:
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPageCount() {
        return pageCount;
    }

}

