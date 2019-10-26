import java.util.ArrayList;

public class Pawn extends ChessObject {

    Pawn(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();
        if (this.getTeam()) {
            if (board.getBoard()[this.getPosition() - 8] == null) {
                possibleMoves.add(this.getPosition() - 8);
            }

            if (this.getPosition() % 8 != 0) {
                if (!board.getBoard()[this.getPosition() - 7].getTeam()) {
                    possibleMoves.add(this.getPosition() - 7);
                }
            }

            if (this.getPosition() % 7 != 0) {
                if (!board.getBoard()[this.getPosition() - 9].getTeam()) {
                    possibleMoves.add(this.getPosition() - 9);
                }
            }

            if (this.getPosition() <= 55 && this.getPosition() >= 48) {
                if (board.getBoard()[this.getPosition() - 8] == null && board.getBoard()[this.getPosition() - 16] == null) {
                    possibleMoves.add(this.getPosition() - 16);
                }
            }
        } else {
            if (board.getBoard()[this.getPosition() + 8] == null) {
                possibleMoves.add(this.getPosition() + 8);
            }

            if (this.getPosition() % 8 != 0) {
                if (board.getBoard()[this.getPosition() + 9].getTeam()) {
                    possibleMoves.add(this.getPosition() + 9);
                }
            }

            if (this.getPosition() % 7 != 0) {
                if (board.getBoard()[this.getPosition() + 7].getTeam()) {
                    possibleMoves.add(this.getPosition() + 7);
                }
            }

            if (this.getPosition() <= 15 && this.getPosition() >= 8) {
                if (board.getBoard()[this.getPosition() + 8] == null && board.getBoard()[this.getPosition() + 16] == null) {
                    possibleMoves.add(this.getPosition() + 16);
                }
            }
        }
        return possibleMoves;
    }

    // TODO: Override moveTo()
}
