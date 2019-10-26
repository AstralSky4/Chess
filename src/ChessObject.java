import java.util.ArrayList;

public abstract class ChessObject {

    private int position;
    private boolean team; // true = white, false = black

    ChessObject(int position, boolean team) {
        this.position = position;
        this.team = team;
    }

    public boolean getTeam() { return this.team; }

    public int getPosition() { return this.position; }

    abstract public ArrayList<Integer> tryMove(Board board);

    public void moveTo(int position, Board board) {
        board.removePiece(this.getPosition());
        board.addPiece(position, this);
    }
}
