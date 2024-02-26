package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReadingJournalTest {
    private ReadingJournal journal;
    private JournalEntry entry1;
    private JournalEntry entry2;
    private JournalEntry entry3;
    private JournalEntry entry4;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    @BeforeEach
    public void setup() {
        book1 = new Book("The Bell Jar", "Sylvia Plath", "Fiction", 240);
        book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 234);
        book3 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 527);
        book4 = new Book("The Trial", "Franz Kafka", "Fiction", 262);
        journal = new ReadingJournal();
        entry1 = new JournalEntry(book1, "One of my favourite books ever!!");
        entry2 = new JournalEntry(book2, "So crazy...");
        entry3 = new JournalEntry(book3, "wow");
        entry4 = new JournalEntry(book1, "CRAY");

    }

    @Test
    public void testConstructor() {
        assertEquals(0, journal.getAllEntries().size());
    }

    @Test
    public void testAddEntry() {
        journal.addEntry(entry2);
        assertEquals(1, journal.getAllEntries().size());
        assertEquals(entry2, journal.getAllEntries().get(0));
    }

    @Test
    public void testAddEntryMultipleTimes() {
        journal.addEntry(entry1);
        assertEquals(1, journal.getAllEntries().size());

        journal.addEntry(entry2);
        assertEquals(2, journal.getAllEntries().size());

        journal.addEntry(entry3);
        assertEquals(3, journal.getAllEntries().size());

        assertEquals(entry1, journal.getAllEntries().get(0));
        assertEquals(entry2, journal.getAllEntries().get(1));
        assertEquals(entry3, journal.getAllEntries().get(2));

    }

    @Test
    public void testGetEntriesForBook() {
        journal.addEntry(entry1);
        journal.addEntry(entry2);
        journal.addEntry(entry3);
        journal.addEntry(entry4);

        assertEquals(2, journal.getEntriesForBook(book1).size());

        assertEquals(entry1, journal.getEntriesForBook(book1).get(0));

        assertEquals(1, journal.getEntriesForBook(book2).size());
        assertEquals(entry2, journal.getEntriesForBook(book2).get(0));

        assertEquals(0, journal.getEntriesForBook(book4).size());

    }

}
