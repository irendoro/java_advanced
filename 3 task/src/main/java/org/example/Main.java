/**
 * @author Dorofeeva Irina
 */

package org.example;

import java.util.Random;
import java.util.Scanner;

/**
 * Main class for executing different tasks.
 */
public class Main {

    /**
     * Main method that starts the program.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of task: (1 - array swap, 2 - sweet gift)");
        int taskNumber = scanner.nextInt();

        if (taskNumber == 1) {
            processArraySwap(scanner);
        } else if (taskNumber == 2) {
            createSweetGift(scanner);
        } else {
            System.err.println("Error: Invalid task number. Please enter 1 or 2.");
        }

        scanner.close();
    }

    /**
     * Processes array and swaps max negative with min positive element.
     * Array size and elements are entered by user.
     *
     * @param scanner Scanner object for user input
     */
    public static void processArraySwap(Scanner scanner) {
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();

        int[] array = new int[size];

        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        // Display original array
        System.out.println("Original array:");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

        // Find max negative and min positive elements
        int maxNegative = Integer.MIN_VALUE;
        int minPositive = Integer.MAX_VALUE;
        int maxNegativeIndex = -1;
        int minPositiveIndex = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 && array[i] > maxNegative) {
                maxNegative = array[i];
                maxNegativeIndex = i;
            }
            if (array[i] > 0 && array[i] < minPositive) {
                minPositive = array[i];
                minPositiveIndex = i;
            }
        }

        // Display found elements
        System.out.println("Max negative element: " + (maxNegativeIndex != -1 ? maxNegative : "not found"));
        System.out.println("Min positive element: " + (minPositiveIndex != -1 ? minPositive : "not found"));

        // Swap elements if both were found
        if (maxNegativeIndex != -1 && minPositiveIndex != -1) {
            int temp = array[maxNegativeIndex];
            array[maxNegativeIndex] = array[minPositiveIndex];
            array[minPositiveIndex] = temp;

            System.out.println("Array after swapping:");
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("Cannot swap - one or both elements not found");
        }
    }

    /**
     * Creates sweet gift with user input for different sweet types.
     *
     * @param scanner Scanner object for user input
     */
    public static void createSweetGift(Scanner scanner) {
        // Available sweet types
        String[] sweetTypes = {"Candy", "Jellybean", "Chocolate", "Marshmallow"};
        String[] sweetNames = {
                "Chocolate Candy", "Caramel Candy", "Fruit Jelly",
                "Gummy Bears", "Dark Chocolate", "Milk Chocolate", "Vanilla Marshmallow"
        };
        double[] weights = {50, 40, 30, 60, 100, 90, 25};
        double[] prices = {25, 20, 35, 45, 80, 70, 30};
        String[] uniqueParams = {
                "Milk Chocolate", "Soft Caramel", "Strawberry",
                "Mixed Fruits", "85% Cocoa", "35% Cocoa", "Vanilla Flavor"
        };

        System.out.print("Enter number of different sweet types in gift: ");
        int typeCount = scanner.nextInt();

        String[] selectedSweets = new String[typeCount];
        int[] quantities = new int[typeCount];

        // Display available sweets
        System.out.println("\nAvailable sweets:");
        for (int i = 0; i < sweetNames.length; i++) {
            System.out.println((i + 1) + ". " + sweetNames[i] + " (" + sweetTypes[i % sweetTypes.length] +
                    ") - Weight: " + weights[i] + "g, Price: " + prices[i] + "rub, " + uniqueParams[i]);
        }

        // Select sweets and quantities
        for (int i = 0; i < typeCount; i++) {
            System.out.print("\nEnter sweet number for type " + (i + 1) + ": ");
            int sweetNumber = scanner.nextInt() - 1;

            System.out.print("Enter quantity for " + sweetNames[sweetNumber] + ": ");
            quantities[i] = scanner.nextInt();

            selectedSweets[i] = sweetNames[sweetNumber] + " (" + sweetTypes[sweetNumber % sweetTypes.length] +
                    ") - Weight: " + weights[sweetNumber] + "g, Price: " + prices[sweetNumber] +
                    "rub, " + uniqueParams[sweetNumber];
        }

        // Display gift contents
        System.out.println("\nSweet Gift Contents:");
        System.out.println("====================");

        double totalWeight = 0;
        double totalPrice = 0;
        int totalSweets = 0;

        for (int i = 0; i < typeCount; i++) {
            int sweetIndex = -1;
            for (int j = 0; j < sweetNames.length; j++) {
                if (selectedSweets[i].contains(sweetNames[j])) {
                    sweetIndex = j;
                    break;
                }
            }

            if (sweetIndex != -1) {
                double typeWeight = weights[sweetIndex] * quantities[i];
                double typePrice = prices[sweetIndex] * quantities[i];

                System.out.printf("%d. %s x%d | Weight: %.2fg | Price: %.2frub%n",
                        (i + 1), selectedSweets[i], quantities[i], typeWeight, typePrice);

                totalWeight += typeWeight;
                totalPrice += typePrice;
                totalSweets += quantities[i];
            }
        }

        System.out.println("====================");
        System.out.printf("Total sweets: %d%n", totalSweets);
        System.out.printf("Total weight: %.2f g%n", totalWeight);
        System.out.printf("Total price: %.2f rub%n", totalPrice);
    }
}