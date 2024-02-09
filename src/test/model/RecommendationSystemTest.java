package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationSystemTest {
    private VirtualBookshelf bookshelf;
    private String rec;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private Book book6;

    @BeforeEach
    public void setup() {
        bookshelf = new VirtualBookshelf();
        book1 = new Book("The Bell Jar", "Sylvia Plath", "Fiction", 240);
        book2 = new Book("The Catcher in the Rye", "J.D. Salinger", "Fiction", 234);
        book3 = new Book("Crime and Punishment", "Fyodor Dostoevsky", "Fiction", 527);
        book4 = new Book("The Trial", "Franz Kafka", "Fiction", 262);
        book5 = new Book("Dune", "Frank Herbert", "Sci-Fi", 681);
        book6 = new Book("Red Comet", "Heather Clark", "Biography", 937);
        rec = null;
    }


}
