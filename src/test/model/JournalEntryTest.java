package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalEntryTest {
    private JournalEntry entry1;
    private JournalEntry entry2;

    @BeforeEach
    public void setup() {
        entry1 = new JournalEntry("One of my favourite books ever!!");
        entry2 = new JournalEntry("So crazy...");
    }

    @Test
    public void testConstructor() {
        assertEquals("One of my favourite books ever!!", entry1.getContent());
        assertEquals("So crazy...", entry2.getContent());

    }

}
