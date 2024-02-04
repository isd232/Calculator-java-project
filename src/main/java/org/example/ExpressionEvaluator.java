package org.example;

import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionEvaluator {

    public double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/^()", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();

            if (token.isEmpty()) {
                continue;
            }

            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                numbers.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operations.push(c);
            } else if (token.equals(")")) {
                while (!operations.isEmpty() && operations.peek() != '(') {
                    numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) {
                while (!operations.isEmpty() && hasPrecedence(c, operations.peek())) {
                    numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()) {
            numbers.push(applyOp(operations.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        if (op1 == '^' && (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/')) {
            return false;
        }
        return true;
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
        }
        return 0;
    }
}