package ui;

import model.VirtualBookshelf;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

// Represents the main frame of the BookBuddy application
public class MainFrame extends JFrame {
    private VirtualBookshelf bookshelf;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/bookshelf.json";

    // EFFECTS: constructs the main frame of the application
    public MainFrame() {
        setTitle("BookBuddy");
        setSize(new Dimension(400, 250));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        bookshelf = new VirtualBookshelf();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        MainMenuPanel mainMenuPanel = new MainMenuPanel(
                this::onLoadButtonClicked,
                this::onCreateButtonClicked
        );
        setContentPane(mainMenuPanel);
    }

    // MODIFIES: this
    // EFFECTS: loads bookshelf from file
    private void onLoadButtonClicked(ActionEvent e) {
        try {
            bookshelf = jsonReader.read();
            System.out.println("Loading bookshelf: " + JSON_STORE);
            setContentPane(new HomePanel(
                    this::accessBookshelf,
                    this::openReadingTracker,
                    this::accessReadingJournal,
                    this::getBookRecommendation
            ));
            pack();
            revalidate();
        } catch (IOException ex) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new bookshelf
    private void onCreateButtonClicked(ActionEvent e) {
        bookshelf = new VirtualBookshelf();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("New bookshelf created");
        setContentPane(new HomePanel(
                this::accessBookshelf,
                this::openReadingTracker,
                this::accessReadingJournal,
                this::getBookRecommendation
        ));
        pack();
        revalidate();
    }

    //TODO: Implement the following methods
    private void accessBookshelf(ActionEvent e) {
        System.out.println("Accessing bookshelf...");
        // Add your code here to access the bookshelf
    }

    private void openReadingTracker(ActionEvent e) {
        System.out.println("Opening reading tracker...");
        // Add your code here to open the reading tracker
    }

    private void accessReadingJournal(ActionEvent e) {
        System.out.println("Accessing reading journal...");
        // Add your code here to access the reading journal
    }

    private void getBookRecommendation(ActionEvent e) {
        System.out.println("Getting book recommendation...");
        // Add your code here to get a book recommendation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}