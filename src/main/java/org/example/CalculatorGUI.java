package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {

    private JFrame frame;
    private JTextField textField;
    private Calculator calculator;

    public CalculatorGUI() {
        calculator = new Calculator();
        createWindow();
        initializeUI();
    }

    private void createWindow() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
    }

    private void initializeUI() {
        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        // Przyciski
        String[] buttons = {
                "C", "^", "√", "/",
                "1", "2", "3", "x",
                "4", "5", "6", "-",
                "7", "8", "9", "+",
                "", "0", ".", "="
        };

        for (String buttonText : buttons) {
            JButton button = new JButton(buttonText);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) == 'C') {
                // clear
                textField.setText("");
            } else if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
                // dodawanie cyfr i kropki do wprowadzonej wartości
                textField.setText(textField.getText() + command);
            } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                // dodanie operatora
                textField.setText(textField.getText() + " " + command + " ");
            } else if (command.equals("=")) {
                // blicz wynik
                String response = calculateResult(textField.getText());
                textField.setText(response);
            } else if (command.equals("^")) {
                textField.setText(textField.getText() + "^");
            } else if (command.equals("√")) {
                textField.setText("√(" + textField.getText() + ")");
            }
        }

        private String calculateResult(String expression) {
            try {
                // założenie, że klasa Calculator ma metodę evaluate, która zwraca double
                double result = calculator.evaluate(expression);

                // konwersja wyniku na String
                return String.valueOf(result);
            } catch (Exception e) {
                // obsługa błędów
                return "Error: " + e.getMessage();
            }
        }
    }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new CalculatorGUI();
                }
            });
        }
    }
