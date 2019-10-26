import java.util.ArrayList;

public class Knight extends ChessObject{

    Knight(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<Integer>();

        if ((this.getPosition() - 17 >= 0 && this.getPosition() % 8 != 0) && (board.getBoard()[this.getPosition() - 17] == null || board.getBoard()[this.getPosition() - 17].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 17);
        }

        if ((this.getPosition() - 15 >= 0 && this.getPosition() % 7 != 0) && (board.getBoard()[this.getPosition() - 15] == null || board.getBoard()[this.getPosition() - 15].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 15);
        }

        if ((this.getPosition() >= 8 && !(this.getPosition() % 6 == 0 || this.getPosition() % 7 == 0)) && (board.getBoard()[this.getPosition() - 6] == null || board.getBoard()[this.getPosition() - 6].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() - 6);
        }

        if ((this.getPosition() <= 55 && !(this.getPosition() % 6 == 0 || this.getPosition() % 7 == 0)) && (board.getBoard()[this.getPosition() + 10] == null || board.getBoard()[this.getPosition() + 10].getTeam() != this.getTeam())) {
            possibleMoves.add(this.getPosition() + 10);
        }

        return possibleMoves;
    }
}
