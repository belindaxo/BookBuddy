package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.UIManager;

// Represents the universal styling for the BookBuddy application
public class UniversalStyler {

    // MODIFIES: this
    // EFFECTS: styles the title and subtitle labels
    public void styleTitleLabel(JLabel titleLabel, JLabel subtitleLabel) {
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(255, 254, 255));

        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 18));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setForeground(new Color(255, 254, 255));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
    }

    // MODIFIES: this
    // EFFECTS: styles the buttons
    public void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(194, 243, 78));
        button.setForeground(new Color(77, 99, 26));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
    }

    // MODIFIES: this
    // EFFECTS: styles all buttons in the panel
    public void styleAllButtons(JButton... buttons) {
        for (JButton button : buttons) {
            styleButton(button);
        }
    }

    // EFFECTS: creates constraints for buttons
    public GridBagConstraints createButtonConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        return gbc;
    }

    // MODIFIES: this
    // EFFECTS: styles the progress bar
    public void styleProgressBar(JProgressBar progressBar) {
        progressBar.setBackground(new Color(194, 243, 78));
        progressBar.setForeground(new Color(77, 99, 26));
        progressBar.setFont(new Font("Arial", Font.BOLD, 16));
        UIManager.put("ProgressBar.selectionBackground", new Color(77, 99, 26));
        UIManager.put("ProgressBar.selectionForeground", new Color(194, 243, 78));
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        progressBar.setBorderPainted(false);
        progressBar.setPreferredSize(new Dimension(300, 40));
    }

}