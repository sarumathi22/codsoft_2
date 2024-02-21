//WORD COUNTER
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    
    private static final String[] STOP_WORDS = {"a", "an", "the", "and", "or", "but", "of", "to", "in", "on"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'text' to input text or 'file' to provide a file:");
        String inputType = scanner.nextLine().trim().toLowerCase();

        String text = "";
        if (inputType.equals("text")) {
            System.out.println("Enter the text:");
            text = scanner.nextLine();
        } else if (inputType.equals("file")) {
            System.out.println("Enter the file path:");
            String filePath = scanner.nextLine();
            try {
                text = readFile(filePath);
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                System.exit(1);
            }
        } else {
            System.err.println("Invalid input type. Please enter 'text' or 'file'.");
            System.exit(1);
        }

        Map<String, Integer> wordCounts = countWords(text);
        int totalWords = wordCounts.values().stream().mapToInt(Integer::intValue).sum();

        System.out.println("Total words: " + totalWords);
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private static Map<String, Integer> countWords(String text) {
        Map<String, Integer> wordCounts = new HashMap<>();
        String[] words = text.split("[\\s\\p{Punct}]+");
        for (String word : words) {
            word = word.toLowerCase();
            if (!isStopWord(word)) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }
        return wordCounts;
    }

    private static boolean isStopWord(String word) {
        for (String stopWord : STOP_WORDS) {
            if (word.equals(stopWord)) {
                return true;
            }
        }
        return false;
    }
}

    

