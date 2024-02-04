package org.example;
import java.util.Stack;

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Nie można dzielić prez 0.");
        }
        return a / b;
    }

    public double power(double a, double b) {
        return Math.pow(a, b);
    }

    public double sqrt(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Liczba nie może być ujemna.");
        }
        return Math.sqrt(a);
    }

    public double evaluate(String expression) {
        // usuwanie białych znaków
        expression = expression.replaceAll("\\s", "");
        return evaluateExpression(expression);
    }

    private double evaluateExpression(String expression) {
        // stack do przechowywania liczb
        Stack<Double> numbers = new Stack<>();

        // stack do przechowywania operatorów
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (Character.isDigit(current)) {
                // obsługa wielocyfrowych liczb
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i++));
                }
                i--; // kompensacja dodatkowego i++ w pętli
                numbers.push(Double.parseDouble(number.toString()));
            } else if (current == '(') {
                operations.push(current);
            } else if (current == ')') {
                while (operations.peek() != '(') {
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            } else if (isOperator(current)) {
                while (!operations.isEmpty() && precedence(current) <= precedence(operations.peek())) {
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(current);
            }
        }

        while (!operations.isEmpty()) {
            numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return -1;
    }

    private double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new UnsupportedOperationException("Nie można dzielić przez 0.");
                return a / b;
        }
        return 0;
    }

}


