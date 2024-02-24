package persistence;

import model.VirtualBookshelf;
import model.Book;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    public void testWriterInvalidFile() {
        try {
            VirtualBookshelf vb = new VirtualBookshelf();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyBookshelf() {
        try {
            VirtualBookshelf vb = new VirtualBookshelf();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBookshelf.json");
            writer.open();
            writer.write(vb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBookshelf.json");
            vb = reader.read();
            assertEquals(0, vb.getBooks().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralBookshelf() {
        try {
            VirtualBookshelf vb = new VirtualBookshelf();
            vb.addBook(new Book("The Bell Jar", "Sylvia Plath",
                    "Semi-Autobiography", 244));
            vb.addBook(new Book("Crime and Punishment", "Fyodor Dostoevsky",
                    "Fiction", 624));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookshelf.json");
            writer.open();
            writer.write(vb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookshelf.json");
            vb = reader.read();
            List<Book> books = vb.getBooks();
            assertEquals(2, books.size());
            checkBook("The Bell Jar", "Sylvia Plath", "Semi-Autobiography", 244,
                    books.get(0));
            checkBook("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 624,
                    books.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
