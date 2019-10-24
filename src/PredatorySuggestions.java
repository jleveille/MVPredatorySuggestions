import java.util.ArrayList;

public class PredatorySuggestions {
    public static final ArrayList<TrainingExample> PRONOUNS_LIST = TextLib.readPronouns("data/Pronouns");
    private static final ArrayList<TrainingExample> PREDATORY_PHRASES_LIST = TextLib.readPredatoryPhrases("data/GuiltAndThreat.txt");

    public static boolean isPredatory(String text) {
        if (containsPredatoryPhrases(text)) return true;
        if (containsPronouns(text)) return true;

        return false;
    }

    private static boolean containsPredatoryPhrases(String text) {
        int predatoryCount = 0, notpredatoryCount = 0;
        for(TrainingExample example: PREDATORY_PHRASES_LIST) {
            if (text.contains(example.getText())){
                if(example.getIsPredatory() == true) {
                    predatoryCount++;
                } else {
                    notpredatoryCount++;
                }
            }
        }
        if (predatoryCount > notpredatoryCount) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean containsPronouns(String text) {
        String[] words = text.split(" ");
        for (TrainingExample pronoun : PRONOUNS_LIST) {
            for (String word : words) {
                if (word.equals(pronoun)) {
                    return true;
                }
            }
        }
        return false;
    }
}
