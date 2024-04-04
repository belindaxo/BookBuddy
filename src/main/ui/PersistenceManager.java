package ui;

import model.VirtualBookshelf;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

public class PersistenceManager {
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    public PersistenceManager(String jsonStore) {
        this.jsonWriter = new JsonWriter(jsonStore);
        this.jsonReader = new JsonReader(jsonStore);
    }

    public VirtualBookshelf loadBookshelf() throws IOException {
        return jsonReader.read();
    }

    public void saveBookshelf(VirtualBookshelf bookshelf) throws IOException {
        jsonWriter.open();
        jsonWriter.write(bookshelf);
        jsonWriter.close();
    }
}