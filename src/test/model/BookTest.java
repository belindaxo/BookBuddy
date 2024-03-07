package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
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
    }

    @Test
    public void testConstructor() {
        assertEquals("The Bell Jar", book1.getTitle());
        assertEquals("Sylvia Plath", book1.getAuthor());
        assertEquals("Fiction", book1.getGenre());
        assertEquals(240, book1.getPageCount());
        assertEquals("unread", book1.getStatus());
        assertEquals(Rating.UNRATED, book1.getRating());
    }

    @Test
    public void testSetStatusRead() {
        book1.setStatusRead();
        assertEquals("read", book1.getStatus());
    }

    @Test
    public void testSetStatusInProgress() {
        book2.setStatusInProgress();
        assertEquals("in progress", book2.getStatus());
    }

    @Test
    public void testSetStatusUnread() {
        book3.setStatusUnread();
        assertEquals("unread", book1.getStatus());
    }

    @Test
    public void testToString() {
        book1.setRating(Rating.FIVE_STARS);

        assertEquals("The Bell Jar by Sylvia Plath\nGenre: Fiction\nPage count: 240" +
                        "\nStatus: unread\nRating: *****",
                book1.toString());
    }




}
