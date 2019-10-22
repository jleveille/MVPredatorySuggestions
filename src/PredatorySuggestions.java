public class PredatorySuggestions {
    //put our methods here

    public void isPredatory(String text) {
        if (containsPredatoryPhrases(text)) return true;
        if (containsPronouns(text)) return true;

        return false;
    }


    private boolean containsPredatoryPhrases(String text) {
        return true;
    }

    private boolean containsPronouns(String text) {
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
