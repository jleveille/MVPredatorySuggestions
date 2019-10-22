public class PredatorySuggestions {
    //put our methods here
    String file = TextLib.readFileAsString("");

    public boolean containsPronouns(String phrase, String file){
        String[] stuff = file.split(",");
        String[] words = phrase.split(" ");
        for (String word : words) {
            if(word.equals(stuff)){

            }
        }
        return true;
    }
}
