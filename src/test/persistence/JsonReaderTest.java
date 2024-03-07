package persistence;

import model.JournalEntry;
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
            List<Book> books = vb.getBooks();
            assertEquals(2, books.size());
            checkBook("The Bell Jar", "Sylvia Plath", "Semi-Autobiography", 244,
                    "unread", Rating.FIVE_STARS, null, books.get(0));
            checkBook("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 624,
                    "unread", Rating.UNRATED, null, books.get(1));

            assertEquals(200, vb.getTracker().getTotalPagesRead());
            assertEquals(1000, vb.getTracker().getReadingGoal());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
