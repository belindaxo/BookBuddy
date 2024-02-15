package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalEntryTest {
    private JournalEntry entry1;
    private JournalEntry entry2;

    @BeforeEach
    public void setup() {
        entry1 = new JournalEntry("The Bell Jar", "One of my favourite books ever!!",
                "\"To the person in the bell jar... the world itself is a bad dream.\"");
        entry2 = new JournalEntry("The Trial", "So crazy...",
                "\"It's only because of their stupidity that they're able to be so sure of themselves.\"");
    }

    @Test
    public void testConstructor() {
        assertEquals("The Bell Jar", entry1.getBookTitle());
        assertEquals("One of my favourite books ever!!", entry1.getContent());
        assertEquals("\"To the person in the bell jar... the world itself is a bad dream.\"",
                entry1.getQuote());
    }

    @Test
    public void testSetContent() {
        entry1.setContent("hi!");
        assertEquals("hi!", entry1.getContent());
    }

    @Test
    public void testSetQuote() {
        entry2.setQuote("\"Yippeeeee\"");
        assertEquals("\"Yippeeeee\"", entry2.getQuote());
    }


}
