package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the home page of BookBuddy application
public class HomePanel extends JPanel {
    private JButton bookshelfButton;
    private JButton trackerButton;
    private JButton journalButton;
    private JButton bookRecButton;
    private final UniversalStyler styler;

    // EFFECTS: constructs a home page panel with buttons to access bookshelf, reading tracker, reading journal,
    //          and book recommendation features
    public HomePanel(ActionListener bookshelfAction, ActionListener trackerAction,
                     ActionListener journalAction, ActionListener bookRecAction) {
        this.styler = new UniversalStyler();
        initPanel(bookshelfAction, trackerAction, journalAction, bookRecAction);
    }

    // MODIFIES: this
    // EFFECTS: initializes the home page panel with title and buttons
    private void initPanel(ActionListener bookshelfAction, ActionListener trackerAction,
                           ActionListener journalAction, ActionListener bookRecAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));

        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(bookshelfAction, trackerAction, journalAction, bookRecAction), BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with the title of the application
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        JLabel titleLabel = new JLabel("BookBuddy");
        JLabel subtitleLabel = new JLabel("What would you like to do today?");
        styler.styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to access bookshelf, reading tracker, reading journal, and book rec system
    private JPanel createButtonPanel(ActionListener bookshelfAction, ActionListener trackerAction,
                                     ActionListener journalAction, ActionListener bookRecAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = styler.createButtonConstraints();
        buttonPanel.setBackground(new Color(26, 67, 76));

        createButtons(bookshelfAction, trackerAction, journalAction, bookRecAction);

        buttonPanel.add(bookshelfButton, gbc);
        buttonPanel.add(trackerButton, gbc);
        buttonPanel.add(journalButton, gbc);
        buttonPanel.add(bookRecButton, gbc);

        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates buttons to access bookshelf, reading tracker, reading journal, and book rec system
    private void createButtons(ActionListener bookshelfAction, ActionListener trackerAction,
                               ActionListener journalAction, ActionListener bookRecAction) {
        bookshelfButton = new JButton("Access your virtual bookshelf");
        trackerButton = new JButton("Open reading tracker");
        journalButton = new JButton("Access your reading journal");
        bookRecButton = new JButton("Get a book recommendation");

        styler.styleButton(bookshelfButton);
        styler.styleButton(trackerButton);
        styler.styleButton(journalButton);
        styler.styleButton(bookRecButton);

        bookshelfButton.addActionListener(bookshelfAction);
        trackerButton.addActionListener(trackerAction);
        journalButton.addActionListener(journalAction);
        bookRecButton.addActionListener(bookRecAction);
    }
}