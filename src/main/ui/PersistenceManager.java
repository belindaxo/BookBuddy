package ui;

import model.Event;
import model.VirtualBookshelf;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.EventLog;

import java.io.IOException;

// Represents a persistence manager that handles saving and loading of the virtual bookshelf
public class PersistenceManager {
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // EFFECTS: constructs a persistence manager with the given json store
    public PersistenceManager(String jsonStore) {
        this.jsonWriter = new JsonWriter(jsonStore);
        this.jsonReader = new JsonReader(jsonStore);
    }

    // MODIFIES: this
    // EFFECTS: loads the bookshelf from file and returns it
    public VirtualBookshelf loadBookshelf() throws IOException {
        EventLog.getInstance().clear();
        EventLog.getInstance().logEvent(new Event("Loaded bookshelf from file."));
        return jsonReader.read();
    }

    // MODIFIES: this
    // EFFECTS: saves the given bookshelf to file
    public void saveBookshelf(VirtualBookshelf bookshelf) throws IOException {
        jsonWriter.open();
        jsonWriter.write(bookshelf);
        jsonWriter.close();
        EventLog.getInstance().logEvent(new Event("Saved bookshelf to file."));
    }
}