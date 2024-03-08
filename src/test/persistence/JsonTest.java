package persistence;

import model.Book;
import model.JournalEntry;
import model.Rating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class JsonTest {

    protected void checkBook(String title, String author, String genre, int pageCount, String status,
                             Rating rating, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
        assertEquals(pageCount, book.getPageCount());
        assertEquals(status, book.getStatus());
        assertEquals(rating, book.getRating());
    }
}
