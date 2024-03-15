package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LandingPageUI {
    private JFrame frame;
    private JButton loadButton;
    private JButton createButton;
    private String[] leftTitles = {
            "Your personal dynamic library",
            "Capture your reading experiences"
    };
    private String[] rightTitles = {
            "Personalized book recommendations",
            "Track and achieve your reading goals"
    };
    private String[] leftDescriptions = {
            "Easily manage your book collection with BookBuddy. Add new books, categorize them, and "
                    + "keep track of your reading journey. Your personal library, organized at your fingertips.",
            "Reflect on your reading experiences by jotting down thoughts, memorable quotes, and ratings for each "
                    + "book. Your reading journal makes it easy to revisit every reading moment."
    };
    private String[] rightDescriptions = {
            "Discover your next favorite read. Tell us your preferences and BookBuddy will recommend books from your "
                    + "collection you're bound to love. Uncover hidden gems in your own library.",
            "Set reading targets and monitor your progress with our Reading Tracker. Whether it's a daily page "
                    + "count or yearly book goal, stay motivated and watch your reading habit grow!"
    };

    // Constructor
    public LandingPageUI() {
        initUI();
    }

    private void initUI() {
        frame = createFrame("Welcome to BookBuddy");

        JPanel leftInfoPanel = createInfoPanel(leftTitles, leftDescriptions, new Color(211, 194, 247),
                new Color(111, 98, 140));
        JPanel rightInfoPanel = createInfoPanel(rightTitles, rightDescriptions, new Color(250, 243, 235),
                new Color(112, 105, 97));
        JPanel centerPanel = new JPanel(new BorderLayout());


        centerPanel.add(createWelcomeLabel(), BorderLayout.NORTH);
        centerPanel.add(createButtonPanel(), BorderLayout.CENTER);
        centerPanel.setBackground(new Color(26, 67, 76));

        frame.add(leftInfoPanel, BorderLayout.WEST);
        frame.add(rightInfoPanel, BorderLayout.EAST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(createFooterLabel(), BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JFrame createFrame(String title) {
        JFrame jframe = new JFrame(title);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(new BorderLayout());
        return jframe;
    }

    private JLabel createWelcomeLabel() {
        JLabel welcomeLabel = new JLabel("Welcome to BookBuddy", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBackground(new Color(26, 67, 76));
        welcomeLabel.setForeground(new Color(255, 244, 255));
        welcomeLabel.setOpaque(true);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return welcomeLabel;
    }

    private void addLoadButtonActionListener(JButton loadButton) {
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: add action for loading a saved bookshelf
            }
        });
    }

    private void addCreateButtonActionListener(JButton createButton) {
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: add action for creating a new bookshelf
            }
        });
    }

    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        Dimension buttonSize = new Dimension(271, 80);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        return button;
    }

//    private JPanel createButtonPanel() {
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
//        buttonPanel.setBackground(new Color(26, 67, 76));
//        buttonPanel.add(Box.createVerticalGlue());
//
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//        buttonPanel.add(Box.createVerticalGlue());
//
//        loadButton = createStyledButton("Load Saved Bookshelf",
//                new Color(194, 243, 78),
//                new Color(77, 99, 26));
//        createButton = createStyledButton("Create New Bookshelf",
//                new Color(96, 211, 133),
//                new Color(43, 104, 62));
//
//        buttonPanel.add(createButtonPanelWithPadding(loadButton));
//        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//        buttonPanel.add(createButtonPanelWithPadding(createButton));
//        buttonPanel.add(Box.createVerticalGlue());
//
//        addLoadButtonActionListener(loadButton);
//        addCreateButtonActionListener(createButton);
//
//        return buttonPanel;
//    }

//    private JPanel createButtonPanelWithPadding(JButton button) {
//        // container holds the button and applies left and right padding
//        JPanel buttonContainer = new JPanel(new BorderLayout());
//        buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.X_AXIS));
//        buttonContainer.setBackground(new Color(26, 67, 76));
//        buttonContainer.add(Box.createHorizontalGlue());
//        buttonContainer.add(button);
//        buttonContainer.add(Box.createHorizontalGlue());
//
//        // add top and bottom padding
//        JPanel paddedButtonPanel = new JPanel();
//        paddedButtonPanel.setLayout(new BoxLayout(paddedButtonPanel, BoxLayout.Y_AXIS));
//        paddedButtonPanel.setBackground(new Color(26, 67, 76));
//        paddedButtonPanel.add(Box.createVerticalGlue());
//        paddedButtonPanel.add(buttonContainer);
//        paddedButtonPanel.add(Box.createVerticalGlue());
//
//        return paddedButtonPanel;
//    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(26, 67, 76));

        loadButton = createStyledButton("Load Saved Bookshelf", new Color(194, 243, 78),
                new Color(77, 99, 26));
        createButton = createStyledButton("Create New Bookshelf", new Color(96, 211, 133),
                new Color(43, 104, 62));

        // Add the first button with padding
        buttonPanel.add(createButtonPanelWithPadding(loadButton));
        // Add a rigid area for a fixed space between the buttons, you can adjust the height as needed
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Adjust this value to change the space
        // Add the second button with padding
        buttonPanel.add(createButtonPanelWithPadding(createButton));

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListeners for buttons are added somewhere else in your code
        return buttonPanel;
    }



    private JPanel createButtonPanelWithPadding(JButton button) {
        // A container that centers the button horizontally
        JPanel horizontalCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        horizontalCenterPanel.setBackground(new Color(26, 67, 76)); // Background color
        horizontalCenterPanel.add(button);

        // This panel wraps the horizontal centering panel and adds top and bottom padding
        JPanel verticalPaddingPanel = new JPanel();
        verticalPaddingPanel.setLayout(new BoxLayout(verticalPaddingPanel, BoxLayout.Y_AXIS));
        verticalPaddingPanel.setBackground(new Color(26, 67, 76)); // Background color
        verticalPaddingPanel.add(Box.createVerticalGlue()); // Glue pushes components to the center
        verticalPaddingPanel.add(horizontalCenterPanel); // Add the button
        verticalPaddingPanel.add(Box.createVerticalGlue()); // More glue for symmetry

        return verticalPaddingPanel;
    }



    private JLabel createFooterLabel() {
        String text = "Welcome to BookBuddy, made by Belinda de Bruyn. As a passionate "
                + "reader and a software developer, I designed BookBuddy to address a challenge I faced: managing and "
                + "enjoying my ever-growing book collection. I hope that my app helps fellow book lovers organize "
                + "their collections, track reading habits, and discover new favorites. Enjoy and happy reading!\n"
                + "\n" + "— Belinda :)";
        String styledText = String.format("<html><div style='width:%dpx;'>%s</div></html>", 750, text);

        JLabel footerLabel = new JLabel(styledText, SwingConstants.CENTER);

        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        footerLabel.setBackground(new Color(39, 94, 105));
        footerLabel.setForeground(new Color(157, 214, 226));
        footerLabel.setOpaque(true);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return footerLabel;
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(font);
        label.setForeground(color);
        // Use simple HTML for line wrapping and alignment
        label.setText(String.format("<html><div style='width:%dpx;'>%s</div></html>", 230, text));
        return label;
    }


    private JPanel createFeaturePanel(String titleText, String descText, Color bgColor, Color fgColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(bgColor);

        JLabel titleLabel = createLabel(titleText, new Font("Arial", Font.BOLD, 16), fgColor);
        JLabel descLabel = createLabel(descText, new Font("Arial", Font.PLAIN, 12), fgColor);

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Adjust spacing as needed
        panel.add(descLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        return panel;
    }


    private JPanel createInfoPanel(String[] titles, String[] descriptions, Color bgColor, Color fgColor) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(bgColor);

        for (int i = 0; i < titles.length; i++) {
            infoPanel.add(createFeaturePanel(titles[i], descriptions[i], bgColor, fgColor));
        }
        return infoPanel;
    }

    // Main method to run UI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LandingPageUI();
            }
        });
    }
}