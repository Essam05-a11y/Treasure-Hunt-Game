public enum Difficulty {

    EASY("Easy 4x4", 4),
    MEDIUM("Medium 5x5", 5),
    HARD("Hard 6x6", 6);

    private String text;
    private int size;

    Difficulty(String text, int size) {
        this.text = text;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return text;
    }
}
