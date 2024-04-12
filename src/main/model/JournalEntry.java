package model;

import org.json.JSONObject;
import persistence.Writeable;

// Represents a single journal entry that has a book title and content
public class JournalEntry implements Writeable {
    private String content;

    // EFFECTS: constructs a journal entry with empty content
    public JournalEntry() {
        this.content = "";
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
        EventLog.getInstance().logEvent(new Event("Journal entry added."));
    }

    // EFFECTS: returns the journal entry as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("content", content);
        return json;
    }

}


