package model;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BookshelfTest {
    private Bookshelf bookshelf;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    @BeforeEach
    public void setup() {
        bookshelf = new Bookshelf();
        book1 = new Book("The Bell Jar", "Sylvia Plath", "Fiction", 240);
        book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 234);
        book3 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 527);
        book4 = new Book("The Trial", "Franz Kafka", "Fiction", 262);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, bookshelf.getBookshelf().size());
    }

    @Test
    public void testAddBook() {
        bookshelf.addBook(book1);
        assertEquals(1, bookshelf.getBookshelf().size());
    }

    @Test
    public void testAddBookMultipleTimes() {
        bookshelf.addBook(book2);
        assertEquals(1, bookshelf.getBookshelf().size());

        bookshelf.addBook(book3);
        assertEquals(2, bookshelf.getBookshelf().size());

        bookshelf.addBook(book4);
        assertEquals(3, bookshelf.getBookshelf().size());

        assertEquals(book2, bookshelf.getBookshelf().get(0));
        assertEquals(book3, bookshelf.getBookshelf().get(1));
        assertEquals(book4, bookshelf.getBookshelf().get(2));

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

        assertEquals(0, bookshelf.readBooksCount());
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

        assertEquals(1, bookshelf.readBooksCount());
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

        assertEquals(2, bookshelf.readBooksCount());
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

        assertEquals(4, bookshelf.readBooksCount());
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

        assertEquals(0, bookshelf.inProgressBooksCount());
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

        assertEquals(1, bookshelf.inProgressBooksCount());
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

        assertEquals(2, bookshelf.inProgressBooksCount());
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

        assertEquals(4, bookshelf.inProgressBooksCount());
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

        assertEquals(0, bookshelf.unreadBooksCount());
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

        assertEquals(1, bookshelf.unreadBooksCount());
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

        assertEquals(3, bookshelf.unreadBooksCount());
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

        assertEquals(4, bookshelf.unreadBooksCount());
    }

}
