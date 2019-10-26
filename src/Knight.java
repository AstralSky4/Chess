import com.sun.deploy.security.BadCertificateDialog;

import java.util.ArrayList;

public class Knight extends ChessObject{

    Knight(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<>();

        // Checks if up 2 and left 1 is possible
        if ((this.getPosition() - 17 >= 0 && this.getPosition() % 8 != 0) && (board.getBoard()[this.getPosition() - 17] == null || board.getBoard()[this.getPosition() - 17].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 17);
        }

        // Checks if up 2 and right 1 is possible
        if ((this.getPosition() - 15 >= 0 && this.getPosition() % 8 != 7) && (board.getBoard()[this.getPosition() - 15] == null || board.getBoard()[this.getPosition() - 15].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 15);
        }

        // Checks if up 1 and right 2 is possible
        if ((this.getPosition() >= 8 && !(this.getPosition() % 8 == 6 || this.getPosition() % 8 == 7)) && (board.getBoard()[this.getPosition() - 6] == null || board.getBoard()[this.getPosition() - 6].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 6);
        }

        // Checks if down 1 and right 2 is possible
        if ((this.getPosition() <= 55 && !(this.getPosition() % 8 == 6 || this.getPosition() % 8 == 7)) && (board.getBoard()[this.getPosition() + 10] == null || board.getBoard()[this.getPosition() + 10].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 10);
        }

        // Checks if down 2 and right 1 is possible
        if ((this.getPosition() <= 47 && this.getPosition() % 8 != 7) && (board.getBoard()[this.getPosition() + 17] == null) || board.getBoard()[this.getPosition() + 17].getTeam() != this.getTeam()) {
            possibleMoves.add(this.getPosition() + 17);
        }

        // Checks if down 2 and left 1 is possible
        if ((this.getPosition() <= 47 && this.getPosition() % 8 != 0) && (board.getBoard()[this.getPosition() + 15] == null || board.getBoard()[this.getPosition() + 15].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 15);
        }

        // Checks if down 1 and left 2 is possible
        if ((this.getPosition() <= 55 && !(this.getPosition() % 8 == 0 || this.getPosition() % 8 == 1)) && (board.getBoard()[this.getPosition() + 6] == null || board.getBoard()[this.getPosition() + 6].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 6);
        }

        // Checks if up 1 and left 2 is possible
        if ((this.getPosition() >= 10 && !(this.getPosition() % 8 == 0 || this.getPosition() % 8 == 1)) && (board.getBoard()[this.getPosition() - 10] == null || board.getBoard()[this.getPosition() - 10].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 10);
        }

        return possibleMoves;
    }
}
