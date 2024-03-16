package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
    private MainMenuPanel mainMenuPanel;

    public MainFrame() {
        setTitle("BookBuddy");
        setSize(new Dimension(400, 250));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mainMenuPanel = new MainMenuPanel(
                this::onLoadButtonClicked,
                this::onCreateButtonClicked
        );
        setContentPane(mainMenuPanel);
    }

    private void onLoadButtonClicked(ActionEvent e) {
        //TODO: load bookshelf action
        System.out.println("Load button clicked");
    }

    private void onCreateButtonClicked(ActionEvent e) {
        //TODO: create bookshelf action
        System.out.println("Create button clicked");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}