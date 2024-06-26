package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;

// Represents a users collection of books added to their bookshelf
public class VirtualBookshelf implements Writeable {
    private final ArrayList<Book> books;
    private ReadingTracker tracker;

    // EFFECTS: initializes a virtual bookshelf with an empty list of books and a new reading tracker
    public VirtualBookshelf() {
        this.books = new ArrayList<>();
        this.tracker = new ReadingTracker();
    }

    // MODIFIES: this
    // EFFECTS: adds book to bookshelf
    public void addBook(Book book) {
        this.books.add(book);
        EventLog.getInstance().logEvent(new Event("Added " + book.getTitle() + " to bookshelf."));
    }

    // EFFECTS: returns the number of books in bookshelf that are read
    public int readCount() {
        int count = 0;
        for (Book book : books) {
            if (book.getStatus().equals("read")) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: returns the number of books in bookshelf that are in progress
    public int inProgressCount() {
        int count = 0;
        for (Book book : books) {
            if (book.getStatus().equals("in progress")) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: returns the number of books in bookshelf that are unread
    public int unreadCount() {
        int count = 0;
        for (Book book : books) {
            if (book.getStatus().equals("unread")) {
                count++;
            }
        }
        return count;
    }

    // EFFECTS: returns list of all books in bookshelf.
    public ArrayList<Book> getBooks() {
        return this.books;
    }

    public ArrayList<String> getBookTitles() {
        ArrayList<String> temp = new ArrayList<>();
        for (Book b : books) {
            temp.add(b.getTitle());
        }
        EventLog.getInstance().logEvent(new Event("Viewed all books in bookshelf."));
        return temp;
    }

    // EFFECTS: returns a list of books in bookshelf that match specified genre
    public ArrayList<Book> getBooksByGenre(String g) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (g.equalsIgnoreCase(b.getGenre())) {
                temp.add(b);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewed books by genre."));
        return temp;
    }

    // REQUIRES: pc = "short" OR "medium" OR "long"
    // EFFECTS: returns a list of books in bookshelf that match specified length
    public ArrayList<Book> getBooksByPageCount(String pc) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (pc.equalsIgnoreCase("short") && (b.getPageCount() <= 350)) {
                temp.add(b);
            } else if (pc.equalsIgnoreCase("medium")
                    && (b.getPageCount() > 350)
                    && (b.getPageCount() <= 650)) {
                temp.add(b);
            } else if (pc.equalsIgnoreCase("long") && (b.getPageCount() > 650)) {
                temp.add(b);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewed books by length."));
        return temp;
    }

    // EFFECTS: returns a list of books in bookshelf that match specified author
    public ArrayList<Book> getBooksByAuthor(String a) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(a.toLowerCase())) {
                temp.add(b);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewed books by author."));
        return temp;
    }

    // REQUIRES: s = "read" OR "unread" OR "in progress"
    // EFFECTS: returns a list of books in bookshelf that have specified status
    public ArrayList<Book> getBooksByStatus(String s) {
        ArrayList<Book> temp = new ArrayList<>();
        for (Book b : books) {
            if (b.getStatus().equalsIgnoreCase(s)) {
                temp.add(b);
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewed books by status."));
        return temp;
    }

    public ReadingTracker getTracker() {
        return this.tracker;
    }

    // EFFECTS: adds virtual bookshelf to JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        json.put("tracker", this.tracker.toJson());
        return json;
    }

    public void setTracker(ReadingTracker tracker) {
        this.tracker = tracker;
    }

    // EFFECTS: returns books in bookshelf as a JSON array
    public JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns a list of all journal entries in the bookshelf
    public List<JournalEntry> getAllEntries() {
        List<JournalEntry> allEntries = new ArrayList<>();
        for (Book b : books) {
            JournalEntry entry = b.getEntry();
            if (!b.isEntryEmpty()) {
                allEntries.add(entry);
            }
        }
        EventLog.getInstance().logEvent(new Event("Reading journal viewed."));
        return allEntries;
    }

    public void addPagesRead(int pages) {
        this.tracker.addPagesRead(pages);
    }

    public void setTotalPagesRead(int pages) {
        this.tracker.setTotalPagesRead(pages);
    }

    public int getTotalPagesRead() {
        return this.tracker.getTotalPagesRead();
    }

    public int getReadingGoal() {
        return this.tracker.getReadingGoal();
    }

    public void setReadingGoal(int goal) {
        this.tracker.setReadingGoal(goal);
    }

    public int getPagesLeft() {
        return this.tracker.getPagesLeft();
    }

    public String getGoalSummary() {
        return this.tracker.goalSummary();
    }

    // MODIFIES: this
    // EFFECTS: resets the reading progress
    public void resetReadingProgress() {
        this.tracker.resetReadingProgress();
    }

    // MODIFIES: this
    // EFFECTS: removes book from bookshelf
    public void removeBook(Book book) {
        this.books.remove(book);
        EventLog.getInstance().logEvent(new Event(book.getTitle() + "removed from bookshelf."));
    }
}