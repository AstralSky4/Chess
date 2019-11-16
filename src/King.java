import java.util.ArrayList;

public class King extends ChessObject {

    private boolean firstMove;

    King(int position, boolean team) {
        super(position, team);
        this.firstMove = true;
    }

    public boolean getFirstMove() {
        return this.firstMove;
    }

    void setFirstMove() {
        this.firstMove = false;
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<>();

        // Checks if 1 to the left is possible move
        if (this.getPosition() % 8 != 0 && (board.getBoard()[this.getPosition() - 1] == null || board.getBoard()[this.getPosition() - 1].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 1);
        }

        // Checks if 1 up 1 left is a possible move
        if (this.getPosition() % 8 != 0 && this.getPosition() % 8 != 1 && this.getPosition() >= 8 && (board.getBoard()[this.getPosition() - 9] == null || board.getBoard()[this.getPosition() - 9].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 9);
        }

        // Checks if 1 up is a possible move
        if (this.getPosition() >= 8 && (board.getBoard()[this.getPosition() - 8] == null || board.getBoard()[this.getPosition() - 8].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 8);
        }

        // Checks if 1 up 1 right is a possible move
        if (this.getPosition() >= 8 && this.getPosition() % 8 != 7 && (board.getBoard()[this.getPosition() - 7] == null || board.getBoard()[this.getPosition() - 7].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 7);
        }

        // Checks if 1 right is a possible move
        if (this.getPosition() % 8 != 7 && (board.getBoard()[this.getPosition() + 1] == null || board.getBoard()[this.getPosition() + 1].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 1);
        }

        // Checks if 1 right 1 down is a possible move
        if (this.getPosition() % 8 != 7 && this.getPosition() <= 55 && (board.getBoard()[this.getPosition() + 9] == null || board.getBoard()[this.getPosition() + 9].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 9);
        }

        // Checks if 1 down is a possible move
        if (this.getPosition() <= 55 && (board.getBoard()[this.getPosition() + 8] == null || board.getBoard()[this.getPosition() + 8].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 8);
        }

        // Check if 1 down 1 left is a possible move
        if (this.getPosition() <= 55 && this.getPosition() % 8 != 0 && (board.getBoard()[this.getPosition() + 7] == null || board.getBoard()[this.getPosition() + 7].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 7);
        }

        // TODO: Cannot castle out of or through check
        // Castling for white king
        if (this.getTeam()) {
            if (this.firstMove && board.getBoard()[63] instanceof Rook && ((Rook) board.getBoard()[63]).getFirstMove()) {
                if (board.getBoard()[61] == null && board.getBoard()[62] == null) {
                    possibleMoves.add(62);
                }
            }
            if (this.firstMove && board.getBoard()[56] instanceof Rook && ((Rook) board.getBoard()[56]).getFirstMove()) {
                if (board.getBoard()[57] == null && board.getBoard()[58] == null && board.getBoard()[59] == null) {
                    possibleMoves.add(58);
                }
            }

            // Castling for black king
        } else {
            if (this.firstMove && board.getBoard()[7] instanceof Rook && ((Rook) board.getBoard()[7]).getFirstMove()) {
                if (board.getBoard()[5] == null && board.getBoard()[6] == null) {
                    possibleMoves.add(6);
                }
            }
            if (this.firstMove && board.getBoard()[0] instanceof Rook && ((Rook) board.getBoard()[0]).getFirstMove()) {
                if (board.getBoard()[1] == null && board.getBoard()[2] == null && board.getBoard()[3] == null) {
                    possibleMoves.add(2);
                }
            }
        }

        return possibleMoves;
    }
}
