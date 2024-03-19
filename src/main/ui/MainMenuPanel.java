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
    private final UniversalStyler styler;

    // EFFECTS: constructs the main menu panel with buttons to load saved bookshelf and create new bookshelf
    public MainMenuPanel(ActionListener loadAction, ActionListener createAction) {
        this.loadAction = loadAction;
        this.createAction = createAction;
        this.styler = new UniversalStyler();
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
        styler.styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to load saved bookshelf and create new bookshelf
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));

        GridBagConstraints gbc = styler.createButtonConstraints();
        createButtons();

        buttonPanel.add(loadButton, gbc);
        buttonPanel.add(createButton, gbc);

        return buttonPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates buttons to load saved bookshelf and create new bookshelf
    private void createButtons() {
        loadButton = new JButton("Load Saved Bookshelf");
        createButton = new JButton("Create New Bookshelf");

        loadButton.addActionListener(loadAction);
        createButton.addActionListener(createAction);

        styler.styleButton(loadButton);
        styler.styleButton(createButton);
    }
}