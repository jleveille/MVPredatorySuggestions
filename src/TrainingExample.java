public class TrainingExample {
    private String text;
    private boolean isPredatory;

    public TrainingExample(String text, boolean isPredatory) {
        this.text = text;
        this.isPredatory = isPredatory;
    }

    public void setPredatory(boolean predatory) {
        isPredatory = predatory;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsPredatory() {
        return isPredatory;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text + ":" + isPredatory;
    }
}
