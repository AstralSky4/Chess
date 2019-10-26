import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameController extends GraphicsProgram {

    private Board board;

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int SQUARES_PER_SIDE = 8;
    private static final int SIDE = WIDTH / SQUARES_PER_SIDE;
    private static final int IMG_SIDE = 60;
    private static final int IMG_OFFSET = (SIDE - IMG_SIDE) / 2;

    private ChessObject lastClickedPiece;
    private ChessObject lastMovedPiece;

    private void createBoard() {

        this.resize(WIDTH, HEIGHT);

        for (int i = 0; i < SQUARES_PER_SIDE; i++) {
            for (int j = 0; j < SQUARES_PER_SIDE; j++) {

                GRect positionRect = new GRect(i * SIDE, j * SIDE, SIDE, SIDE);
                positionRect.setFilled(true);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) positionRect.setFillColor(Color.WHITE);
                else positionRect.setFillColor(Color.GRAY);

                add(positionRect);

            }
        }

    }

    private void drawBoard(Board board) {

        for (int i = 0; i < SQUARES_PER_SIDE; i++) {
            for (int j = 0; j < SQUARES_PER_SIDE; j++) {
                if (board.getBoard()[i + SQUARES_PER_SIDE * j] != null) { // if there's a piece
                    // Images from https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
                    String name = board.getBoard()[i + SQUARES_PER_SIDE * j].getClass().getSimpleName();
                    boolean team = board.getBoard()[i + SQUARES_PER_SIDE * j].getTeam();
                    String target = ((name.equals("Knight")) ? 'n' : Character.toLowerCase(name.charAt(0))) +(team ? "l" : "d");
                    add(new GImage("images/Chess_" + target + "t60.png", i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET));
                }
            }
        }

    }

    public void run() {

        addMouseListeners();

        this.board = new Board();

        this.createBoard();
        this.drawBoard(this.board);

    }

    // mouse click
    public void mouseClicked(MouseEvent e) {
        // coords of mouse:
        e.getX();
        e.getY();
        // position in grid:
        int xBox = e.getX() / SIDE;
        int yBox = e.getY() / SIDE;
        int boxClicked = xBox + SQUARES_PER_SIDE * yBox;

        System.out.println("Clicked " + (this.board.getBoard()[boxClicked] != null));

        if (lastMovedPiece.getTeam() != this.board.getBoard()[boxClicked].getTeam()) {
            // check possible moves
            ArrayList<Integer> possibleMoves = this.board.getBoard()[boxClicked].tryMove(this.board);
            for (int i: possibleMoves) {

                int xCoord = i % 8;
                int yCoord = i / 8;

                GOval moveCircle = new GOval(xCoord * SIDE, yCoord * SIDE, SIDE * 0.6, SIDE * 0.6);
                moveCircle.setFilled(true);
                moveCircle.setFillColor(Color.CYAN);

                add(moveCircle);

                System.out.println("Got to step 2");
            }
            // show possible moves
        } else {
            // possible move? --> do stuff
            // if was moved, change lastMovedPiece
        }

    }


}
