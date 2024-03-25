package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the bookshelf panel of the BookBuddy application
public class BookshelfPanel extends JPanel {
    private final UniversalStyler styler;

    // EFFECTS: constructs the bookshelf panel with buttons to add, view, rate, and update books
    public BookshelfPanel(ActionListener addAction, ActionListener viewAction,
                          ActionListener rateAction, ActionListener updateAction, ActionListener returnHomeAction) {
        this.styler = new UniversalStyler();
        initPanel(addAction, viewAction, rateAction, updateAction, returnHomeAction);
    }

    // MODIFIES: this
    // EFFECTS: initializes the bookshelf panel with title and buttons
    private void initPanel(ActionListener addAction, ActionListener viewAction,
                          ActionListener rateAction, ActionListener updateAction, ActionListener returnHomeAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(addAction, viewAction, rateAction, updateAction, returnHomeAction),
                BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with the title and subtitle of the panel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        JLabel titleLabel = new JLabel("Virtual Bookshelf");
        JLabel subtitleLabel = new JLabel("What would you like to do today?");
        styler.styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to add, view, rate, and update books
    private JPanel createButtonPanel(ActionListener addAction, ActionListener viewAction, ActionListener rateAction,
                                     ActionListener updateAction, ActionListener returnHomeAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));
        GridBagConstraints gbc = styler.createButtonConstraints();

        JButton addButton = new JButton("Add a book");
        JButton viewButton = new JButton("View bookshelf");
        JButton rateButton = new JButton("Rate a book");
        JButton updateButton = new JButton("Update book status");
        JButton returnHomeButton = new JButton("Return to home");

        styler.styleButton(addButton);
        styler.styleButton(viewButton);
        styler.styleButton(rateButton);
        styler.styleButton(updateButton);
        styler.styleButton(returnHomeButton);

        addButton.addActionListener(addAction);
        viewButton.addActionListener(viewAction);
        rateButton.addActionListener(rateAction);
        updateButton.addActionListener(updateAction);
        returnHomeButton.addActionListener(returnHomeAction);

        buttonPanel.add(addButton, gbc);
        buttonPanel.add(viewButton, gbc);
        buttonPanel.add(rateButton, gbc);
        buttonPanel.add(updateButton, gbc);
        buttonPanel.add(returnHomeButton, gbc);

        return buttonPanel;
    }
}