import java.util.ArrayList;

public class PredatorySuggestions {
    //put our methods here
    private static final ArrayList<TrainingExample> PRONOUNS_LIST = TextLib.readPronouns("data/Pronouns");


    public boolean isPredatory(String text) {
        if (containsPredatoryPhrases(text)) return true;

        if (containsPronouns(text)) return true;

        return false;
    }


    private boolean containsPredatoryPhrases(String text) {
        return true;
    }

    private boolean containsPronouns(String text) {
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
