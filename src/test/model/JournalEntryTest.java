package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JournalEntryTest {
    private Book book1;
    private Book book2;
    private JournalEntry entry1;
    private JournalEntry entry2;

    @BeforeEach
    public void setup() {
        book1 = new Book("The Bell Jar", "Sylvia Plath", "Fiction", 240);
        book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 234);
        entry1 = new JournalEntry(book1, "One of my favourite books ever!!");
        entry2 = new JournalEntry(book2, "So crazy...");
    }

    @Test
    public void testConstructor() {
        assertEquals("The Bell Jar", entry1.getBookTitle());
        assertEquals("One of my favourite books ever!!", entry1.getContent());
    }

}
