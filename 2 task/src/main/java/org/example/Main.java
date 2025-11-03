/**
 * @author Dorofeeva Irina
 */

package org.example;

import java.util.Scanner;

public class Main {

    /**
     * Main method of the program that performs one arithmetic operation.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of task: (1 - calculator, 2 - string array)");
        int taskNumber = scanner.nextInt();

        if (taskNumber == 1) {
            // Calculator task
            // Input first number
            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();

            // Input operation
            System.out.print("Enter operation (+, -, *, /): ");
            String operation = scanner.next();

            // Input second number
            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();

            // Perform operation and display result
            performOperation(num1, num2, operation);
        } else if (taskNumber == 2) {
            // String array task
            System.out.print("Enter the size of the array: ");
            int arraySize = scanner.nextInt();

            // Consume the newline character
            scanner.nextLine();

            // Create array for words
            String[] words = new String[arraySize];

            System.out.println("Enter " + arraySize + " words:");

            // Read words from console
            for (int i = 0; i < arraySize; i++) {
                System.out.print("Word " + (i + 1) + ": ");
                words[i] = scanner.nextLine();
            }

            // Find the longest word
            String longestWord = findLongestWordInArray(words);

            // Display result
            System.out.println("The longest word in the array is: \"" + longestWord + "\"");
        } else {
            System.err.println("Error: Invalid task number. Please enter 1 or 2.");
        }

        scanner.close();
    }

    /**
     * Performs arithmetic operation on two numbers and displays the result.
     * Result is rounded to 4 decimal places.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @param operation the arithmetic operation (+, -, *, /)
     */
    public static void performOperation(double num1, double num2, String operation) {
        double result;
        boolean validOperation = true;

        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    System.err.println("Error: Division by zero is not allowed!");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.err.println("Error: Unsupported operation: " + operation);
                validOperation = false;
                result = 0;
        }

        if (validOperation) {
            System.out.printf("Result: %.4f", result);
        }
    }

    /**
     * Finds and returns the longest word in the given array of strings.
     * If multiple words have the same maximum length, returns the first one encountered.
     *
     * @param words the array of words to search
     * @return the longest word from the array
     */
    public static String findLongestWordInArray(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        String longestWord = words[0];

        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longestWord.length()) {
                longestWord = words[i];
            }
        }

        return longestWord;
    }
}