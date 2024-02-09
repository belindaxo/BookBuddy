package model;

// Tracks daily reading progress and goals
public class ReadingTracker {
    private int dailyReadingGoal;
    private int pagesReadToday;

    public ReadingTracker(int dailyReadingGoal) {
        this.dailyReadingGoal = dailyReadingGoal;
        this.pagesReadToday = 0;
    }

    //TODO
    // updateDailyReading, checkProgress


    public int getDailyReadingGoal() {
        return dailyReadingGoal;
    }

    public void setDailyReadingGoal(int dailyReadingGoal) {
        this.dailyReadingGoal = dailyReadingGoal;
    }

    public int getPagesReadToday() {
        return pagesReadToday;
    }

    public void setPagesReadToday(int pagesReadToday) {
        this.pagesReadToday = pagesReadToday;
    }
}
