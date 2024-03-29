package persistence;

import model.Rating;
import model.VirtualBookshelf;
import model.Book;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            VirtualBookshelf vb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReaderEmptyBookshelf() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBookshelf.json");
        try {
            VirtualBookshelf vb = reader.read();
            assertEquals(0, vb.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralBookshelf() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBookshelf.json");
        try {
            VirtualBookshelf vb = reader.read();
            System.out.println("Total pages read from VirtualBookshelf: " + vb.getTracker().getTotalPagesRead());
            List<Book> books = vb.getBooks();
            assertEquals(2, books.size());

            assertEquals("One of my fave books ever!", books.get(0).getEntry().getContent());
            checkBook("The Bell Jar", "Sylvia Plath", "Semi-Autobiography", 244,
                    "read", Rating.FIVE_STARS, books.get(0));

            assertEquals("", books.get(1).getEntry().getContent());
            checkBook("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 624,
                    "unread", Rating.UNRATED, books.get(1));

            assertEquals(200, vb.getTracker().getTotalPagesRead());
            assertEquals(1000, vb.getTracker().getReadingGoal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}