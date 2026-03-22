import javax.swing.*;
import java.awt.*;

public class SimpleCalc {
    // Variables to store numbers and the chosen operator
    static double num1 = 0, num2 = 0, result = 0;
    static char operator;

    public static void main(String[] args) {
        // 1. Create the Frame
        JFrame frame = new JFrame("Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 2. The Display Field
        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // 3. The Button Panel (4x4 Grid)
        JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));

        // Define the button labels
        String[] labels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", ".", "=", "+"
        };

        // 4. Create buttons and add logic
        for (String label : labels) {
            JButton btn = new JButton(label);
            panel.add(btn);

            // Add action to each button
            btn.addActionListener(e -> {
                String cmd = btn.getText();

                if (cmd.equals("C")) {
                    display.setText("");
                } else if (cmd.equals("=")) {
                    num2 = Double.parseDouble(display.getText());
                    if (operator == '+') result = num1 + num2;
                    if (operator == '-') result = num1 - num2;
                    if (operator == '*') result = num1 * num2;
                    if (operator == '/') {
                        if (num2 == 0) display.setText("Error");
                        else result = num1 / num2;
                    }
                    if (!display.getText().equals("Error")) {
                        display.setText(String.valueOf(result));
                    }
                } else if ("+-*/".contains(cmd)) {
                    num1 = Double.parseDouble(display.getText());
                    operator = cmd.charAt(0);
                    display.setText("");
                } else {
                    // It's a number or a decimal point
                    display.setText(display.getText() + cmd);
                }
            });
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
