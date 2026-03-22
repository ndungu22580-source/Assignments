import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibrarySearch extends JFrame {
    // Sample Data: 10 books (Title, Author, ISBN, Availability)
    String[][] data = {
        {"The Hobbit", "Tolkien", "101", "Yes"}, {"1984", "Orwell", "102", "No"},
        {"Java Basics", "Smith", "103", "Yes"}, {"C++ Pro", "Jones", "104", "Yes"},
        {"The Great Gatsby", "Fitzgerald", "105", "No"}, {"Moby Dick", "Melville", "106", "Yes"},
        {"Ulysses", "Joyce", "107", "No"}, {"The Odyssey", "Homer", "108", "Yes"},
        {"Hamlet", "Shakespeare", "109", "Yes"}, {"War and Peace", "Tolstoy", "110", "No"}
    };

    public LibrarySearch() {
        setTitle("Library Search");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Search Components
        JTextField txtSearch = new JTextField(15);
        JButton btnSearch = new JButton("Search");
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);

        // 2. Table for Results
        String[] cols = {"Title", "Author", "ISBN", "Availability"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        // 3. List for History
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> list = new JList<>(listModel);
        list.setBorder(BorderFactory.createTitledBorder("History"));

        // 4. Menu Bar
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File"); JMenuItem e1 = new JMenuItem("Exit"); m1.add(e1);
        JMenu m2 = new JMenu("View"); JMenuItem e2 = new JMenuItem("Clear History"); m2.add(e2);
        JMenu m3 = new JMenu("Help"); JMenuItem e3 = new JMenuItem("About"); m3.add(e3);
        mb.add(m1); mb.add(m2); mb.add(m3);
        setJMenuBar(mb);

        // Logic for Search
        btnSearch.addActionListener(e -> {
            String query = txtSearch.getText().toLowerCase();
            model.setRowCount(0); // Clear table
            listModel.addElement(txtSearch.getText()); // Add to history
            
            for (String[] book : data) {
                if (book[0].toLowerCase().contains(query)) {
                    model.addRow(book);
                }
            }
        });

        // Menu Actions
        e1.addActionListener(e -> System.exit(0));
        e2.addActionListener(e -> listModel.clear());
        e3.addActionListener(e -> JOptionPane.showMessageDialog(this, "Library System v1"));

        // Add to Layout
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(new JScrollPane(list), BorderLayout.EAST);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new LibrarySearch();
    }
}
