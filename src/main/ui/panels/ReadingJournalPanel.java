package ui.panels;

import ui.UniversalStyler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the reading journal panel of the application
public class ReadingJournalPanel extends JPanel {
    private final UniversalStyler styler;

    // EFFECTS: constructs the reading journal panel with buttons to edit an entry, view journal, and return to home
    public ReadingJournalPanel(ActionListener editEntryAction, ActionListener viewJournalAction,
                               ActionListener returnHomeAction) {
        this.styler = new UniversalStyler();
        initPanel(editEntryAction, viewJournalAction, returnHomeAction);
    }

    // MODIFIES: this
    // EFFECTS: initializes the reading journal panel with title and buttons
    private void initPanel(ActionListener editEntryAction, ActionListener viewJournalAction,
                           ActionListener returnHomeAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(editEntryAction, viewJournalAction, returnHomeAction), BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with the title and subtitle of the panel
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        JLabel titleLabel = new JLabel("Reading Journal");
        JLabel subtitleLabel = new JLabel("What would you like to do today?");
        styler.styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel with buttons to edit an entry, view journal, and return to home
    private JPanel createButtonPanel(ActionListener editEntryAction, ActionListener viewJournalAction,
                                     ActionListener returnHomeAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));
        GridBagConstraints gbc = styler.createButtonConstraints();

        JButton editEntryButton = new JButton("Edit an entry");
        JButton viewJournalButton = new JButton("View journal");
        JButton returnHomeButton = new JButton("Return to home");

        styler.styleAllButtons(editEntryButton, viewJournalButton, returnHomeButton);

        editEntryButton.addActionListener(editEntryAction);
        viewJournalButton.addActionListener(viewJournalAction);
        returnHomeButton.addActionListener(returnHomeAction);

        buttonPanel.add(editEntryButton, gbc);
        buttonPanel.add(viewJournalButton, gbc);
        buttonPanel.add(returnHomeButton, gbc);

        return buttonPanel;
    }
}