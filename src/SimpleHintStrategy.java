public class SimpleHintStrategy implements HintStrategy {

    @Override
    public String makeHint(Position guess, Position treasure) {
        int rowDiff = Math.abs(guess.getRow() - treasure.getRow());
        int colDiff = Math.abs(guess.getCol() - treasure.getCol());
        int distance = rowDiff + colDiff;

        if (distance == 0) {
            return "You found the treasure!";
        } else if (distance == 1) {
            return "Very close!";
        } else if (distance <= 3) {
            return "Close!";
        } else {
            return "Far!";
        }
    }
}
