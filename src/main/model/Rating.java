package model;

// Represents the rating options for a book
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

}