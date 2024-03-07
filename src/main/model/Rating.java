package model;

public enum Rating {
    UNRATED("Unrated"),
    ONE_STAR("*"),
    TWO_STARS("**"),
    THREE_STARS("***"),
    FOUR_STARS("****"),
    FIVE_STARS("*****");

    private final String stars;

    Rating(String stars) {
        this.stars = stars;
    }

    public String getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return stars;
    }
}
