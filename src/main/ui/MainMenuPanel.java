package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private JButton loadButton;
    private JButton createButton;
    private ActionListener loadAction;
    private ActionListener createAction;

    public MainMenuPanel(ActionListener loadAction, ActionListener createAction) {
        this.loadAction = loadAction;
        this.createAction = createAction;
        initPanel();
    }

    private void initPanel() {
        this.setLayout(new BorderLayout());
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(), BorderLayout.CENTER);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        JLabel titleLabel = new JLabel("Welcome to BookBuddy!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(26, 67, 76));
        titleLabel.setForeground(new Color(255, 254, 255));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        return titlePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);
        buttonPanel.setBackground(new Color(26, 67, 76));

        createButtons();

        buttonPanel.add(loadButton, gbc);
        buttonPanel.add(createButton, gbc);

        return buttonPanel;
    }

    private void createButtons() {
        loadButton = new JButton("Load Saved Bookshelf");
        createButton = new JButton("Create New Bookshelf");

        loadButton.addActionListener(loadAction);
        createButton.addActionListener(createAction);

        styleButton(loadButton);
        styleButton(createButton);
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