import java.util.ArrayList;

public class Bishop extends ChessObject{

    Bishop(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {
        return null;
    }
}
