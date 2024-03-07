package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReadingJournalTest {
    private VirtualBookshelf bookshelf;
    private Book book1;
    private Book book2;

    @Test
    public void testGetAllEntries() {
        VirtualBookshelf bookshelf = new VirtualBookshelf();
        Book book1 = new Book("The Bell Jar", "Sylvia Plath", "Fiction", 434);
        Book book2 = new Book("Ariel", "Sylvia Plath", "Poetry", 150);

        bookshelf.addBook(book1);
        bookshelf.addBook(book2);

        book1.setJournalEntry(new JournalEntry("Content"));

        assertEquals(1, ReadingJournal.getAllEntries(bookshelf).size());
        assertEquals("Content", ReadingJournal.getAllEntries(bookshelf).get(0).getContent());
    }
}
