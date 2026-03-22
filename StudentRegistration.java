import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentRegistration extends JFrame implements ActionListener {
    // Components
    private JTextField txtID, txtName, txtCourse, txtYear;
    private JComboBox<String> cbDepartment;
    private JRadioButton rbMale, rbFemale, rbOther;
    private ButtonGroup genderGroup;
    private JCheckBox chkHostel;
    private JTextArea displayArea;
    private JButton btnRegister, btnClear;

    public StudentRegistration() {
        // 1. Frame Setup
        setTitle("Student Registration");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 2. Input Panel (GridLayout for labels and fields)
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Student ID:"));
        txtID = new JTextField();
        inputPanel.add(txtID);

        inputPanel.add(new JLabel("Full Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Course:"));
        txtCourse = new JTextField();
        inputPanel.add(txtCourse);

        inputPanel.add(new JLabel("Year of Study:"));
        txtYear = new JTextField();
        inputPanel.add(txtYear);

        inputPanel.add(new JLabel("Department:"));
        String[] depts = {"IT", "Business", "Engineering", "Science"};
        cbDepartment = new JComboBox<>(depts);
        inputPanel.add(cbDepartment);

        inputPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbOther = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);
        inputPanel.add(genderPanel);

        inputPanel.add(new JLabel("Hostel:"));
        chkHostel = new JCheckBox("Hostel Required");
        inputPanel.add(chkHostel);

        // Buttons
        btnRegister = new JButton("Register");
        btnClear = new JButton("Clear");
        btnRegister.addActionListener(this);
        btnClear.addActionListener(this);
        
        inputPanel.add(btnRegister);
        inputPanel.add(btnClear);

        // 3. Display Area (Bottom)
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Adding panels to Frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            handleRegistration();
        } else if (e.getSource() == btnClear) {
            clearFields();
        }
    }

    private void handleRegistration() {
        // Validation: Check if text fields are empty
        if (txtID.getText().isEmpty() || txtName.getText().isEmpty() || 
            txtCourse.getText().isEmpty() || txtYear.getText().isEmpty() ||
            genderGroup.getSelection() == null) {
            
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Gather Data
        String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "Other";
        String hostel = chkHostel.isSelected() ? "Yes" : "No";

        // Display Data
        String result = String.format(
            "--- Student Details ---\n" +
            "ID: %s\nName: %s\nCourse: %s\nYear: %s\nDept: %s\nGender: %s\nHostel: %s\n",
            txtID.getText(), txtName.getText(), txtCourse.getText(), 
            txtYear.getText(), cbDepartment.getSelectedItem(), gender, hostel
        );
        
        displayArea.setText(result);
    }

    private void clearFields() {
        txtID.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtYear.setText("");
        cbDepartment.setSelectedIndex(0);
        genderGroup.clearSelection();
        chkHostel.setSelected(false);
        displayArea.setText("");
    }

    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new StudentRegistration());
    }
}
