package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads bookshelf from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads virtual bookshelf from file and returns it;
    // throws IOException if an error occurs reading data from file
    public VirtualBookshelf read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseVirtualBookshelf(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses virtual bookshelf from JSON object and returns it
    private VirtualBookshelf parseVirtualBookshelf(JSONObject jsonObject) {
        VirtualBookshelf vb = new VirtualBookshelf();
        addBooks(vb, jsonObject);

        JSONObject jsonTracker = jsonObject.getJSONObject("tracker");
        ReadingTracker tracker = parseReadingTracker(jsonTracker);
        vb.setTracker(tracker);

        return vb;
    }

    // EFFECTS: parses reading tracker from JSON object and returns it
    private ReadingTracker parseReadingTracker(JSONObject jsonObject) {
        ReadingTracker tracker = new ReadingTracker();
        tracker.setTotalPagesRead(jsonObject.getInt("pages"));
        tracker.setReadingGoal(jsonObject.getInt("goal"));
        return tracker;
    }

    // MODIFIES: vb
    // EFFECTS: parses books from JSON object and adds them to bookshelf
    private void addBooks(VirtualBookshelf vb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(vb, nextBook);
        }
    }

    // MODIFIES: vb
    // EFFECTS: parses book from JSON object and adds it to bookshelf
    private void addBook(VirtualBookshelf vb, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        String genre = jsonObject.getString("genre");
        int pageCount = jsonObject.getInt("pageCount");
        String status = jsonObject.optString("status", "unread");
        Rating rating = Rating.valueOf(jsonObject.optString("rating", Rating.UNRATED.name()));

        Book book = new Book(title, author, genre, pageCount);
        book.setStatus(status);
        book.setRating(rating);


        JSONObject jsonJournal = jsonObject.getJSONObject("entry");
        String content = jsonJournal.optString("content", "");
        JournalEntry entry = new JournalEntry();
        entry.setContent(content);
        book.setEntry(entry);

        vb.addBook(book);
    }
}