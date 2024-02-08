package model;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class VirtualBookshelfTest {
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
    public void testAddBook() {
        bookshelf.addBook(book1);
        assertEquals(1, bookshelf.getBooks().size());
        assertEquals(book1, bookshelf.getBooks().get(0));
    }

    @Test
    public void testAddBookMultipleTimes() {
        bookshelf.addBook(book2);
        assertEquals(1, bookshelf.getBooks().size());

        bookshelf.addBook(book3);
        assertEquals(2, bookshelf.getBooks().size());

        bookshelf.addBook(book4);
        assertEquals(3, bookshelf.getBooks().size());

        assertEquals(book2, bookshelf.getBooks().get(0));
        assertEquals(book3, bookshelf.getBooks().get(1));
        assertEquals(book4, bookshelf.getBooks().get(2));

    }

    @Test
    public void testReadBooksCountZeroRead() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusUnread();
        book2.setStatusUnread();
        book3.setStatusUnread();
        book4.setStatusUnread();

        assertEquals(0, bookshelf.readCount());
    }

    @Test
    public void testReadBooksCountOneRead() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusRead();
        book2.setStatusInProgress();
        book3.setStatusUnread();
        book4.setStatusUnread();

        assertEquals(1, bookshelf.readCount());
    }

    @Test
    public void testReadBooksCountMultipleRead() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusRead();
        book2.setStatusInProgress();
        book3.setStatusRead();
        book4.setStatusUnread();

        assertEquals(2, bookshelf.readCount());
    }

    @Test
    public void testReadBooksCountAllRead() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusRead();
        book2.setStatusRead();
        book3.setStatusRead();
        book4.setStatusRead();

        assertEquals(4, bookshelf.readCount());
    }

    @Test
    public void testInProgressBooksCountZeroInProgress() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusRead();
        book2.setStatusRead();
        book3.setStatusRead();
        book4.setStatusUnread();

        assertEquals(0, bookshelf.inProgressCount());
    }

    @Test
    public void testInProgressBooksCountOneInProgress() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusRead();
        book2.setStatusInProgress();
        book3.setStatusRead();
        book4.setStatusUnread();

        assertEquals(1, bookshelf.inProgressCount());
    }

    @Test
    public void testInProgressBooksCountMultipleInProgress() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusRead();
        book2.setStatusInProgress();
        book3.setStatusUnread();
        book4.setStatusInProgress();

        assertEquals(2, bookshelf.inProgressCount());
    }

    @Test
    public void testInProgressBooksCountAllInProgress() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusInProgress();
        book2.setStatusInProgress();
        book3.setStatusInProgress();
        book4.setStatusInProgress();

        assertEquals(4, bookshelf.inProgressCount());
    }

    @Test
    public void testUnreadBooksCountZeroUnread() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusInProgress();
        book2.setStatusRead();
        book3.setStatusInProgress();
        book4.setStatusInProgress();

        assertEquals(0, bookshelf.unreadCount());
    }

    @Test
    public void testUnreadBooksCountOneUnread() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusInProgress();
        book2.setStatusRead();
        book3.setStatusUnread();
        book4.setStatusInProgress();

        assertEquals(1, bookshelf.unreadCount());
    }

    @Test
    public void testUnreadBooksCountMultipleUnread() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusUnread();
        book2.setStatusRead();
        book3.setStatusUnread();
        book4.setStatusUnread();

        assertEquals(3, bookshelf.unreadCount());
    }

    @Test
    public void testUnreadBooksCountAllUnread() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);

        book1.setStatusUnread();
        book2.setStatusUnread();
        book3.setStatusUnread();
        book4.setStatusUnread();

        assertEquals(4, bookshelf.unreadCount());
    }

    @Test
    public void testGetBooksByPageCount() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        assertEquals(3, bookshelf.getBooksByPageCount("short").size());
        assertEquals(2, bookshelf.getBooksByPageCount("long").size());
        assertEquals(1, bookshelf.getBooksByPageCount("medium").size());
        assertEquals(0, bookshelf.getBooksByPageCount("hai").size());

        assertEquals(book1, bookshelf.getBooksByPageCount("short").get(0));
        assertEquals(book2, bookshelf.getBooksByPageCount("short").get(1));
        assertEquals(book4, bookshelf.getBooksByPageCount("short").get(2));

        assertEquals(book3, bookshelf.getBooksByPageCount("medium").get(0));

        assertEquals(book5, bookshelf.getBooksByPageCount("long").get(0));
        assertEquals(book6, bookshelf.getBooksByPageCount("long").get(1));

    }


    @Test
    public void testGetBooksByGenre() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        assertEquals(4, bookshelf.getBooksByGenre("Fiction").size());
        assertEquals(1, bookshelf.getBooksByGenre("Biography").size());
        assertEquals(0, bookshelf.getBooksByGenre("Fantasy").size());

        assertEquals(book1, bookshelf.getBooksByGenre("Fiction").get(0));
        assertEquals(book2, bookshelf.getBooksByGenre("Fiction").get(1));
        assertEquals(book3, bookshelf.getBooksByGenre("Fiction").get(2));
        assertEquals(book4, bookshelf.getBooksByGenre("Fiction").get(3));

        assertEquals(book5, bookshelf.getBooksByGenre("Sci-Fi").get(0));

        assertEquals(book6, bookshelf.getBooksByGenre("Biography").get(0));
    }

    @Test
    public void testGetBooksByAuthor() {
        bookshelf.addBook(book1);
        bookshelf.addBook(book2);
        bookshelf.addBook(book3);
        bookshelf.addBook(book4);
        bookshelf.addBook(book5);
        bookshelf.addBook(book6);

        book3.setAuthor("Franz Kafka");
        book2.setAuthor("Sylvia Plath");
        book6.setAuthor("Sylvia Plath");

        assertEquals(3, bookshelf.getBooksByAuthor("Sylvia Plath").size());
        assertEquals(3, bookshelf.getBooksByAuthor("sylvia plath").size());
        assertEquals(3, bookshelf.getBooksByAuthor("sylvia PLATH").size());
        assertEquals(3, bookshelf.getBooksByAuthor("Plath").size());
        assertEquals(3, bookshelf.getBooksByAuthor("sylvia").size());

        assertEquals(2, bookshelf.getBooksByAuthor("Franz Kafka").size());
        assertEquals(2, bookshelf.getBooksByAuthor("Franz").size());
        assertEquals(2, bookshelf.getBooksByAuthor("kafka").size());

        assertEquals(1, bookshelf.getBooksByAuthor("Frank Herbert").size());

        assertEquals(book1, bookshelf.getBooksByAuthor("sylvia plath").get(0));
        assertEquals(book2, bookshelf.getBooksByAuthor("sylvia").get(1));
        assertEquals(book6, bookshelf.getBooksByAuthor("PLATH").get(2));

        assertEquals(book3, bookshelf.getBooksByAuthor("Franz kafka").get(0));
        assertEquals(book4, bookshelf.getBooksByAuthor("FRANZ").get(1));

        assertEquals(book5, bookshelf.getBooksByAuthor("herbert").get(0));
    }

}
