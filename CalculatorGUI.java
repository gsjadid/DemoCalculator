import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {
    private JTextField display;
    private double num1, num2, result;
    private String operator = "";

    public CalculatorGUI() {
        setTitle("DemoCalculatorForPractice");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Display Screen
        display = new JTextField();
        display.setFont(new Font("Digital-7", Font.BOLD, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(Color.DARK_GRAY);
        String[] buttons = {
                "7", "8", "9", "÷",
                "4", "5", "6", "×",
                "1", "2", "3", "−",
                "0", ".", "=", "+",
                "C", "±", "%", "⌫"
        };

        for (String text : buttons) {
            JButton button = createButton(text);
            buttonPanel.add(button);
            button.addActionListener(e -> handleButtonClick(text));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());

        if ("÷×−+=C±%⌫".contains(text)) {
            button.setBackground(Color.ORANGE);
        }
        return button;
    }

    private void handleButtonClick(String text) {
        if ("0123456789.".contains(text)) {
            display.setText(display.getText() + text);
        } else if ("÷×−+".contains(text)) {
            num1 = Double.parseDouble(display.getText());
            operator = text;
            display.setText("");
        } else if ("=".equals(text)) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "÷" -> result = CalculatorLogic.divide(num1, num2);
                case "×" -> result = CalculatorLogic.multiply(num1, num2);
                case "−" -> result = CalculatorLogic.subtract(num1, num2);
                case "+" -> result = CalculatorLogic.add(num1, num2);
            }

            // Display integer if no decimal part
            if (result == (int) result) {
                display.setText(String.valueOf((int) result));
            } else {
                display.setText(String.valueOf(result));
            }
        } else if ("C".equals(text)) {
            display.setText("");
        } else if ("⌫".equals(text) && !display.getText().isEmpty()) {
            display.setText(display.getText().substring(0, display.getText().length() - 1));
        } else if ("±".equals(text) && !display.getText().isEmpty()) {
            display.setText(String.valueOf(-Double.parseDouble(display.getText())));
        } else if ("%".equals(text) && !display.getText().isEmpty()) {
            display.setText(String.valueOf(Double.parseDouble(display.getText()) / 100));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}
