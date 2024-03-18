package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the main menu panel of the BookBuddy application
public class MainMenuPanel extends JPanel {
    private JButton loadButton;
    private JButton createButton;
    private final ActionListener loadAction;
    private final ActionListener createAction;

    // EFFECTS: constructs the main menu panel with buttons to load saved bookshelf and create new bookshelf
    public MainMenuPanel(ActionListener loadAction, ActionListener createAction) {
        this.loadAction = loadAction;
        this.createAction = createAction;
        initPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the main menu panel with title and buttons
    private void initPanel() {
        this.setLayout(new BorderLayout());
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(), BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with a title and subtitle
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        JLabel titleLabel = new JLabel("BookBuddy");
        JLabel subtitleLabel = new JLabel("your personal library assistant");
        styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: styles the title and subtitle labels
    private void styleTitleLabel(JLabel titleLabel, JLabel subtitleLabel) {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 254, 255));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitleLabel.setForeground(new Color(255, 254, 255));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to load saved bookshelf and create new bookshelf
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));

        GridBagConstraints gbc = createButtonConstraints();
        createButtons();

        buttonPanel.add(loadButton, gbc);
        buttonPanel.add(createButton, gbc);

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
    // EFFECTS: creates buttons to load saved bookshelf and create new bookshelf
    private void createButtons() {
        loadButton = new JButton("Load Saved Bookshelf");
        createButton = new JButton("Create New Bookshelf");

        loadButton.addActionListener(loadAction);
        createButton.addActionListener(createAction);

        styleButton(loadButton);
        styleButton(createButton);
    }

    // MODIFIES: button
    // EFFECTS: styles the button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(194, 243, 78));
        button.setForeground(new Color(77, 99, 26));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
    }
}