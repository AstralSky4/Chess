import acm.program.GraphicsProgram;
import acm.graphics.*;
import java.awt.Color;

public class Board {

    private ChessObject[] board = new ChessObject[64];

    Board() {

    }

    public ChessObject[] getBoard() { return board; }

    public void addPiece(int position, ChessObject piece) {
        this.board[position] = piece;
    }

    public void removePiece (int position) {
        this.board[position] = null;
    }
}
