package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookshelfPanel extends JPanel {
    private JButton addButton;
    private JButton viewButton;
    private JButton rateButton;
    private JButton updateButton;

    public BookshelfPanel(ActionListener addAction, ActionListener viewAction,
                          ActionListener rateAction, ActionListener updateAction) {
        initPanel(addAction, viewAction, rateAction, updateAction);
    }

    private void initPanel(ActionListener addAction, ActionListener viewAction,
                          ActionListener rateAction, ActionListener updateAction) {
        this.setLayout(new BorderLayout());
        this.add(createTitlePanel(), BorderLayout.NORTH);
        this.add(createButtonPanel(addAction, viewAction, rateAction, updateAction), BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(600, 400));
    }

    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Virtual Bookshelf");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        return titlePanel;
    }

    private JPanel createButtonPanel(ActionListener addAction,
                                     ActionListener viewAction,
                                     ActionListener rateAction,
                                     ActionListener updateAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        addButton = new JButton("Add a book");
        viewButton = new JButton("View bookshelf");
        rateButton = new JButton("Rate a book");
        updateButton = new JButton("Update book status");

        addButton.addActionListener(addAction);
        viewButton.addActionListener(viewAction);
        rateButton.addActionListener(rateAction);
        updateButton.addActionListener(updateAction);

        styleButton(addButton);
        styleButton(viewButton);
        styleButton(rateButton);
        styleButton(updateButton);

        buttonPanel.add(addButton, gbc);
        buttonPanel.add(viewButton, gbc);
        buttonPanel.add(rateButton, gbc);

        buttonPanel.add(updateButton, gbc);

        return buttonPanel;
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setBackground(new Color(255, 254, 255));
        button.setForeground(new Color(26, 67, 76));
    }
}