package model;

import java.util.ArrayList;
import java.util.Random;

// Suggests books using inputted user preferences for genre, length, and read-status
public class RecommendationSystem {
    private VirtualBookshelf bookshelf;

    // EFFECTS: initializes a recommendation system with a bookshelf
    public RecommendationSystem(VirtualBookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    // EFFECTS: returns random book from bookshelf that matches specified genre preference,
    //          if no books match preference, return null
    public Book recBookByGenre(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getGenre().equalsIgnoreCase(pref)) {
                temp.add(book);
            }
        }
        if (temp.size() == 0) {
            return null;
        }
        Random random = new Random();
        return temp.get(random.nextInt(temp.size()));
    }

    // EFFECTS: returns random book from bookshelf that matches specified length preference,
    //          if no books match preference, return null
    public Book recBookByPageCount(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getPageCount() <= 350
                    && pref.equalsIgnoreCase("short")) {
                temp.add(book);
            } else if (book.getPageCount() > 350
                    && book.getPageCount() <= 650
                    && pref.equalsIgnoreCase("medium")) {
                temp.add(book);
            } else if (book.getPageCount() > 650
                    && pref.equalsIgnoreCase("long")) {
                temp.add(book);
            }
        }

        if (temp.size() == 0) {
            return null;
        }

        Random random = new Random();
        return temp.get(random.nextInt(temp.size()));
    }

    // EFFECTS: returns random book from bookshelf that matches specified status preference,
    //          if no books match preference, return null
    public Book recBookByStatus(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getStatus().equalsIgnoreCase(pref)) {
                temp.add(book);
            }
        }
        if (temp.size() == 0) {
            return null;
        }
        Random random = new Random();
        return temp.get(random.nextInt(temp.size()));
    }

    // EFFECTS: returns random book from the whole bookshelf
    public Book recRandomBook() {
        Random random = new Random();
        return bookshelf.getBooks().get(random.nextInt(bookshelf.getBooks().size()));
    }
}
