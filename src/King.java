import java.util.ArrayList;

public class King extends ChessObject {

    King(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {
        // TODO: Can't make moves that will put King in check
        return null;
    }
}
