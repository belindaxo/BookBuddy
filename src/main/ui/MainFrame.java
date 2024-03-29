package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Represents the main frame of the BookBuddy application
public class MainFrame extends JFrame {
    private VirtualBookshelf bookshelf;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/bookshelf.json";
    private static final String JSON_STORE_NEW = "./data/newBookshelf.json";
    private final UniversalStyler styler;

    // EFFECTS: constructs the main frame of the application
    public MainFrame() {
        createMainFrame();
        this.bookshelf = new VirtualBookshelf();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.styler = new UniversalStyler();
        setContentToMainMenuPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates the main frame
    private void createMainFrame() {
        setTitle("BookBuddy");
        setSize(new Dimension(400, 250));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleSaveOptions();
            }
        });
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu panel
    private MainMenuPanel createMainMenuPanel() {
        return new MainMenuPanel(
                this::onLoadButtonClicked,
                this::onCreateButtonClicked
        );
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the main menu panel
    private void setContentToMainMenuPanel() {
        setContentPane(createMainMenuPanel());
        pack();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: loads bookshelf from file
    private void onLoadButtonClicked(ActionEvent e) {
        try {
            bookshelf = jsonReader.read();
            System.out.println("Loading bookshelf: " + JSON_STORE);
            setContentToHomePanel();
        } catch (IOException ex) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new bookshelf
    private void onCreateButtonClicked(ActionEvent e) {
        bookshelf = new VirtualBookshelf();
        jsonWriter = new JsonWriter(JSON_STORE_NEW);
        jsonReader = new JsonReader(JSON_STORE_NEW);
        System.out.println("New bookshelf created");
        setContentToHomePanel();
    }

    // PANEL GETTERS + SETTERS:
    // 1. HomePanel
    // MODIFIES: this
    // EFFECTS: creates a new home panel
    private HomePanel createHomePanel() {
        return new HomePanel(
                this::accessBookshelf,
                this::accessReadingTracker,
                this::accessReadingJournal,
                this::getBookRecommendation
        );
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the home panel
    private void setContentToHomePanel() {
        setContentPane(createHomePanel());
        pack();
        revalidate();
    }

    // 2. BookshelfPanel
    // MODIFIES: this
    // EFFECTS: creates a new bookshelf panel
    private BookshelfPanel createBookshelfPanel() {
        return new BookshelfPanel(
                this::addAction,
                this::viewAction,
                this::rateAction,
                this::updateAction,
                this::returnHomeAction
        );
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the bookshelf panel
    private void setContentToBookshelfPanel() {
        setContentPane(createBookshelfPanel());
        pack();
        revalidate();
    }

    // 3. ReadingTrackerPanel
    // MODIFIES: this
    // EFFECTS: creates a new reading tracker panel
    private ReadingTrackerPanel createReadingTrackerPanel() {
        return new ReadingTrackerPanel(
                this::setGoalAction,
                this::logPagesAction,
                this::viewSummaryAction,
                this::returnHomeAction
        );
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the reading tracker panel
    private void setContentToTrackerPanel() {
        setContentPane(createReadingTrackerPanel());
        pack();
        revalidate();
    }

    // 4. ReadingJournalPanel
    // MODIFIES: this
    // EFFECTS: creates a new reading journal panel
    private ReadingJournalPanel createReadingJournalPanel() {
        return new ReadingJournalPanel(
                this::editEntryAction,
                this::viewJournalAction,
                this::returnHomeAction
        );
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the reading journal panel
    private void setContentToJournalPanel() {
        setContentPane(createReadingJournalPanel());
        pack();
        revalidate();
    }

    // 5. BookRecPanel
    // MODIFIES: this
    // EFFECTS: creates a new book recommendation panel
    private BookRecPanel createBookRecPanel() {
        return new BookRecPanel(
                this::recByGenreAction,
                this::recByPageCountAction,
                this::randomRecAction,
                this::returnHomeAction
        );
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the book recommendation panel
    private void setContentToBookRecPanel() {
        setContentPane(createBookRecPanel());
        pack();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: accesses the bookshelf
    private void accessBookshelf(ActionEvent e) {
        System.out.println("Accessing bookshelf...");
        setContentToBookshelfPanel();
    }

    // MODIFIES: this
    // EFFECTS: adds a book to the bookshelf
    private void addAction(ActionEvent e) {
        System.out.println("Adding a book...");
        boolean continueAdding = true;
        while (continueAdding) {
            while (continueAdding) {
                Book newBook = getBookDetails();
                addBookToBookshelf(newBook);
                int option = getNextAddAction(newBook);
                continueAdding = handleNextAddAction(option);
            }
        }
    }

    // EFFECTS: gets the details of a book
    private Book getBookDetails() {
        String title = JOptionPane.showInputDialog(this, "Enter the title of the book:");
        String author = JOptionPane.showInputDialog(this, "Enter the author of the book:");
        String genre = JOptionPane.showInputDialog(this, "Enter the genre of the book:");
        int pageCount = Integer.parseInt(
                JOptionPane.showInputDialog(this, "Enter the page count of the book:"));

        return new Book(title, author, genre, pageCount);
    }

    // MODIFIES: this
    // EFFECTS: adds a book to the bookshelf
    private void addBookToBookshelf(Book newBook) {
        bookshelf.addBook(newBook);
    }

    // EFFECTS: gets the next action to be performed
    private int getNextAddAction(Book newBook) {
        String confirmation = newBook.getTitle() + " by " + newBook.getAuthor()
                + " has been added to your bookshelf!";
        return JOptionPane.showOptionDialog(this,
                confirmation + "\nWhat would you like to do next?",
                "Next Action",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Add another book", "Return to bookshelf", "Return to home"},
                null);
    }

    // EFFECTS: handles the next action selected
    private boolean handleNextAddAction(int option) {
        switch (option) {
            case 0:
                return true;
            case 1:
                setContentToBookshelfPanel();
                return false;
            case 2:
                setContentToHomePanel();
                return false;
            default:
                return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the save action selected
    public void handleSaveOptions() {
        int saveOptions = getSaveOptions();
        switch (saveOptions) {
            case 0:
                saveChanges();
                break;
            case 1:
                deleteChanges();
                break;
            default:
                break;
        }
    }

    // EFFECTS: gets the save options
    private int getSaveOptions() {
        return JOptionPane.showOptionDialog(null,
                "Would you like to save changes to your bookshelf?",
                "Save or Delete Changes",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Save changes", "Discard changes", "Cancel"},
                null);
    }

    // MODIFIES: this
    // EFFECTS: saves changes to the bookshelf
    private void saveChanges() {
        try {
            System.out.println("Saving changes...");
            jsonWriter.open();
            jsonWriter.write(bookshelf);
            jsonWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Unable to save changes to bookshelf");
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes changes to the bookshelf
    private void deleteChanges() {
        try {
            System.out.println("Deleting changes...");
            bookshelf = jsonReader.read();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: Unable to delete changes to bookshelf");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the bookshelf as a JTable
    private void viewAction(ActionEvent e) {
        System.out.println("Viewing bookshelf...");
        DefaultTableModel tableModel = createTableModel();
        JTable table = createTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton deleteButton = createDeleteButton(table, tableModel);
        JButton returnToBookshelfButton = createReturnToBookshelfButton();
        JButton returnToHomeButton = createReturnToHomeButton();
        JPanel panel = createPanel(scrollPane, deleteButton, returnToBookshelfButton, returnToHomeButton);
        panel.add(createFilterPanel(tableModel), BorderLayout.NORTH);
        setContentPane(panel);
        pack();
        revalidate();
    }

    // EFFECTS: creates a table model
    private DefaultTableModel createTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{
                "Title", "Author", "Genre", "Page Count"}, 0);

        for (Book book : bookshelf.getBooks()) {
            tableModel.addRow(new Object[]{
                    book.getTitle(), book.getAuthor(), book.getGenre(), book.getPageCount()});
        }

        return tableModel;
    }

    // EFFECTS: creates a table
    private JTable createTable(DefaultTableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.getModel().addTableModelListener(createTableModelListener(table, tableModel));
        return table;
    }

    // EFFECTS: creates a table model listener
    private TableModelListener createTableModelListener(JTable table, DefaultTableModel tableModel) {
        return e -> {
            int row = e.getFirstRow();
            int col = e.getColumn();
            Book book = bookshelf.getBooks().get(row);
            switch (col) {
                case 0:
                    book.setTitle((String) tableModel.getValueAt(row, col));
                    break;
                case 1:
                    book.setAuthor((String) tableModel.getValueAt(row, col));
                    break;
                case 2:
                    book.setGenre((String) tableModel.getValueAt(row, col));
                    break;
                case 3:
                    book.setPageCount(Integer.parseInt((String) tableModel.getValueAt(row, col)));
                    break;
            }
        };
    }

    // EFFECTS: creates a scroll pane for the table
    private JScrollPane createScrollPane(JTable table) {
        return new JScrollPane(table);
    }

    // EFFECTS: creates a delete button for the table
    private JButton createDeleteButton(JTable table, DefaultTableModel tableModel) {
        JButton deleteButton = new JButton("Delete selected book");
        styler.styleButton(deleteButton);
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                tableModel.removeRow(row);
                bookshelf.removeBook(bookshelf.getBooks().get(row));
            }
        });

        return deleteButton;
    }

    // EFFECTS: creates a button to return to the bookshelf panel
    private JButton createReturnToBookshelfButton() {
        JButton returnToBookshelfButton = new JButton("Return to bookshelf");
        styler.styleButton(returnToBookshelfButton);
        returnToBookshelfButton.addActionListener(e -> setContentToBookshelfPanel());
        return returnToBookshelfButton;
    }

    // EFFECTS: creates a button to return to the home panel
    private JButton createReturnToHomeButton() {
        JButton returnToHomeButton = new JButton("Return to home");
        styler.styleButton(returnToHomeButton);
        returnToHomeButton.addActionListener(e -> setContentToHomePanel());
        return returnToHomeButton;
    }

    // EFFECTS: creates a panel for the table
    private JPanel createPanel(JScrollPane scrollPane, JButton deleteButton, JButton returnToBookshelfButton,
                               JButton returnToHomeButton) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionButtonPanel = new JPanel();
        actionButtonPanel.add(deleteButton);
        actionButtonPanel.add(returnToBookshelfButton);
        actionButtonPanel.add(returnToHomeButton);

        panel.add(actionButtonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // EFFECTS: creates a filter panel for the table
    private JPanel createFilterPanel(DefaultTableModel tableModel) {
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());

        JComboBox<String> authorBox = createComboBox(getUniqueAuthors());
        JComboBox<String> genreBox = createComboBox(getUniqueGenres());
        JComboBox<String> lengthBox = createComboBox(new String[]{"Short", "Medium", "Long"});
        JComboBox<String> statusBox = createComboBox(new String[]{"Read", "In Progress", "Unread"});

        JButton applyFiltersButton = new JButton("Apply Filters");
        applyFiltersButton.addActionListener(
                e -> applyFilters(authorBox, genreBox, lengthBox, statusBox, tableModel));

        filterPanel.add(new JLabel("Author:"));
        filterPanel.add(authorBox);
        filterPanel.add(new JLabel("Genre:"));
        filterPanel.add(genreBox);
        filterPanel.add(new JLabel("Length:"));
        filterPanel.add(lengthBox);
        filterPanel.add(new JLabel("Status:"));
        filterPanel.add(statusBox);
        filterPanel.add(applyFiltersButton);

        return filterPanel;
    }

    // EFFECTS: creates filter option boxes
    private JComboBox<String> createComboBox(String[] options) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.insertItemAt("Any", 0);
        comboBox.setSelectedIndex(0);
        return comboBox;
    }

    // MODIFIES: this
    // EFFECTS: applies filters to the bookshelf
    private void applyFilters(JComboBox<String> authorBox, JComboBox<String> genreBox, JComboBox<String> lengthBox,
                              JComboBox<String> statusBox, DefaultTableModel tableModel) {
        String author = (String) authorBox.getSelectedItem();
        String genre = (String) genreBox.getSelectedItem();
        String length = (String) lengthBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();

        ArrayList<Book> filteredBooks = new ArrayList<>(bookshelf.getBooks());

        if (author != null && !author.equals("Any")) {
            filteredBooks.retainAll(bookshelf.getBooksByAuthor(author));
        }
        if (genre != null && !genre.equals("Any")) {
            filteredBooks.retainAll(bookshelf.getBooksByGenre(genre));
        }
        if (length != null && !length.equals("Any")) {
            filteredBooks.retainAll(bookshelf.getBooksByPageCount(length));
        }
        if (status != null && !status.equals("Any")) {
            filteredBooks.retainAll(bookshelf.getBooksByStatus(status));
        }

        tableModel.setRowCount(0);
        for (Book book : filteredBooks) {
            tableModel.addRow(new Object[]{
                    book.getTitle(), book.getAuthor(), book.getGenre(), book.getPageCount()});
        }
    }

    // EFFECTS: returns unique authors from the bookshelf
    private String[] getUniqueAuthors() {
        return bookshelf.getBooks().stream().map(Book::getAuthor).distinct().toArray(String[]::new);
    }

    // EFFECTS: returns unique genres from the bookshelf
    private String[] getUniqueGenres() {
        return bookshelf.getBooks().stream().map(Book::getGenre).distinct().toArray(String[]::new);
    }

    // MODIFIES: this
    // EFFECTS: rates a book
    private void rateAction(ActionEvent e) {
        System.out.println("Rating a book...");
        boolean continueRating = true;
        while (continueRating) {
            Book selectedBook = getSelectedBookToRate();
            if (selectedBook != null) {
                int rating = getRating();
                if (rating != -1) {
                    updateRating(selectedBook, rating);
                    int nextAction = getNextRatingAction(selectedBook);
                    continueRating = handleNextAddAction(nextAction);
                }
            } else {
                System.out.println("No book selected");
                continueRating = false;
            }
        }
    }

    // EFFECTS: gets the selected book
    private Book getSelectedBookToRate() {
        ArrayList<Book> books = bookshelf.getBooks();
        String[] bookDesc = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            bookDesc[i] = book.getTitle() + " by " + book.getAuthor() + ": " + displayRating(book.getRating());
        }
        String selectedBook = (String) JOptionPane.showInputDialog(
                this, "Select a book to rate:", "Rate a Book",
                JOptionPane.QUESTION_MESSAGE, null, bookDesc, bookDesc[0]);

        if (selectedBook != null) {
            for (Book book : books) {
                if (selectedBook.contains(book.getTitle()) && selectedBook.contains(book.getAuthor())) {
                    return book;
                }
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: updates the rating of a book
    private void updateRating(Book selectedBook, int rating) {
        Rating bookRating;
        switch (rating) {
            case 1:
                bookRating = Rating.ONE_STAR;
                break;
            case 2:
                bookRating = Rating.TWO_STARS;
                break;
            case 3:
                bookRating = Rating.THREE_STARS;
                break;
            case 4:
                bookRating = Rating.FOUR_STARS;
                break;
            case 5:
                bookRating = Rating.FIVE_STARS;
                break;
            default:
                bookRating = Rating.UNRATED;
        }
        selectedBook.setRating(bookRating);
    }

    // EFFECTS: displays the rating of a book using stars
    private String displayRating(Rating rating) {
        switch (rating) {
            case ONE_STAR:
                return "* - - - -";
            case TWO_STARS:
                return "* * - - -";
            case THREE_STARS:
                return "* * * -";
            case FOUR_STARS:
                return "* * * * -";
            case FIVE_STARS:
                return "* * * * *";
            default:
                return "- - - - -";
        }
    }

    // EFFECTS: gets the rating for a book
    private int getRating() {
        String rating = (String) JOptionPane.showInputDialog(this,
                "Select a rating for the book:", "Rate a Book", JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"1", "2", "3", "4", "5"}, "1");
        return rating != null ? Integer.parseInt(rating) : -1;
    }

    // EFFECTS: gets the next action to be performed
    private int getNextRatingAction(Book selectedBook) {
        String confirmation = "Rating for " + selectedBook.getTitle()
                + " has been updated to: " + displayRating(selectedBook.getRating());
        return JOptionPane.showOptionDialog(this,
                confirmation + "\nWhat would you like to do next?", "Next Action",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Add another rating", "Return to bookshelf", "Return to home"}, null);
    }

    // MODIFIES: this
    // EFFECTS: updates the status of a book
    private void updateAction(ActionEvent e) {
        System.out.println("Updating book status...");
        boolean continueUpdating = true;
        while (continueUpdating) {
            Book selectedBook = getSelectedBook();
            if (selectedBook != null) {
                String status = getStatus();
                if (status != null) {
                    updateBookStatus(selectedBook, status);
                    int nextAction = getNextStatusAction(selectedBook);
                    continueUpdating = handleNextAddAction(nextAction);
                }
            } else {
                System.out.println("No book selected");
                continueUpdating = false;
            }
        }
    }

    // EFFECTS: gets the selected book
    private Book getSelectedBook() {
        ArrayList<Book> books = bookshelf.getBooks();
        String[] bookDesc = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            bookDesc[i] = book.getTitle() + " by " + book.getAuthor() + ": " + book.getStatus();
        }
        String selectedBook = (String) JOptionPane.showInputDialog(
                this, "Select a book:", "Update Book Status",
                JOptionPane.QUESTION_MESSAGE, null, bookDesc, bookDesc[0]);

        if (selectedBook != null) {
            for (Book book : books) {
                if (selectedBook.contains(book.getTitle()) && selectedBook.contains(book.getAuthor())) {
                    return book;
                }
            }
        }
        return null;
    }

    // EFFECTS: gets the status for a book
    private String getStatus() {
        String[] options = {"Read", "In Progress", "Unread"};
        return (String) JOptionPane.showInputDialog(this,
                "Select a status:", "Update Book Status",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    // MODIFIES: this
    // EFFECTS: updates the status of the book
    private void updateBookStatus(Book book, String status) {
        book.setStatus(status);
    }

    // EFFECTS: gets the next status action to be performed
    private int getNextStatusAction(Book selectedBook) {
        String confirmation = "Status for " + selectedBook.getTitle() + " has been updated to: "
                + selectedBook.getStatus();
        return JOptionPane.showOptionDialog(this,
                confirmation + "\nWhat would you like to do next?", "Next Action",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Update another book's status", "Return to bookshelf", "Return to home"}, null);
    }

    // MODIFIES: this
    // EFFECTS: opens the reading tracker
    private void accessReadingTracker(ActionEvent e) {
        System.out.println("Opening reading tracker...");
        setContentToTrackerPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets the reading goal
    private void setGoalAction(ActionEvent e) {
        System.out.println("Setting reading goal...");
        String goalInput = JOptionPane.showInputDialog(this,
                "Enter your reading goal (number of pages): "
                        + "\n** note that this will overwrite any existing goal and pages logged **");
        if (goalInput != null && !goalInput.isEmpty()) {
            try {
                int goal = Integer.parseInt(goalInput);
                bookshelf.setReadingGoal(goal);
                bookshelf.resetReadingProgress();
                JOptionPane.showMessageDialog(this, "Reading goal set to: " + goal + " pages");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: logs the number of pages read
    private void logPagesAction(ActionEvent e) {
        System.out.println("Logging pages read...");
        String logInput = JOptionPane.showInputDialog(this,
                "Enter the number of pages read:");
        if (logInput != null && !logInput.isEmpty()) {
            try {
                int pages = Integer.parseInt(logInput);
                bookshelf.addPagesRead(pages);
                JOptionPane.showMessageDialog(this, "Pages read logged: " + pages + " pages");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        }
    }

//    // EFFECTS: views the goal summary
//    private void viewSummaryAction(ActionEvent e) {
//        String goalSummary = bookshelf.getGoalSummary();
//        JOptionPane.showMessageDialog(this, goalSummary);
//    }


    // MODIFIES: this
    // EFFECTS: creates and displays a panel showing the reading goal summary and progress
    private void viewSummaryAction(ActionEvent e) {
        setContentToGoalSummaryPanel();
    }

    // MODIFIES: this
// EFFECTS: sets the content to the goal summary panel
    private void setContentToGoalSummaryPanel() {
        JPanel goalSummaryPanel = createGoalSummaryPanel();
        setContentPane(goalSummaryPanel);
        pack();
        revalidate();
    }

    // EFFECTS: creates the goal summary panel
    private JPanel createGoalSummaryPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(350, 250));
        String totalPagesRead = "Total pages read: " + bookshelf.getTotalPagesRead();
        String pagesLeft = "Pages left: " + bookshelf.getPagesLeft();
        String goal = "Reading goal: " + bookshelf.getReadingGoal() + " pages";

        // Goal Summary
        String goalSummaryHtml = "<html><div style='padding: 5px;'>"
                + totalPagesRead + "<br>"
                + pagesLeft + "<br>"
                + goal
                + "</div></html>";
        JLabel goalSummaryLabel = new JLabel(goalSummaryHtml);
        goalSummaryLabel.setForeground(Color.WHITE);
        goalSummaryLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        textPanel.setBackground(new Color(26, 67, 76));
        textPanel.add(goalSummaryLabel);
        mainPanel.add(textPanel);
        mainPanel.setBackground(new Color(26, 67, 76));

        JProgressBar progressBar = new JProgressBar(0, bookshelf.getReadingGoal());
        progressBar.setValue(bookshelf.getTotalPagesRead());
        progressBar.setStringPainted(true);
        JPanel progressBarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        styler.styleProgressBar(progressBar);
        progressBarPanel.setBackground(new Color(26, 67, 76));
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 45, 0));
        progressBarPanel.add(progressBar);
        mainPanel.add(progressBarPanel);

        JButton returnButton = new JButton("Return");
        styler.styleButton(returnButton);
        returnButton.setPreferredSize(new Dimension(350, 30));
        returnButton.addActionListener(e -> setContentToTrackerPanel());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(26, 67, 76));
        buttonPanel.add(returnButton);
        mainPanel.add(buttonPanel);

        return mainPanel;
    }

        // EFFECTS: gets the next action to be performed
    private void getNextTrackerAction() {
        int option = JOptionPane.showOptionDialog(this,
                "What would you like to do next?",
                "Next Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Return to home", "Return to tracker"},
                null);

        if (option == JOptionPane.YES_OPTION) {
            setContentToHomePanel();
        } else if (option == JOptionPane.NO_OPTION) {
            setContentToTrackerPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: accesses the reading journal
    private void accessReadingJournal(ActionEvent e) {
        System.out.println("Accessing reading journal...");
        setContentToJournalPanel();
    }

    // MODIFIES: this
    // EFFECTS: allows user to edit an entry in the reading journal
    private void editEntryAction(ActionEvent e) {
        setEntryPanel();
    }

    // MODIFIES: this
    // EFFECTS: sets the entry panel
    private void setEntryPanel() {
        Book selectedBook = getSelectedBook();
        if (selectedBook != null) {
            JTextPane textPane = createTextPane(selectedBook);
            JScrollPane scrollPane = new JScrollPane(textPane);
            JPanel panel = createEntryPanel(scrollPane);
            JButton saveButton = createSaveButton(selectedBook, textPane);
            panel.add(saveButton, BorderLayout.SOUTH);
            setContentPane(panel);
            pack();
            revalidate();
        }
    }

    // EFFECTS: creates a text pane for the entry
    private JTextPane createTextPane(Book selectedBook) {
        JTextPane textPane = new JTextPane();
        textPane.setText(selectedBook.getEntry().getContent());
        textPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        return textPane;
    }

    // EFFECTS: creates an entry panel
    private JPanel createEntryPanel(JScrollPane scrollPane) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(600, 600));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panel.setBackground(new Color(26, 67, 76));
        return panel;
    }

    // EFFECTS: creates a save button for the entry
    private JButton createSaveButton(Book selectedBook, JTextPane textPane) {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveEntryAction(selectedBook, textPane));
        styler.styleButton(saveButton);
        return saveButton;
    }

    // MODIFIES: this
    // EFFECTS: saves the entry for a book
    private void saveEntryAction(Book selectedBook, JTextPane textPane) {
        selectedBook.getEntry().setContent(textPane.getText());
        int option = JOptionPane.showOptionDialog(this,
                "Entry for " + selectedBook.getTitle() + " has been saved."
                        + "\nWhat would you like to do next?",
                "Next Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Return to journal", "Return to home"},
                null);

        if (option == JOptionPane.YES_OPTION) {
            setContentToJournalPanel();
        } else if (option == JOptionPane.NO_OPTION) {
            setContentToHomePanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: views the reading journal
    private void viewJournalAction(ActionEvent e) {
        setContentToJournalViewPanel();
    }

    // EFFECTS: creates a view journal panel
    private JPanel createViewJournalPanel() {
        List<JournalEntry> allEntries = bookshelf.getAllEntries();
        StringBuilder allEntriesText = getAllEntriesText(allEntries);

        JTextArea textArea = createTextArea(allEntriesText.toString());
        JScrollPane scrollPane = new JScrollPane(
                textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JButton returnToJournalButton = createJournalButton("Return to journal", e1 -> setContentToJournalPanel());
        JButton returnToHomeButton = createJournalButton("Return to home", e2 -> setContentToHomePanel());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(returnToJournalButton);
        buttonPanel.add(returnToHomeButton);
        buttonPanel.setBackground(new Color(26, 67, 76));

        return createJournalPanel(scrollPane, buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets the content to the journal view panel
    private void setContentToJournalViewPanel() {
        setContentPane(createViewJournalPanel());
        pack();
        revalidate();
    }

    // EFFECTS: gets all entries in the reading journal as a string builder
    private StringBuilder getAllEntriesText(List<JournalEntry> allEntries) {
        StringBuilder allEntriesText = new StringBuilder();
        for (Book b: bookshelf.getBooks()) {
            JournalEntry entry = b.getEntry();
            if (entry.getContent().isEmpty()) {
                continue;
            }
            allEntriesText.append("\nBook: ").append(b.getTitle()).append(" by ").append(b.getAuthor());
            allEntriesText.append("\n").append(entry.getContent()).append("\n ");
        }
        return allEntriesText;
    }

    // EFFECTS: creates a text area for the reading journal
    private JTextArea createTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    // EFFECTS: creates a button for the reading journal
    private JButton createJournalButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        styler.styleButton(button);
        return button;
    }

    // EFFECTS: creates a button panel for the reading journal
    private JPanel createJournalButtonPanel(JButton returnToJournalButton, JButton returnToHomeButton) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(26, 67, 76));
        buttonPanel.add(returnToJournalButton);
        buttonPanel.add(returnToHomeButton);
        return buttonPanel;
    }

    // EFFECTS: creates a journal panel
    private JPanel createJournalPanel(JScrollPane scrollPane, JPanel buttonPanel) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.PAGE_END);
        panel.setBackground(new Color(26, 67, 76));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: gets a book recommendation
    private void getBookRecommendation(ActionEvent e) {
        System.out.println("Getting book recommendation...");
        setContentToBookRecPanel();
    }

    // MODIFIES: this
    // EFFECTS: recommends a book by genre
    private void recByGenreAction(ActionEvent e) {
        RecommendationSystem rec = new RecommendationSystem(bookshelf);
        String[] genres = getUniqueGenres();
        String selectedGenre = (String) JOptionPane.showInputDialog(this, "Select a genre:",
                "Recommend by Genre", JOptionPane.QUESTION_MESSAGE, null, genres, genres[0]);
        Book recBook = rec.recBookByGenre(selectedGenre);
        if (recBook != null) {
            int option = JOptionPane.showOptionDialog(this, "Recommended Book: "
                            + recBook.getTitle() + " by " + recBook.getAuthor() + "\nWhat would you like to do next?",
                    "Book Recommendation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        new Object[]{"Get another recommendation", "Return to home"}, null);
            if (option == JOptionPane.YES_OPTION) {
                setContentToBookRecPanel();
            } else if (option == JOptionPane.NO_OPTION) {
                setContentToHomePanel();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: recommends a book by page count
    private void recByPageCountAction(ActionEvent e) {
        RecommendationSystem rec = new RecommendationSystem(bookshelf);
        String[] lengths = {"short", "medium", "long"};
        String selectedLength = (String) JOptionPane.showInputDialog(this,
                "Select a book length:",
                "Recommend by Book Length", JOptionPane.QUESTION_MESSAGE, null, lengths, lengths[0]);
        Book recBook = rec.recBookByPageCount(selectedLength);
        if (recBook != null) {
            int option = JOptionPane.showOptionDialog(this, "Recommended Book: "
                            + recBook.getTitle() + " by " + recBook.getAuthor() + "\nWhat would you like to do next?",
                    "Book Recommendation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null,
                        new Object[]{"Get another recommendation", "Return to home"}, null);
            if (option == JOptionPane.YES_OPTION) {
                setContentToBookRecPanel();
            } else if (option == JOptionPane.NO_OPTION) {
                setContentToHomePanel();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No books match the specified length.");
        }
    }

    // MODIFIES: this
    // EFFECTS: recommends a random book
    private void randomRecAction(ActionEvent e) {
        RecommendationSystem rec = new RecommendationSystem(bookshelf);
        Book recBook = rec.recRandomBook();
        int option = JOptionPane.showOptionDialog(this, "Recommended Book: "
                        + recBook.getTitle() + " by " + recBook.getAuthor() + "\nWhat would you like to do next?",
                "Book Recommendation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Get another recommendation", "Return to home"}, null);
        if (option == JOptionPane.YES_OPTION) {
            setContentToBookRecPanel();
        } else if (option == JOptionPane.NO_OPTION) {
            setContentToHomePanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: returns to the home panel
    private void returnHomeAction(ActionEvent e) {
        setContentToHomePanel();
    }

    // EFFECTS: runs the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}