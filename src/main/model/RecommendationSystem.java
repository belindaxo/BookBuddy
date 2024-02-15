package model;

import java.util.ArrayList;
import java.util.Random;

// Suggests books using inputted user preferences for genre, length, and read-status
public class RecommendationSystem {
    private VirtualBookshelf bookshelf;


    public RecommendationSystem(VirtualBookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    //TODO
    // recommendBook method that recommends based off all three/a combo of some kind
    // filterBy..., pass into the rec filter, bool params

    // MODIFIES: this
    // EFFECTS: returns title of random book from list of books that match specified preference and are unread
    public Book recBookByGenre(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getGenre().equalsIgnoreCase(pref) && book.getStatus().equalsIgnoreCase("unread")) {
                temp.add(book);
            }
        }

        // if no books match preference, return null
        if (temp.size() == 0) {
            return null;
        }

        // randomly select one of the matching books in the temp list
        Random random = new Random();
        return temp.get(random.nextInt(temp.size()));

    }

    // MODIFIES: this
    // EFFECTS: returns title of random book from list of books that match specified preference and are unread
    public Book recBookByPageCount(String pref) {
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

        // if no books match preference, return null;
        if (temp.size() == 0) {
            return null;
        }

        // randomly select one of the matching books in the temp list
        Random random = new Random();
        return temp.get(random.nextInt(temp.size()));

    }

    // MODIFIES: this
    // EFFECTS: returns title of random book from list of books that match specified preference and are unread
    public Book recBookByStatus(String pref) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : bookshelf.getBooks()) {
            if (book.getStatus().equalsIgnoreCase(pref)) {
                temp.add(book);
            }
        }

        // if no books match preference, return null
        if (temp.size() == 0) {
            return null;
        }

        // randomly select one of the matching books in the temp list
        Random random = new Random();
        return temp.get(random.nextInt(temp.size()));
    }

    // MODIFIES: this
    // EFFECTS: returns title of random book from the whole bookshelf
    public Book recRandomBook() {
        Random random = new Random();
        return bookshelf.getBooks().get(random.nextInt(bookshelf.getBooks().size()));
    }

}
