import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class TextLib {
    public static String readFileAsString(String filename) {
        Scanner scanner;
        StringBuilder output = new StringBuilder();

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                output.append(line.trim() + "\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    public static ArrayList<TrainingExample> readDataSet(String filename) {
        ArrayList<TrainingExample> trainingExampleList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                TrainingExample example = processLineDataSet(line);
                trainingExampleList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return trainingExampleList;
    }

    public static ArrayList<TrainingExample> readTwoWordFiles(String filename) {
        ArrayList<TrainingExample> trainingExampleList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                TrainingExample example = processLineTwoWordFiles(line);
                trainingExampleList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return trainingExampleList;
    }

    public static ArrayList<TrainingExample> readPredatoryPhrases(String filename) {
        ArrayList<TrainingExample> predatoryPhrasesList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                TrainingExample example = processLinePhrase(line);
                predatoryPhrasesList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return predatoryPhrasesList;
    }

    public static ArrayList<String> readOneWordFiles(String filename) {
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String input = line.trim();
                list.add(input);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return list;
    }

    private static TrainingExample processLineDataSet(String line) {
        boolean isPredatory = false;
        String text = "";
        int i = line.length() -1;
        String newLine = line.substring(i);
        while (!newLine.contains(",")) {
            i--;
            newLine = line.substring(i);
        }
            isPredatory = Boolean.parseBoolean(newLine.substring(newLine.indexOf(" ")).trim());
            text = line.substring(0, i).trim();

        return new TrainingExample(text, isPredatory);
    }

    private static TrainingExample processLineTwoWordFiles(String line) {
        String[] values = line.split(",");
        String text = values[0].trim();
        boolean isPredatory = Boolean.parseBoolean(values[1].trim());

        return new TrainingExample(text, isPredatory);
    }

    private static TrainingExample processLinePhrase(String line) {
        String[] values = line.split(",");
        String phrase = values[0].trim();
        String connotation = values[1].trim();
        boolean isPredatory;
        if (connotation.equals("pos")) {
            isPredatory = false;
        } else if (connotation.equals("neg")) {
            isPredatory = true;
        } else {
            System.out.println("THERE IS AN ERROR IN PROCESS LINE FOR PHRASE");
            isPredatory = true;
        }
        return new TrainingExample(phrase, isPredatory);
    }

    private static String removePunctuation(String input) {
        String word = input.toLowerCase();
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            if ("abcdefghijklmnopqrstuvwxyz1234567890 ".contains(letter)) newWord += letter;
        }
        return newWord;
    }

    private static ArrayList<String> breakIntoWords(String sentence) {
        ArrayList<String> words = new ArrayList<>();
        String[] wordArr = sentence.split(" ");
        for (String word : wordArr) {
            word = removePunctuation(word);
            if (!word.equals("")) words.add(word);
        }
        return words;
    }

}

