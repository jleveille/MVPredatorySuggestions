import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        double error = getPercentError("data/DataSet.txt");
        System.out.println(error);
}

    private static double getPercentError(String filename) {
        double incorrect = 0;
        ArrayList<TrainingExample> list = TextLib.readDataSet(filename);
        for (TrainingExample example : list) {
            String text = example.getText();
            boolean experimental = PredatorySuggestions.isPredatory(text);
            if (experimental != example.getIsPredatory()) incorrect++;
        }
        return incorrect/list.size() *100;
    }
}
