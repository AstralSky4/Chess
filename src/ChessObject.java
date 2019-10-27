import acm.graphics.GImage;

import java.util.ArrayList;

public abstract class ChessObject {

    private int position;
    private boolean team; // true = white, false = black

    ChessObject(int position, boolean team) {
        this.position = position;
        this.team = team;
    }

    // Takes in the position in the board and returns X, Y coordinate array.
    static int[] toCoords(int pos) {
        return new int[]{pos % 8, pos / 8};
    }

    // Takes X, Y coordinates and returns position in array.
    static int toPos(int x, int y) {
        return (x + (8 * y));
    }

    boolean getTeam() { return this.team; }

    int getPosition() { return this.position; }

    abstract public ArrayList<Integer> tryMove(Board board);

    void moveTo(int position, Board board) {
        board.removePiece(this.getPosition());

        board.addPiece(position, this);

        this.position = position;

        if (this instanceof Rook) ((Rook) this).setFirstMove();
        if (this instanceof King) ((King) this).setFirstMove();
    }
}
