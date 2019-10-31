import java.util.ArrayList;

public class PredatorySuggestions {
    private static final ArrayList<String> PRONOUNS_LIST = TextLib.readOneWordFiles("data/Names.txt");
    private static final ArrayList<TrainingExample> ACTIONS_LIST = TextLib.readTwoWordFiles("data/Action.txt");
    private static final ArrayList<TrainingExample> PREDATORY_PHRASES_LIST = TextLib.readPredatoryPhrases("data/GuiltAndThreat(LargeText).txt");

    public static boolean isPredatory(String text) {
        int predatoryCount = 0, notpredatoryCount = 0;
        if (pronounFollowedByNegAction(text)) {
            predatoryCount++;
        } else {
            notpredatoryCount++;
        }
        if (containsPredatoryPhrases(text)) {
            predatoryCount++;
        } else {
            notpredatoryCount++;
        }
        return (predatoryCount > notpredatoryCount);
    }

    private static boolean containsPredatoryPhrases(String text) {
        int predatoryCount = 0, notpredatoryCount = 0;
        for (TrainingExample example : PREDATORY_PHRASES_LIST) {
            if (text.contains(example.getText())) {
                if (example.getIsPredatory()) {
                    predatoryCount++;
                } else {
                    notpredatoryCount++;
                }
            }
        }
        if (predatoryCount > notpredatoryCount) {
            return true;
        } else {
            return false; //not enough information
        }
    }

    private static boolean pronounFollowedByNegAction(String text) {
        ArrayList<String> words = breakIntoWords(text);
        for (int i = 0; i < words.size() - 1; i++) {
            if (containsList(words.get(i), PRONOUNS_LIST)) {
                for (TrainingExample example : ACTIONS_LIST) {
                    if (words.get(i + 1).equals(example)) {
                        if (example.getIsPredatory()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean containsList(String word, ArrayList<String> list) {
        for (String item : list) {
            if (word.equals(item)) {
                return true;
            }
        }
        return false;
    }

    private static String removePunctuation(String input) {
        String word = input.toLowerCase();
        String newWord = "";
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            if ("abcdefghijklmnopqrstuvwxyz1234567890".contains(letter)) newWord += letter;
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
