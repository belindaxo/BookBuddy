package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkBook(String title, String author, String genre, int pageCount, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
        assertEquals(pageCount, book.getPageCount());
    }
}
