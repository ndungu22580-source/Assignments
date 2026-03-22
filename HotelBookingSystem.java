import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class HotelBookingSystem extends JFrame {
    // UI Components
    private JSpinner checkInSpinner, checkOutSpinner;
    private JComboBox<String> roomTypeCombo;
    private JTable roomTable;
    private DefaultTableModel tableModel;

    public HotelBookingSystem() {
        setTitle("Hotel Booking System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JTabbedPane tabbedPane = new JTabbedPane();

        // --- 1. Booking Tab ---
        JPanel bookingPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        bookingPanel.add(new JLabel("Customer Name:"));
        JTextField nameField = new JTextField();
        bookingPanel.add(nameField);

        bookingPanel.add(new JLabel("Check-In Date:"));
        checkInSpinner = new JSpinner(new SpinnerDateModel());
        bookingPanel.add(checkInSpinner);

        bookingPanel.add(new JLabel("Check-Out Date:"));
        checkOutSpinner = new JSpinner(new SpinnerDateModel());
        bookingPanel.add(checkOutSpinner);

        bookingPanel.add(new JLabel("Room Type:"));
        roomTypeCombo = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        bookingPanel.add(roomTypeCombo);

        JButton btnBook = new JButton("Book Now");
        JButton btnReceipt = new JButton("Generate Receipt");
        bookingPanel.add(btnBook);
        bookingPanel.add(btnReceipt);

        // --- 2. Rooms Tab ---
        String[] columns = {"Room Type", "Status"};
        Object[][] data = {{"Single", "Available"}, {"Double", "Available"}, {"Suite", "Available"}};
        tableModel = new DefaultTableModel(data, columns);
        roomTable = new JTable(tableModel);
        JPanel roomsPanel = new JPanel(new BorderLayout());
        roomsPanel.add(new JScrollPane(roomTable), BorderLayout.CENTER);

        // Add Tabs
        tabbedPane.addTab("Booking", bookingPanel);
        tabbedPane.addTab("Rooms", roomsPanel);
        add(tabbedPane);

        // --- Logic & Events ---
        
        // Task 3: Update room status dynamically
        btnBook.addActionListener(e -> {
            String selectedType = (String) roomTypeCombo.getSelectedItem();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(selectedType)) {
                    tableModel.setValueAt("Booked", i, 1);
                    JOptionPane.showMessageDialog(this, selectedType + " room booked!");
                }
            }
        });

        // Task 4: Generate Receipt in JDialog
        btnReceipt.addActionListener(e -> {
            // Task 2: Simple Date Validation
            Date in = (Date) checkInSpinner.getValue();
            Date out = (Date) checkOutSpinner.getValue();
            
            if (out.before(in)) {
                JOptionPane.showMessageDialog(this, "Error: Check-out must be after Check-in.");
                return;
            }

            JDialog receipt = new JDialog(this, "Receipt", true);
            receipt.setLayout(new FlowLayout());
            receipt.add(new JLabel("<html><b>Receipt Summary</b><br>Name: " + nameField.getText() + 
                "<br>Room: " + roomTypeCombo.getSelectedItem() + 
                "<br>Check-in: " + in + "</html>"));
            receipt.setSize(300, 200);
            receipt.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HotelBookingSystem().setVisible(true));
    }
}
