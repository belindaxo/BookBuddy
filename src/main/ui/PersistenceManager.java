package ui;

import model.Event;
import model.VirtualBookshelf;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.EventLog;

import java.io.IOException;

public class PersistenceManager {
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    public PersistenceManager(String jsonStore) {
        this.jsonWriter = new JsonWriter(jsonStore);
        this.jsonReader = new JsonReader(jsonStore);
    }

    public VirtualBookshelf loadBookshelf() throws IOException {
        EventLog.getInstance().clear();
        EventLog.getInstance().logEvent(new Event("Loaded bookshelf from file."));
        return jsonReader.read();
    }

    public void saveBookshelf(VirtualBookshelf bookshelf) throws IOException {
        jsonWriter.open();
        jsonWriter.write(bookshelf);
        jsonWriter.close();
        EventLog.getInstance().logEvent(new Event("Saved bookshelf to file."));
    }
}