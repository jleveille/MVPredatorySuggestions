import java.util.ArrayList;

public class PredatorySuggestions {
    public static final ArrayList<String> PRONOUNS_LIST = TextLib.readOneWordFiles("data/Pronouns");
    public static final ArrayList<String> ACTIONS_LIST = TextLib.readOneWordFiles("data/Action.txt");
    private static final ArrayList<TrainingExample> PREDATORY_PHRASES_LIST = TextLib.readPredatoryPhrases("data/GuiltAndThreat(LargeText).txt");

    public static boolean isPredatory(String text) {
        int predatoryCount = 0, notpredatoryCount = 0;
        if (pronounFollowedByAction(text)) {
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
            return false; //not enough information
        }
    }

    private static boolean pronounFollowedByAction(String text){
        String[] words = text.split(" ");
        for (int i = 0; i < words.length -1; i++) {
            if(containsPronouns(words[i]) && containsActions(words[i+1])) return true;
        }
        return false;
    }

    private static boolean containsPronouns(String word) {
        for (String pronoun : PRONOUNS_LIST) {
                if (word.equals(pronoun)) {
                    return true;
                }
            }
        return false;
    }

    private static boolean containsActions(String word) {
        for (String action : ACTIONS_LIST) {
                if (word.equals(action)) {
                    return true;
                }
            }
        return false;
    }
}
