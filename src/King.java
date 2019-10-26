import java.util.ArrayList;

public class King extends ChessObject {

    King(int position, boolean team) {
        super(position, team);
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
        if (this.getPosition() >= 8 && this.getPosition() % 8 != 7 && (board.getBoard()[this.getPosition() - 7].getTeam() != this.getTeam() || board.getBoard()[this.getPosition() - 7] == null)) {
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

        return possibleMoves;
    }
}
