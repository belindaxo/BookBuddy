package model;

import org.json.JSONObject;
import persistence.Writeable;

// Updates and tracks reading progress and goals
public class ReadingTracker implements Writeable {
    private int totalPagesRead;
    private int readingGoal;

    // EFFECTS: constructs a page counter with total pages read and the associated reading goal
    public ReadingTracker() {
        this.totalPagesRead = 0;
        this.readingGoal = 0;
    }

    public int getTotalPagesRead() {
        return this.totalPagesRead;
    }

    // MODIFIES: this
    // EFFECTS: adds specified number of pages read to total pages read
    public void addPagesRead(int pages) {
        this.totalPagesRead += pages;
    }

    public int getReadingGoal() {
        return this.readingGoal;
    }

    public void setReadingGoal(int goal) {
        this.readingGoal = goal;
    }

    // EFFECTS: calculates number of pages left to reach goal
    public int getPagesLeft() {
        int pagesLeft = readingGoal - totalPagesRead;
        return Math.max(pagesLeft, 0);
    }

    // EFFECTS: returns summary of goal progress
    public String goalSummary() {
        if (getPagesLeft() > 1) {
            return "Goal: " + readingGoal + " pages"
                    + "\nPages Read: " + totalPagesRead + " pages"
                    + "\nPages Left: " + getPagesLeft() + " pages";
        } else if (getPagesLeft() == 1) {
            return "Goal: " + readingGoal + " pages"
                    + "\nPages Read: " + totalPagesRead + " page"
                    + "\nPages Left: " + getPagesLeft() + " page";
        } else {
            return "Goal of " + readingGoal + " pages has been met!";
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pages", totalPagesRead);
        json.put("goal", readingGoal);
        return json;
    }
}
