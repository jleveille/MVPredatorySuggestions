import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<TrainingExample> suggestions = new ArrayList<>();
        suggestions.add(new TrainingExample("I am entitled to be the best", true));
        suggestions.add(new TrainingExample("I am a predatory", true));
        suggestions.add(new TrainingExample("I follow the world and am the worst!", false));
        suggestions.add(new TrainingExample("I am inevitably in love with toys", true));
        suggestions.add(new TrainingExample("I hate puppies so much", false));

        ArrayList<TrainingExample> dataset = new ArrayList<>();
        dataset.add(new TrainingExample("I am entitled to be the best", true));
        dataset.add(new TrainingExample("I am not predatory", false));
        dataset.add(new TrainingExample("I rule the world and am the GREATEST!", true));
        dataset.add(new TrainingExample("I am inevitable", true));
        dataset.add(new TrainingExample("I love puppies so much", false));

        double error = getError(suggestions, dataset);
        System.out.println(error);
    }

    private static double getError(ArrayList<TrainingExample> suggestions, ArrayList<TrainingExample> dataset) {
        int correct = 0;
        for(TrainingExample suggestion: suggestions) {
            for(TrainingExample data: dataset) {
                if(data.getText().equals(suggestion.getText())) {
                    if(data.getIsPredatory() == suggestion.getIsPredatory()) correct++;
                }
            }
        }
        return (double) correct/dataset.size();
    }
}
