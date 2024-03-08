package persistence;

import model.*;

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
            Book book1 = new Book("The Bell Jar", "Sylvia Plath", "Semi-Autobiography", 244);
            vb.addBook(book1);
            Book book2 = new Book("Crime and Punishment", "Fyodor Dostoevsky",
                    "Fiction", 624);
            vb.addBook(book2);

            vb.getBooks().get(0).setStatus("read");
            vb.getBooks().get(0).setRating(Rating.FIVE_STARS);
            vb.getBooks().get(0).addContent("sample");

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBookshelf.json");
            writer.open();
            writer.write(vb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBookshelf.json");
            vb = reader.read();
            List<Book> books = vb.getBooks();
            assertEquals(2, books.size());
            assertEquals("sample", vb.getBooks().get(0).getEntry().getContent());
            assertEquals("", vb.getBooks().get(1).getEntry().getContent());
            checkBook("The Bell Jar", "Sylvia Plath", "Semi-Autobiography", 244,
                    "read", Rating.FIVE_STARS, books.get(0));
            checkBook("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 624,
                    "unread", Rating.UNRATED, books.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
