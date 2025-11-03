/**
 * @author Dorofeeva Irina
 */

package org.example;

import java.util.Scanner;

/**
 * Calculator application implemented using OOP principles.
 */
public class Main {

    /**
     * Main method that starts the calculator application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }
}

/**
 * Custom exception for division by zero.
 */
class DivisionByZeroException extends Exception {
    /**
     * Constructs a new DivisionByZeroException with the specified detail message.
     *
     * @param message the detail message
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}

/**
 * Represents a calculator with basic arithmetic operations.
 */
class Calculator {
    private Scanner scanner;
    private double result;

    /**
     * Constructs a new Calculator instance.
     */
    public Calculator() {
        this.scanner = new Scanner(System.in);
        this.result = 0;
    }

    /**
     * Starts the calculator interaction with user.
     */
    public void start() {
        System.out.println("=== OOP CALCULATOR ===");

        boolean continueCalculations = true;

        while (continueCalculations) {
            try {
                displayMenu();
                int choice = getMenuChoice();

                switch (choice) {
                    case 1:
                        performAddition();
                        break;
                    case 2:
                        performSubtraction();
                        break;
                    case 3:
                        performMultiplication();
                        break;
                    case 4:
                        performDivision();
                        break;
                    case 5:
                        displayResult();
                        break;
                    case 6:
                        clearResult();
                        break;
                    case 0:
                        continueCalculations = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (DivisionByZeroException e) {
                System.err.println("Calculation error: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Calculator closed. Goodbye!");
    }

    /**
     * Displays the calculator menu.
     */
    private void displayMenu() {
        System.out.println("\nCurrent result: " + result);
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Display result");
        System.out.println("6. Clear result");
        System.out.println("0. Exit");
        System.out.print("Choose operation: ");
    }

    /**
     * Gets menu choice from user.
     *
     * @return the user's menu choice
     */
    private int getMenuChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * Gets a number input from user.
     *
     * @return the number entered by user
     */
    private double getNumberInput() {
        System.out.print("Enter number: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    /**
     * Performs addition operation.
     */
    private void performAddition() {
        double number = getNumberInput();
        result = Operation.ADDITION.calculate(result, number);
        System.out.printf("Result: %.4f + %.4f = %.4f%n", result - number, number, result);
    }

    /**
     * Performs subtraction operation.
     */
    private void performSubtraction() {
        double number = getNumberInput();
        result = Operation.SUBTRACTION.calculate(result, number);
        System.out.printf("Result: %.4f - %.4f = %.4f%n", result + number, number, result);
    }

    /**
     * Performs multiplication operation.
     */
    private void performMultiplication() {
        double number = getNumberInput();
        double previousResult = result;
        result = Operation.MULTIPLICATION.calculate(result, number);
        System.out.printf("Result: %.4f * %.4f = %.4f%n", previousResult, number, result);
    }

    /**
     * Performs division operation.
     *
     * @throws DivisionByZeroException if division by zero is attempted
     */
    private void performDivision() throws DivisionByZeroException {
        double number = getNumberInput();

        // Check for division by zero using MathUtils
        if (MathUtils.isZero(number)) {
            throw new DivisionByZeroException("Division by zero is not allowed!");
        }

        double previousResult = result;
        result = Operation.DIVISION.calculate(result, number);
        System.out.printf("Result: %.4f / %.4f = %.4f%n", previousResult, number, result);
    }

    /**
     * Displays the current result.
     */
    private void displayResult() {
        System.out.printf("Current result: %.4f%n", result);
    }

    /**
     * Clears the current result.
     */
    private void clearResult() {
        result = 0;
        System.out.println("Result cleared.");
    }
}

/**
 * Enum representing arithmetic operations.
 */
enum Operation {
    ADDITION {
        @Override
        public double calculate(double a, double b) {
            return a + b;
        }
    },
    SUBTRACTION {
        @Override
        public double calculate(double a, double b) {
            return a - b;
        }
    },
    MULTIPLICATION {
        @Override
        public double calculate(double a, double b) {
            return a * b;
        }
    },
    DIVISION {
        @Override
        public double calculate(double a, double b) {
            return a / b;
        }
    };

    /**
     * Performs the arithmetic operation on two numbers.
     *
     * @param a the first operand
     * @param b the second operand
     * @return the result of the operation
     */
    public abstract double calculate(double a, double b);
}

/**
 * Utility class for mathematical operations.
 */
class MathUtils {

    /**
     * Rounds a number to specified decimal places.
     *
     * @param value the number to round
     * @param places the number of decimal places
     * @return the rounded number
     * @throws IllegalArgumentException if places is negative
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException("Decimal places cannot be negative: " + places);
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Validates if a number is zero.
     *
     * @param number the number to validate
     * @return true if number is zero, false otherwise
     */
    public static boolean isZero(double number) {
        return Math.abs(number) < 1e-10;
    }
}