import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameController extends GraphicsProgram {

    private Board board;

    private static final String[] letterConvDict = {"a", "b", "c", "d", "e", "f", "g", "h"};

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int SQUARES_PER_SIDE = 8;
    private static final int SIDE = WIDTH / SQUARES_PER_SIDE;
    private static final int IMG_SIDE = 60;
    private static final int IMG_OFFSET = (SIDE - IMG_SIDE) / 2;
    private boolean turn;

    private GRect[] boardPattern = new GRect[64];

    private ChessObject lastClickedPiece;
    private ChessObject lastMovedPiece;

    // Developer
    private int findPiece(String pos) {
        for (int i = 0; i < this.board.getBoard().length; i++) {
            if (this.board.getBoard()[i] != null && this.board.getBoard()[i].getTeam() == turn) {
                for (int j = 0; j < this.board.getBoard()[i].tryMove(this.board).size(); j++) {
                    int[] testPos = ChessObject.toCoords(this.board.getBoard()[i].tryMove(this.board).get(j));
                    if ((letterConvDict[testPos[0]] + (8 - testPos[1])).equals(pos)) return this.board.getBoard()[i].tryMove(this.board).get(j);
                }
            }
        }
        return -1;
    }

    // Developer
    private int IntConvDict (String pos) {
        for (int i = 0; i < letterConvDict.length; i++)
            if (pos.equals(letterConvDict[i])) return i;
        return -1;
    }



    private void createBoard() {

        this.resize(WIDTH, HEIGHT);

        for (int i = 0; i < SQUARES_PER_SIDE; i++) {
            for (int j = 0; j < SQUARES_PER_SIDE; j++) {

                boardPattern[i + 8 * j] = new GRect(i * SIDE, j * SIDE, SIDE, SIDE);
                boardPattern[i + 8 * j].setFilled(true);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) boardPattern[i + 8 * j].setFillColor(Color.WHITE);
                else boardPattern[i + 8 * j].setFillColor(Color.decode("#e3e3e3"));

                add(boardPattern[i + 8 * j]);

            }
        }

    }

    // Developer
    private void commandMove(String pos) {
        this.board.getBoard()[this.findPiece(pos)].moveTo(ChessObject.toPos(this.IntConvDict(pos.split("")[0]), Integer.parseInt(pos.split("")[1])), board);
        this.turn = !this.turn;
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

        if (this.turn) {
            if (this.board.getBoard()[boxClicked] != null && this.board.getBoard()[boxClicked].getTeam() == turn) {

                // Reset board colors
                for (int i = 0; i < SQUARES_PER_SIDE; i++) {
                    for (int j = 0; j < SQUARES_PER_SIDE; j++) {
                        if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) boardPattern[i + 8 * j].setFillColor(Color.WHITE);
                        else boardPattern[i + 8 * j].setFillColor(Color.decode("#e3e3e3"));
                    }
                }

                // Possible moves
                ArrayList<Integer> possibleMoves = this.board.getBoard()[boxClicked].tryMove(this.board);

                for (int i: possibleMoves) {

                    int xCoord = i % 8;
                    int yCoord = i / 8;

                    boardPattern[xCoord + SQUARES_PER_SIDE * yCoord].setFillColor(Color.decode("#d1ecff"));

                    System.out.println("Got to step 2");
                }
            }
        } else {

        }

    }

    public void run() {

        addMouseListeners();

        this.board = new Board();
        this.turn = true;

        this.createBoard();
        this.drawBoard(this.board);
        this.commandMove("d4");

    }
}
