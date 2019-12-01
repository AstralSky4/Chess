import java.util.ArrayList;

public class Pawn extends ChessObject {

    private boolean jumped = false;

    void setJumped(boolean pawnJump) { this.jumped =  pawnJump; }

    Pawn(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<>();

        // Pawn moves upwards if it is white (team = true)
        if (this.getTeam()) {

            // Checks if it can move directly forward
            if (board.getBoard()[this.getPosition() - 8] == null) possibleMoves.add(this.getPosition() - 8);

            // Checks for enemy pieces diagonally to the left
            if (this.getPosition() % 8 != 0) {
                if ((board.getBoard()[this.getPosition() - 9] != null && !board.getBoard()[this.getPosition() - 9].getTeam()) || ((board.getBoard()[this.getPosition() - 1] instanceof Pawn) && !board.getBoard()[this.getPosition() - 1].getTeam() && ((Pawn) board.getBoard()[this.getPosition() - 1]).jumped)) {
                    possibleMoves.add(this.getPosition() - 9);
                }
            }

            // Checks for enemy pieces diagonally to the right
            if (this.getPosition() % 8 != 7) {
                if ((board.getBoard()[this.getPosition() - 7] != null && !board.getBoard()[this.getPosition() - 7].getTeam()) || ((board.getBoard()[this.getPosition() + 1] instanceof Pawn) && !board.getBoard()[this.getPosition() + 1].getTeam() && ((Pawn) board.getBoard()[this.getPosition() + 1]).jumped)) {
                    possibleMoves.add(this.getPosition() - 7);
                }
            }

            // If pawn hasn't moved, check if moving 2 spots forward is possible
            if (this.getPosition() <= 55 && this.getPosition() >= 48) {
                if (board.getBoard()[this.getPosition() - 8] == null && board.getBoard()[this.getPosition() - 16] == null) {
                    possibleMoves.add(this.getPosition() - 16);
                }
            }

        // Pawn moves downwards if it is black (team = false)
        } else {

            // Checks if it can move directly downward
            if (board.getBoard()[this.getPosition() + 8] == null) possibleMoves.add(this.getPosition() + 8);

            // Checks for enemy pieces diagonally to the right
            if (this.getPosition() % 8 != 7) {
                if (board.getBoard()[this.getPosition() + 9] != null && board.getBoard()[this.getPosition() + 9].getTeam() || ((board.getBoard()[this.getPosition() + 1] instanceof Pawn) && board.getBoard()[this.getPosition() + 1].getTeam() && ((Pawn) board.getBoard()[this.getPosition() + 1]).jumped)) {
                    possibleMoves.add(this.getPosition() + 9);
                }
            }

            // Checks for enemy pieces diagonally to the left
            if (this.getPosition() % 8 != 0) {
                if (board.getBoard()[this.getPosition() + 7] != null && board.getBoard()[this.getPosition() + 7].getTeam() || ((board.getBoard()[this.getPosition() - 1] instanceof Pawn) && board.getBoard()[this.getPosition() - 1].getTeam() && ((Pawn) board.getBoard()[this.getPosition() - 1]).jumped)) {
                    possibleMoves.add(this.getPosition() + 7);
                }
            }

            // If pawn hasn't moved, check if moving 2 spots downward is possible
            if (this.getPosition() <= 15 && this.getPosition() >= 8) {
                if (board.getBoard()[this.getPosition() + 8] == null && board.getBoard()[this.getPosition() + 16] == null) {
                    possibleMoves.add(this.getPosition() + 16);
                }
            }
        }
        return possibleMoves;
    }
}
