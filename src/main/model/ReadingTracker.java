package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

// tracks reading goal progress and logs reading habits
public class ReadingTracker {
    private HashMap<LocalDate, Integer> readingLog;
    private ArrayList<ReadingGoal> readingGoals;

    public ReadingTracker() {
        this.readingLog = new HashMap<>();
    }

}
