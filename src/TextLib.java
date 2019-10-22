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
                output.append(line.trim()+"\n");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }

        return output.toString();
    }

    public static ArrayList<TrainingExample> readDataSet(String filename){
        ArrayList<TrainingExample> trainingExampleList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                TrainingExample example = processLine(line);
                trainingExampleList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return trainingExampleList;
    }

    private static TrainingExample processLine(String line) {
        String[] values = line.split(",");
        String text = values[0].trim();
        boolean isPredatory = Boolean.parseBoolean(values[1].trim());

        return new TrainingExample(text, isPredatory);
    }

    public static ArrayList<TrainingExample> readPredatoryPhrases(String filename){
        ArrayList<TrainingExample> predatoryPhrasesList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                TrainingExample example = processLinePhrase(line);
                predatoryPhrasesList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return predatoryPhrasesList;
    }

    private static TrainingExample processLinePhrase(String line) {
        String[] values = line.split(",");
        String phrase = values[0].trim();
        String connotation = values[1].trim();
        boolean isPredatory;
        if (connotation.equals("pos")) {
            isPredatory = false;
        } else if (connotation.equals("neg")){
            isPredatory = true;
        } else {
            System.out.println("THERE IS AN ERROR IN PROCESS LINE FOR PHRASE");
            isPredatory = true;
        }
        return new TrainingExample(phrase, isPredatory);
    }


    //Predatory language METHODS
    private static TrainingExample processLineActions(String line) {
        String[] values = line.split(" ");
        String text = values[0].trim();
        boolean isPredatory = Boolean.parseBoolean(values[1].trim());

        return new TrainingExample(text, isPredatory);
    }

    private static TrainingExample processLineOneWord(String line) {
//        String[] values = line.split(",");
        String text = line.trim();
        boolean isPredatory = Boolean.parseBoolean(text);
        return new TrainingExample(text, isPredatory);
    }
    public static ArrayList<TrainingExample> readActions(String filename){
        ArrayList<TrainingExample> trainingExampleList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                TrainingExample example = processLineActions(line);
                trainingExampleList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return trainingExampleList;
    }

    public static ArrayList<TrainingExample> readPronouns(String filename){
        ArrayList<TrainingExample> trainingExampleList = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filename), "UTF-8");
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                TrainingExample example = processLineOneWord(line);
                trainingExampleList.add(example);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + filename);
        }
        return trainingExampleList;
    }

}

