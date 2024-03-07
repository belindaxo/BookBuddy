package model;

import java.util.List;
import java.util.ArrayList;


// Represents a reading journal that has a list of journal entries
public class ReadingJournal {

    public static List<JournalEntry> getAllEntries(VirtualBookshelf bookshelf) {
        List<JournalEntry> allEntries = new ArrayList<>();
        if (bookshelf.getBooks() != null) {
            for (Book b : bookshelf.getBooks()) {
                JournalEntry entry = b.getJournalEntry();
                if (entry != null) {
                    allEntries.add(entry);
                }
            }
        }
        return allEntries;
    }
}
