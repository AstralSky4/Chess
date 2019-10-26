import java.util.ArrayList;

public class Rook extends ChessObject {

    private boolean firstMove;

    Rook(int position, boolean team) {
        super(position, team);
        this.firstMove = true;
    }

    boolean getFirstMove() { return this.firstMove; }

    void setFirstMove() { this.firstMove = false; }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<>();

        // Checks for moves to the right of the rook
        for (int i = this.getPosition(); i % 8 < 7; i++) {
            // Stops when a piece of the same color is found
            if (board.getBoard()[i].getTeam() == this.getTeam()) {
                break;
            } else {
                possibleMoves.add(i);
            }
        }

        // Checks for moves to the left of the rook
        for (int i = this.getPosition(); i % 8 > 0; i--) {
            // Stops when a piece of the same color is found
            if (board.getBoard()[i].getTeam() == this.getTeam()) {
                break;
            } else {
                possibleMoves.add(i);
            }
        }

        // Checks for moves above the rook
        for (int i = this.getPosition(); i >= 8; i -= 8) {
            // Stops when a piece of the same color is found
            if (board.getBoard()[i].getTeam() == this.getTeam()) {
                break;
            } else {
                possibleMoves.add(i);
            }
        }

        // Checks for moves below the rook
        for (int i = this.getPosition(); i <= 55; i += 8) {
            // Stops when a piece of the same color is found
            if (board.getBoard()[i].getTeam() == this.getTeam()) {
                break;
            } else {
                possibleMoves.add(i);
            }
        }

        return possibleMoves;
    }
}
