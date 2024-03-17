package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePagePanel extends JPanel {
    private JButton accessBookshelfButton;
    private JButton openReadingTrackerButton;
    private JButton accessReadingJournalButton;
    private JButton getBookRecommendationButton;

    public HomePagePanel(ActionListener accessBookshelfAction,
                         ActionListener openReadingTrackerAction,
                         ActionListener accessReadingJournalAction,
                         ActionListener getBookRecommendationAction) {
        initPanel(accessBookshelfAction,
                openReadingTrackerAction,
                accessReadingJournalAction,
                getBookRecommendationAction);
    }

    private void initPanel(ActionListener accessBookshelfAction,
                           ActionListener openReadingTrackerAction,
                           ActionListener accessReadingJournalAction,
                           ActionListener getBookRecommendationAction) {
        this.setLayout(new BorderLayout());
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(accessBookshelfAction,
                openReadingTrackerAction,
                accessReadingJournalAction,
                getBookRecommendationAction), BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(600, 400));
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("BookBuddy");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(255, 254, 255));

        JLabel subtitleLabel = new JLabel("What would you like to do today?");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setForeground(new Color(255, 254, 255));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        return titlePanel;
    }

    private JPanel createButtonPanel(ActionListener accessBookshelfAction,
                                     ActionListener openReadingTrackerAction,
                                     ActionListener accessReadingJournalAction,
                                     ActionListener getBookRecommendationAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        buttonPanel.setBackground(new Color(26, 67, 76));

        createButtons(accessBookshelfAction,
                openReadingTrackerAction,
                accessReadingJournalAction,
                getBookRecommendationAction);

        buttonPanel.add(accessBookshelfButton, gbc);
        buttonPanel.add(openReadingTrackerButton, gbc);
        buttonPanel.add(accessReadingJournalButton, gbc);
        buttonPanel.add(getBookRecommendationButton, gbc);

        return buttonPanel;
    }

    private void createButtons(ActionListener accessBookshelfAction,
                               ActionListener openReadingTrackerAction,
                               ActionListener accessReadingJournalAction,
                               ActionListener getBookRecommendationAction) {
        accessBookshelfButton = new JButton("Access your virtual bookshelf");
        openReadingTrackerButton = new JButton("Open reading tracker");
        accessReadingJournalButton = new JButton("Access your reading journal");
        getBookRecommendationButton = new JButton("Get a book recommendation");

        accessBookshelfButton.addActionListener(accessBookshelfAction);
        openReadingTrackerButton.addActionListener(openReadingTrackerAction);
        accessReadingJournalButton.addActionListener(accessReadingJournalAction);
        getBookRecommendationButton.addActionListener(getBookRecommendationAction);

        styleButton(accessBookshelfButton);
        styleButton(openReadingTrackerButton);
        styleButton(accessReadingJournalButton);
        styleButton(getBookRecommendationButton);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(194, 243, 78));
        button.setForeground(new Color(77, 99, 26));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
    }
}