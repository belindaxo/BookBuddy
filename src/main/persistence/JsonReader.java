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
    private String source;

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
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses virtual bookshelf from JSON object and returns it
    private VirtualBookshelf parseVirtualBookshelf(JSONObject jsonObject) {
        VirtualBookshelf vb = new VirtualBookshelf();
        addBooks(vb, jsonObject);
        if (jsonObject.has("tracker")) {
            JSONObject jsonTracker = jsonObject.getJSONObject("tracker");
            ReadingTracker tracker = parseReadingTracker(jsonTracker);
            vb.setTracker(tracker);
        }
        return vb;
    }

    private ReadingTracker parseReadingTracker(JSONObject jsonObject) {
        ReadingTracker tracker = new ReadingTracker();
        tracker.addPagesRead(jsonObject.getInt("pages"));
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
        int pageCount = jsonObject.getInt("page count");
        String status = jsonObject.optString("status", "unread");
        Rating rating = Rating.valueOf(jsonObject.getString("rating"));

        Book book = new Book(title, author, genre, pageCount);
        book.setStatus(status);
        book.setRating(rating);

        if (jsonObject.has("entry")) {
            JSONObject jsonJournal = jsonObject.getJSONObject("entry");
            String content = jsonJournal.getString("content");

            JournalEntry entry = new JournalEntry(content);
            book.setJournalEntry(entry);
        }
        vb.addBook(book);
    }
}
