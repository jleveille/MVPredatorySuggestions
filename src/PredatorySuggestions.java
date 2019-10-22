import java.util.ArrayList;

public class PredatorySuggestions {
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
                if(example.getIsPredatory() == true) predatoryCount++;
                if (example.getIsPredatory() == false) notpredatoryCount++;
            }
        }
        if (predatoryCount > notpredatoryCount) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean containsPronouns(String text) {
        String[] stuff = file.split(",");
        String[] words = phrase.split(" ");
        for (String word : words) {
            if (word.equals(stuff)) {
                return true;
            }
        }
        return false;
    }
}
