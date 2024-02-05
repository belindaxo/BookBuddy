package model;

public class ReadingGoal {
    private String goalType;
    private int target;
    private int progress;

    public ReadingGoal(String goalType, int target) {
        this.goalType = goalType;
        this.target = target;
        this.progress = 0;
    }


}
