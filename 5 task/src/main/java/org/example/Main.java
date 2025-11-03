/**
 * @author Dorofeeva Irina
 */
package org.example;

import java.io.*;
import java.util.*;

/**
 * Program for analyzing words in a file using collections.
 */
public class Main {

    /**
     * Main method that starts the word analysis program.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        analyzeWordsInFile(filePath);

        scanner.close();
    }

    /**
     * Analyzes words in the specified file.
     * Reads words, sorts them alphabetically, counts occurrences,
     * and displays statistics.
     *
     * @param filePath path to the input file
     */
    public static void analyzeWordsInFile(String filePath) {
        // Read words from file
        List<String> words = readWordsFromFile(filePath);

        if (words.isEmpty()) {
            System.out.println("No words found in file.");
            return;
        }

        // Sort words alphabetically
        List<String> sortedWords = sortWordsAlphabetically(words);

        // Count word occurrences
        Map<String, Integer> wordCount = countWordOccurrences(words);

        // Display statistics
        displayStatistics(sortedWords, wordCount);

        // Find and display most frequent word
        findAndDisplayMostFrequentWord(wordCount);
    }

    /**
     * Reads words from file and returns them as a list.
     *
     * @param filePath path to the input file
     * @return list of words from the file
     */
    private static List<String> readWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            System.err.println("File does not exist: " + filePath);
            return words;
        }

        if (!file.isFile()) {
            System.err.println("Path is not a file: " + filePath);
            return words;
        }

        if (!file.canRead()) {
            System.err.println("Cannot read file: " + filePath);
            return words;
        }

        // Try to read file without exceptions
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    if (!word.trim().isEmpty()) {
                        words.add(word.trim().toLowerCase());
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return words;
    }

    /**
     * Sorts words alphabetically.
     *
     * @param words list of words to sort
     * @return sorted list of words
     */
    private static List<String> sortWordsAlphabetically(List<String> words) {
        List<String> sorted = new ArrayList<>(words);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Counts how many times each word appears in the list.
     *
     * @param words list of words
     * @return map with words as keys and counts as values
     */
    private static Map<String, Integer> countWordOccurrences(List<String> words) {
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount;
    }

    /**
     * Displays word statistics.
     *
     * @param sortedWords alphabetically sorted words
     * @param wordCount map with word counts
     */
    private static void displayStatistics(List<String> sortedWords, Map<String, Integer> wordCount) {
        System.out.println("\n=== WORD STATISTICS ===");

        System.out.println("\nAll words in alphabetical order:");
        for (String word : sortedWords) {
            System.out.println(word);
        }

        System.out.println("\nWord occurrences:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " times");
        }
    }

    /**
     * Finds and displays the most frequent word.
     *
     * @param wordCount map with word counts
     */
    private static void findAndDisplayMostFrequentWord(Map<String, Integer> wordCount) {
        String mostFrequentWord = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentWord = entry.getKey();
            }
        }

        if (mostFrequentWord != null) {
            System.out.println("\nMost frequent word:");
            System.out.println(mostFrequentWord + ": " + maxCount + " times");
        }
    }
}

