package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecommendationSystemTest {
    private RecommendationSystem rec;
    private VirtualBookshelf bookshelf;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;

    @BeforeEach
    public void setup() {
        bookshelf = new VirtualBookshelf();
        rec = new RecommendationSystem(bookshelf);
        book1 = new Book("The Bell Jar", "Sylvia Plath", "Fiction", 240);
        book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 234);
        book3 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 527);
        book4 = new Book("The Trial", "Franz Kafka", "Fiction", 262);
        book5 = new Book("Dune", "Frank Herbert", "Sci-Fi", 681);
        book6 = new Book("Red Comet", "Heather Clark", "Biography", 937);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, bookshelf.getBooks().size());
    }

    @Test
    public void testRecBookByGenre() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        assertEquals(book5, rec.recBookByGenre("Sci-Fi"));
        assertNull(rec.recBookByGenre("Cooking"));
        assertEquals( "Fiction", rec.recBookByGenre("Fiction").getGenre());
        assertEquals("Biography", rec.recBookByGenre("Biography").getGenre());
    }

    @Test
    public void testRecBookByPageCount() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        assertEquals(book3, rec.recBookByPageCount("medium"));
        assertTrue(rec.recBookByPageCount("short").getPageCount() <= 350);
        assertTrue(rec.recBookByPageCount("medium").getPageCount() > 350
        && rec.recBookByPageCount("medium").getPageCount() <= 650);
        assertTrue(rec.recBookByPageCount("long").getPageCount() > 650);
    }

    @Test
    public void testRecBookByStatus() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        book1.setStatusInProgress();
        book2.setStatusRead();

        assertEquals(book1, rec.recBookByStatus("in progress"));
        assertEquals("unread", rec.recBookByStatus("unread").getStatus());
        assertEquals("read", rec.recBookByStatus("read").getStatus());
        assertEquals("in progress", rec.recBookByStatus("in progress").getStatus());
    }

    @Test
    public void testRecRandomBook() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        assertTrue(bookshelf.getBooks().contains(rec.recRandomBook()));
    }



}
