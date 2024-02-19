package model;

import java.util.ArrayList;

// Represents a reading journal that has a list of journal entries
public class ReadingJournal {
    private final ArrayList<JournalEntry> journal;

    // EFFECTS: initializes a reading journal with an empty list of journal entries
    public ReadingJournal() {
        this.journal = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds an entry to reading journal
    public void addEntry(JournalEntry entry) {
        journal.add(entry);
    }

    // EFFECTS: returns a list of all entries in reading journal
    public ArrayList<JournalEntry> getAllEntries() {
        return journal;
    }

    // EFFECTS: returns a list of all entries in reading journal for the specified book
    public ArrayList<JournalEntry> getEntriesForBook(String title) {
        ArrayList<JournalEntry> temp = new ArrayList<>();
        for (JournalEntry entry : journal) {
            if (entry.getBookTitle().equalsIgnoreCase(title)) {
                temp.add(entry);
            }
        }
        return temp;
    }
}
