package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the panel that contains the reading tracker
public class ReadingTrackerPanel extends JPanel {
    private final UniversalStyler styler;

    public ReadingTrackerPanel(ActionListener setGoalAction, ActionListener logPagesAction,
                               ActionListener viewSummaryAction, ActionListener returnHomeAction) {
        this.styler = new UniversalStyler();
        initPanel(setGoalAction, logPagesAction, viewSummaryAction, returnHomeAction);
    }

    private void initPanel(ActionListener setGoalAction, ActionListener logPagesAction,
                           ActionListener viewSummaryAction, ActionListener returnHomeAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(setGoalAction, logPagesAction, viewSummaryAction, returnHomeAction),
                BorderLayout.CENTER);
    }

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

        styler.styleButton(setGoalButton);
        styler.styleButton(logPagesButton);
        styler.styleButton(viewSummaryButton);
        styler.styleButton(returnHomeButton);

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