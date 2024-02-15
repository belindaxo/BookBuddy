package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReadingJournalTest {
    private ReadingJournal journal;
    private JournalEntry entry1;
    private JournalEntry entry2;
    private JournalEntry entry3;

    @BeforeEach
    public void setup() {
        journal = new ReadingJournal();
        entry1 = new JournalEntry("The Bell Jar", "One of my favourite books ever!!",
                "\"To the person in the bell jar... the world itself is a bad dream.\"");
        entry2 = new JournalEntry("The Trial", "So crazy...",
                "\"It's only because of their stupidity that they're able to be so sure of themselves.\"");
        entry3 = new JournalEntry("BookA", "wow", "\"blah blah blah\"");
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

        entry1.setBookTitle("Abc");
        entry3.setBookTitle("Abc");

        assertEquals(2, journal.getEntriesForBook("Abc").size());
        assertEquals(2, journal.getEntriesForBook("abc").size());
        assertEquals(2, journal.getEntriesForBook("ABC").size());

        assertEquals(entry1, journal.getEntriesForBook("AbC").get(0));
        assertEquals(entry3, journal.getEntriesForBook("aBc").get(1));

        assertEquals(1, journal.getEntriesForBook("The trial").size());
        assertEquals(entry2, journal.getEntriesForBook("the trial").get(0));

        assertEquals(0, journal.getEntriesForBook("Jane Eyre").size());

    }

}
