import java.util.ArrayList;

public class Rook extends ChessObject {

    Rook(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) { return null; }
}