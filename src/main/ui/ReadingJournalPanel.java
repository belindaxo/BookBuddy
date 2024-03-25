package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReadingJournalPanel extends JPanel {
    private final UniversalStyler styler;

    public ReadingJournalPanel(ActionListener editEntryAction, ActionListener viewJournalAction) {
        this.styler = new UniversalStyler();
        initPanel(editEntryAction, viewJournalAction);
    }

    private void initPanel(ActionListener editEntryAction, ActionListener viewJournalAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(editEntryAction, viewJournalAction), BorderLayout.CENTER);
    }

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

    private JPanel createButtonPanel(ActionListener editEntryAction, ActionListener viewJournalAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));
        GridBagConstraints gbc = styler.createButtonConstraints();

        JButton editEntryButton = new JButton("Edit an entry");
        JButton viewJournalButton = new JButton("View journal");

        styler.styleButton(editEntryButton);
        styler.styleButton(viewJournalButton);

        editEntryButton.addActionListener(editEntryAction);
        viewJournalButton.addActionListener(viewJournalAction);

        buttonPanel.add(editEntryButton, gbc);
        buttonPanel.add(viewJournalButton, gbc);

        return buttonPanel;
    }
}