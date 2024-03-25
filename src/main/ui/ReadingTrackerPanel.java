package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the reading tracker panel of the application
public class ReadingTrackerPanel extends JPanel {
    private final UniversalStyler styler;

    // EFFECTS: constructs the reading tracker panel with buttons to set goal, log pages, view summary, and return home
    public ReadingTrackerPanel(ActionListener setGoalAction, ActionListener logPagesAction,
                               ActionListener viewSummaryAction, ActionListener returnHomeAction) {
        this.styler = new UniversalStyler();
        initPanel(setGoalAction, logPagesAction, viewSummaryAction, returnHomeAction);
    }

    // MODIFIES: this
    // EFFECTS: initializes the reading tracker panel with title and buttons
    private void initPanel(ActionListener setGoalAction, ActionListener logPagesAction,
                           ActionListener viewSummaryAction, ActionListener returnHomeAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(setGoalAction, logPagesAction, viewSummaryAction, returnHomeAction),
                BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with the title and subtitle of the panel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20,0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        JLabel titleLabel = new JLabel("Reading Tracker");
        JLabel subtitleLabel = new JLabel("What would you like to do today?");
        styler.styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to set goal, log pages, view summary, and return home
    private JPanel createButtonPanel(ActionListener setGoalAction, ActionListener logPagesAction,
                                     ActionListener viewSummaryAction, ActionListener returnHomeAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));
        GridBagConstraints gbc = styler.createButtonConstraints();

        JButton setGoalButton = new JButton("Set reading goal");
        JButton logPagesButton = new JButton("Log pages read");
        JButton viewSummaryButton = new JButton("View goal summary");
        JButton returnHomeButton = new JButton("Return to home");

        styler.styleAllButtons(setGoalButton, logPagesButton, viewSummaryButton, returnHomeButton);

        setGoalButton.addActionListener(setGoalAction);
        logPagesButton.addActionListener(logPagesAction);
        viewSummaryButton.addActionListener(viewSummaryAction);
        returnHomeButton.addActionListener(returnHomeAction);

        buttonPanel.add(setGoalButton, gbc);
        buttonPanel.add(logPagesButton, gbc);
        buttonPanel.add(viewSummaryButton, gbc);
        buttonPanel.add(returnHomeButton, gbc);

        return buttonPanel;
    }
}