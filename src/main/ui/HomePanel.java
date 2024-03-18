package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

// Represents the home page of BookBuddy application
public class HomePanel extends JPanel {
    private JButton bookshelfButton;
    private JButton trackerButton;
    private JButton journalButton;
    private JButton bookRecButton;

    // EFFECTS: constructs a home page panel with buttons to access bookshelf, reading tracker, reading journal,
    //          and book recommendation features
    public HomePanel(ActionListener bookshelfAction, ActionListener trackerAction,
                     ActionListener journalAction, ActionListener bookRecAction) {
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
        styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: styles the title and subtitle labels
    private void styleTitleLabel(JLabel titleLabel, JLabel subtitleLabel) {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(255, 254, 255));

        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setForeground(new Color(255, 254, 255));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to access bookshelf, reading tracker, reading journal, and book rec system
    private JPanel createButtonPanel(ActionListener bookshelfAction, ActionListener trackerAction,
                                     ActionListener journalAction, ActionListener bookRecAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = createButtonConstraints();
        buttonPanel.setBackground(new Color(26, 67, 76));

        createButtons(bookshelfAction, trackerAction, journalAction, bookRecAction);

        buttonPanel.add(bookshelfButton, gbc);
        buttonPanel.add(trackerButton, gbc);
        buttonPanel.add(journalButton, gbc);
        buttonPanel.add(bookRecButton, gbc);

        return buttonPanel;
    }

    // EFFECTS: creates constraints for the buttons
    private GridBagConstraints createButtonConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        return gbc;
    }

    // MODIFIES: this
    // EFFECTS: creates buttons to access bookshelf, reading tracker, reading journal, and book rec system
    private void createButtons(ActionListener bookshelfAction, ActionListener trackerAction,
                               ActionListener journalAction, ActionListener bookRecAction) {
        bookshelfButton = new JButton("Access your virtual bookshelf");
        trackerButton = new JButton("Open reading tracker");
        journalButton = new JButton("Access your reading journal");
        bookRecButton = new JButton("Get a book recommendation");

        styleAllButtons();

        addActionsToButtons(bookshelfAction, trackerAction, journalAction, bookRecAction);
    }

    // MODIFIES: this
    // EFFECTS: styles all buttons
    private void styleAllButtons() {
        JButton[] buttons = {bookshelfButton, trackerButton, journalButton, bookRecButton};
        for (JButton button : buttons) {
            styleButton(button);
        }
    }

    // MODIFIES: button
    // EFFECTS: styles the given button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(194, 243, 78));
        button.setForeground(new Color(77, 99, 26));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
    }

    // MODIFIES: this
    // EFFECTS: adds actions to the buttons
    private void addActionsToButtons(ActionListener bookshelfAction, ActionListener trackerAction,
                                     ActionListener journalAction, ActionListener bookRecAction) {
        Map<JButton, ActionListener> buttonActions = Map.of(
                bookshelfButton, bookshelfAction,
                trackerButton, trackerAction,
                journalButton, journalAction,
                bookRecButton, bookRecAction
        );

        for (JButton button : buttonActions.keySet()) {
            button.addActionListener(buttonActions.get(button));
        }
    }
}