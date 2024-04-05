package ui.panels;

import ui.UniversalStyler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents the book rec panel with options to get book recommendations based on genre, page count, or randomly
public class BookRecPanel extends JPanel {
    private final UniversalStyler styler;


    public BookRecPanel(ActionListener recByGenreAction, ActionListener recByPageCount,
                        ActionListener randomRecAction, ActionListener returnHomeAction) {
        this.styler = new UniversalStyler();
        initPanel(recByGenreAction, recByPageCount, randomRecAction, returnHomeAction);
    }

    private void initPanel(ActionListener recByGenreAction, ActionListener recByPageCount,
                           ActionListener randomRecAction, ActionListener returnHomeAction) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(600, 400));
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(recByGenreAction, recByPageCount, randomRecAction, returnHomeAction),
                BorderLayout.CENTER);
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        titlePanel.setBackground(new Color(26, 67, 76));

        JLabel titleLabel = new JLabel("Book Recommendations");
        JLabel subtitleLabel = new JLabel("Get recommendation based on:");
        styler.styleTitleLabel(titleLabel, subtitleLabel);

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        return titlePanel;
    }

    private JPanel createButtonPanel(ActionListener recByGenreAction, ActionListener recByPageCount,
                                     ActionListener randomRecAction, ActionListener returnHomeAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(26, 67, 76));
        GridBagConstraints gbc = styler.createButtonConstraints();

        JButton recByGenreButton = new JButton("Recommend by genre");
        JButton recByPageCountButton = new JButton("Recommend by book length");
        JButton randomRecButton = new JButton("Surprise me!");
        JButton returnHomeButton = new JButton("Return to home");

        styler.styleAllButtons(recByGenreButton, recByPageCountButton, randomRecButton, returnHomeButton);

        recByGenreButton.addActionListener(recByGenreAction);
        recByPageCountButton.addActionListener(recByPageCount);
        randomRecButton.addActionListener(randomRecAction);
        returnHomeButton.addActionListener(returnHomeAction);

        buttonPanel.add(recByGenreButton, gbc);
        buttonPanel.add(recByPageCountButton, gbc);
        buttonPanel.add(randomRecButton, gbc);
        buttonPanel.add(returnHomeButton, gbc);

        return buttonPanel;
    }
}