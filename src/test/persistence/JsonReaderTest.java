package persistence;

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
            assertEquals(2, vb.getBooks().size());
            checkBook("The Bell Jar", "Sylvia Plath", "Semi-Autobiography", 244,
                    books.get(0));
            checkBook("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 624,
                    books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
