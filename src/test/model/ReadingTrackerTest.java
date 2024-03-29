package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReadingTrackerTest {
     private ReadingTracker tracker;

    @BeforeEach
    public void setup() {
        this.tracker = new ReadingTracker();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, tracker.getReadingGoal());
        assertEquals(0, tracker.getTotalPagesRead());
    }

    @Test
    public void testGetPages() {
        assertEquals(0, tracker.getPagesLeft());

        //reading goal set, pages read = 0
        tracker.setReadingGoal(100);
        assertEquals(100, tracker.getPagesLeft());

        //add pages read
        tracker.addPagesRead(45);
        assertEquals(55, tracker.getPagesLeft());

        //add pages read to just before edge case
        tracker.addPagesRead(54);
        assertEquals(1, tracker.getPagesLeft());

        //complete goal (boundary)
        tracker.addPagesRead(1);
        assertEquals(0, tracker.getPagesLeft());

        //surpass goal
        tracker.addPagesRead(5);
        assertEquals(0, tracker.getPagesLeft());
    }

    @Test
    public void testGoalSummary() {
        tracker.setReadingGoal(50);
        // pages left > 1
        tracker.addPagesRead(10);
        assertEquals("Goal: " + tracker.getReadingGoal() + " pages"
                + "\nPages Read: " + tracker.getTotalPagesRead() + " pages"
                + "\nPages Left: " + tracker.getPagesLeft() + " pages",
                tracker.goalSummary());

        // pages left = 2
        tracker.addPagesRead(38);
        assertEquals("Goal: " + tracker.getReadingGoal() + " pages"
                        + "\nPages Read: " + tracker.getTotalPagesRead() + " pages"
                        + "\nPages Left: " + tracker.getPagesLeft() + " pages",
                tracker.goalSummary());

        // pages left = 1
        tracker.addPagesRead(1);
        assertEquals("Goal: " + tracker.getReadingGoal() + " pages"
                + "\nPages Read: " + tracker.getTotalPagesRead() + " pages"
                + "\nPages Left: " + tracker.getPagesLeft() + " page",
                tracker.goalSummary());

        // pages left = 0
        tracker.addPagesRead(1);
        assertEquals("Goal of " + tracker.getReadingGoal()
                + " pages has been met!",
                tracker.goalSummary());
    }

    @Test
    public void testResetReadingProgress() {
        tracker.setReadingGoal(50);
        tracker.addPagesRead(10);
        tracker.resetReadingProgress();
        assertEquals(0, tracker.getTotalPagesRead());
        assertEquals(50, tracker.getPagesLeft());
    }
}