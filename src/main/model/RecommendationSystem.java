package model;

import java.util.ArrayList;
import java.util.Random;

// Suggests books using inputted user preferences for genre, length, and read-status
public class RecommendationSystem {
    private VirtualBookshelf bookshelf;
    private Book rec;

    public RecommendationSystem(VirtualBookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    //TODO
    // recommendBook method that recommends based off all three/a combo of some kind

    // MODIFIES: this
    // EFFECTS: returns title of random book from list of books that match specified preference and are unread
    public String recBookByGenre(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getGenre().equalsIgnoreCase(pref) && book.getStatus().equalsIgnoreCase("unread")) {
                temp.add(book);
            }
        }

        // if no books match preference, return error message
        if (temp.size() == 0) {
            return "No books found matching that genre, please try again.";
        }

        // randomly select one of the matching books in the temp list
        Random random = new Random();
        this.rec = temp.get(random.nextInt(temp.size()));

        return rec.getTitle();
    }

    // MODIFIES: this
    // EFFECTS: returns title of random book from list of books that match specified preference and are unread
    public String recBookByPageCount(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getPageCount() <= 350
                    && pref.equalsIgnoreCase("short")
                    && book.getStatus().equalsIgnoreCase("unread")) {
                temp.add(book);
            } else if (book.getPageCount() > 350
                    && book.getPageCount() <= 650
                    && pref.equalsIgnoreCase("medium")
                    && book.getStatus().equalsIgnoreCase("unread")) {
                temp.add(book);
            } else if (book.getPageCount() > 650
                    && pref.equalsIgnoreCase("long")
                    && book.getStatus().equalsIgnoreCase("unread")) {
                temp.add(book);
            }
        }

        // if no books match preference, return error message
        if (temp.size() == 0) {
            return "No books found with that length, please try again.";
        }

        // randomly select one of the matching books in the temp list
        Random random = new Random();
        this.rec = temp.get(random.nextInt(temp.size()));

        return rec.getTitle();
    }

    // MODIFIES: this
    // EFFECTS: returns title of random book from list of books that match specified preference and are unread
    public String recBookByStatus(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getStatus().equalsIgnoreCase(pref)) {
                temp.add(book);
            }
        }

        // if no books match preference, return error message
        if (temp.size() == 0) {
            return "No books found matching that status, please try again.";
        }

        // randomly select one of the matching books in the temp list
        Random random = new Random();
        this.rec = temp.get(random.nextInt(temp.size()));

        return rec.getTitle();
    }

    // MODIFIES: this
    // EFFECTS: returns title of random book from the whole bookshelf
    public String recRandomBook() {
        Random random = new Random();
        this.rec = bookshelf.getBooks().get(random.nextInt(bookshelf.getBooks().size()));

        return rec.getTitle();
    }

}
