package model;

import java.time.LocalDate;
import java.util.ArrayList;

// Represents a journal entry for a book with a date created, entry content, and list of highlights.
public class JournalEntry {
    private LocalDate dateCreated;
    private String content;
    private ArrayList<String> highlight;

    public JournalEntry(LocalDate dateCreated, String content) {
        this.dateCreated = dateCreated;
        this.content = content;

    }

    //TODO
    // methods to edit entries + add highlights

}
