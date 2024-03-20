package ui;

import model.Book;
import model.VirtualBookshelf;
import model.Rating;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

// Represents the main frame of the BookBuddy application
public class MainFrame extends JFrame {
    private VirtualBookshelf bookshelf;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/bookshelf.json";
    private final UniversalStyler styler;

    // EFFECTS: constructs the main frame of the application
    public MainFrame() {
        setTitle("BookBuddy");
        setSize(new Dimension(400, 250));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.bookshelf = new VirtualBookshelf();
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.jsonReader = new JsonReader(JSON_STORE);
        this.styler = new UniversalStyler();

        MainMenuPanel mainMenuPanel = new MainMenuPanel(
                this::onLoadButtonClicked,
                this::onCreateButtonClicked
        );
        setContentPane(mainMenuPanel);
    }

    // MODIFIES: this
    // EFFECTS: loads bookshelf from file
    private void onLoadButtonClicked(ActionEvent e) {
        try {
            bookshelf = jsonReader.read();
            System.out.println("Loading bookshelf: " + JSON_STORE);
            setContentPane(new HomePanel(
                    this::accessBookshelf,
                    this::openReadingTracker,
                    this::accessReadingJournal,
                    this::getBookRecommendation
            ));
            pack();
            revalidate();
        } catch (IOException ex) {
            System.out.println("Unable to load file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new bookshelf
    private void onCreateButtonClicked(ActionEvent e) {
        bookshelf = new VirtualBookshelf();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        System.out.println("New bookshelf created");
        setContentPane(new HomePanel(
                this::accessBookshelf,
                this::openReadingTracker,
                this::accessReadingJournal,
                this::getBookRecommendation
        ));
        pack();
        revalidate();
    }

    // MODIFIES: this
    // EFFECTS: accesses the bookshelf
    private void accessBookshelf(ActionEvent e) {
        System.out.println("Accessing bookshelf...");
        setContentPane(new BookshelfPanel(
                this::addAction,
                this::viewAction,
                this::rateAction,
                this::updateAction
        ));
        pack();
        revalidate();
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
                addBookMessage(newBook);
                int option = getNextAction();
                continueAdding = handleNextAction(option);
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

    // EFFECTS: displays a message indicating that a book has been added to the bookshelf
    private void addBookMessage(Book book) {
        JOptionPane.showMessageDialog(this, book.getTitle() + " by " + book.getAuthor()
                + " has been added to your bookshelf!");
    }

    // EFFECTS: gets the next action to be performed
    private int getNextAction() {
        return JOptionPane.showOptionDialog(this,
                "What would you like to do next?",
                "Next Action",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Add another book", "View bookshelf", "Exit"},
                null);
    }

    // EFFECTS: handles the next action to be performed
    private boolean handleNextAction(int option) {
        switch (option) {
            case 0:
                return true;
            case 1:
                return handleBookshelfPanelOption();
            case 2:
                return handleHomePanelOption();
            default:
                return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the bookshelf panel option
    private boolean handleBookshelfPanelOption() {
        setContentPane(new BookshelfPanel(
                this::addAction,
                this::viewAction,
                this::rateAction,
                this::updateAction
        ));
        pack();
        revalidate();
        return false;
    }

    // MODIFIES: this
    // EFFECTS: handles the home panel option
    private boolean handleHomePanelOption() {
        setContentPane(new HomePanel(
                this::accessBookshelf,
                this::openReadingTracker,
                this::accessReadingJournal,
                this::getBookRecommendation
        ));
        pack();
        revalidate();
        return false;
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

    // EFFECTS: creates a table model for the bookshelf
    private DefaultTableModel createTableModel() {
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{
                "Title", "Author", "Genre", "Page Count"}, 0);

        for (Book book : bookshelf.getBooks()) {
            tableModel.addRow(new Object[]{
                    book.getTitle(), book.getAuthor(), book.getGenre(), book.getPageCount()});
        }

        return tableModel;
    }

    // EFFECTS: creates a table for the bookshelf
    private JTable createTable(DefaultTableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.addMouseListener(createMouseListener(table, tableModel));
        return table;
    }

    // EFFECTS: creates a mouse listener for the table
    private MouseAdapter createMouseListener(JTable table, DefaultTableModel tableModel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
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
            }
        };
    }

    // EFFECTS: creates a scroll pane for the table
    private JScrollPane createScrollPane(JTable table) {
        return new JScrollPane(table);
    }

    // EFFECTS: creates a delete button for the table
    private JButton createDeleteButton(JTable table, DefaultTableModel tableModel) {
        JButton deleteButton = new JButton("Delete Book");
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
        JButton returnToBookshelfButton = new JButton("Return to Bookshelf");
        styler.styleButton(returnToBookshelfButton);
        returnToBookshelfButton.addActionListener(e -> handleBookshelfPanelOption());
        return returnToBookshelfButton;
    }

    // EFFECTS: creates a button to return to the home panel
    private JButton createReturnToHomeButton() {
        JButton returnToHomeButton = new JButton("Return to Home");
        styler.styleButton(returnToHomeButton);
        returnToHomeButton.addActionListener(e -> handleHomePanelOption());
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
        String author = (String) genreBox.getSelectedItem();
        String genre = (String) genreBox.getSelectedItem();
        String length = (String) lengthBox.getSelectedItem();
        String status = (String) statusBox.getSelectedItem();

        ArrayList<Book> filteredBooks = new ArrayList<>(bookshelf.getBooks());

        applyFilter(author, filteredBooks, bookshelf::getBooksByAuthor);
        applyFilter(genre, filteredBooks, bookshelf::getBooksByGenre);
        applyFilter(length, filteredBooks, bookshelf::getBooksByPageCount);
        applyFilter(status, filteredBooks, bookshelf::getBooksByStatus);

        tableModel.setRowCount(0);
        for (Book book : filteredBooks) {
            tableModel.addRow(new Object[]{
                    book.getTitle(), book.getAuthor(), book.getGenre(), book.getPageCount()});
        }
    }

    // EFFECTS: applies specified filtering method to specified value
    private void applyFilter(String filterValue, ArrayList<Book> filteredBooks,
                             Function<String, ArrayList<Book>> filterMethod) {
        if (filterValue != null) {
            if (!filterValue.equals("Any")) {
                filteredBooks.retainAll(filterMethod.apply(filterValue));
            }
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
        Book selectedBook = getSelectedBook();
        if (selectedBook != null) {
            int rating = getRating();
            if (rating != -1) {
                updateRating(selectedBook, rating);
            }
        }
        handleNextAction(getNextRatingAction());
    }

    // EFFECTS: gets the selected book
    private Book getSelectedBook() {
        ArrayList<Book> books = bookshelf.getBooks();
        String[] bookDesc = new String[books.size()];
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            bookDesc[i] = book.getTitle() + " by " + book.getAuthor() + ", " + book.getRating();
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

    // EFFECTS: gets the rating for a book
    private int getRating() {
        String rating = (String) JOptionPane.showInputDialog(this,
                "Select a rating for the book:", "Rate a Book", JOptionPane.QUESTION_MESSAGE,
                null, new String[]{"1", "2", "3", "4", "5"}, "1");
        return rating != null ? Integer.parseInt(rating) : -1;
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

    // EFFECTS: gets the next action to be performed
    private int getNextRatingAction() {
        return JOptionPane.showOptionDialog(this,
                "What would you like to do next?", "Next Action",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Add another rating", "View bookshelf", "Exit"}, null);
    }



    // MODIFIES: this
    // EFFECTS: updates the status of a book
    private void updateAction(ActionEvent e) {
        System.out.println("Updating book status...");
        //TODO: implement updateAction
    }

    // MODIFIES: this
    // EFFECTS: opens the reading tracker
    private void openReadingTracker(ActionEvent e) {
        System.out.println("Opening reading tracker...");
        //TODO: implement openReadingTracker
    }

    // MODIFIES: this
    // EFFECTS: accesses the reading journal
    private void accessReadingJournal(ActionEvent e) {
        System.out.println("Accessing reading journal...");
        //TODO: implement accessReadingJournal
    }

    // MODIFIES: this
    // EFFECTS: gets a book recommendation
    private void getBookRecommendation(ActionEvent e) {
        System.out.println("Getting book recommendation...");
        //TODO: implement getBookRecommendation
    }

    // EFFECTS: runs the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}