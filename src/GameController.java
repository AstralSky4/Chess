import acm.graphics.GImage;
import acm.graphics.GPoint;
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
    private int lastClick;

    // Developer
//    private String[] convertString(String pos) {
//        String[] out = new String[3];
//        pos = pos.replace("x", "");
//        pos = pos.replace("+", "");
//
//        if (pos.length() == 2){
//            out[0] = pos;
//        }
//        else if (pos.length() == 3) {
//            String[] split = pos.split("");
//
//            out[0] = split[0] + split[1];
//            out[1] = split[2];
//        }
//
//        return out;
//    }

    private int findPiece(String pos) {
        for (int i = 0; i < this.board.getBoard().length; i++) {
            if (this.board.getBoard()[i] != null && this.board.getBoard()[i].getTeam() == turn) {
                for (int j = 0; j < this.board.getBoard()[i].tryMove(this.board).size(); j++) {
                    int[] testPos = ChessObject.toCoords(this.board.getBoard()[i].tryMove(this.board).get(j));
                    if ((letterConvDict[testPos[0]] + (8 - testPos[1])).equals(pos)) return i;
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

    // Developer
    private void commandMove(String pos) {
        int from = this.findPiece(pos);
        int to = ChessObject.toPos(this.IntConvDict(pos.split("")[0]), Integer.parseInt(pos.split("")[1]));
        this.board.getBoard()[from].moveTo(to, board);
        this.turn = !this.turn;
        this.moveImage(from, to);
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

    // Move image
    private void moveImage(int current, int moveTo) {

        double xCoordCurrent = (current % SQUARES_PER_SIDE + 0.5) * SIDE;
        double yCoordCurrent = (current / SQUARES_PER_SIDE + 0.5) * SIDE;
        int xCoordNext = moveTo % SQUARES_PER_SIDE * SIDE + IMG_OFFSET;
        int yCoordNext = moveTo / SQUARES_PER_SIDE * SIDE + IMG_OFFSET;

        GImage selectedImage = (GImage) getElementAt(xCoordCurrent, yCoordCurrent);
        selectedImage.setLocation(xCoordNext, yCoordNext);
        selectedImage.sendToFront();
    }

    // mouse click
    public void mouseClicked(MouseEvent e) {
        // position in grid:
        int xBox = e.getX() / SIDE;
        int yBox = e.getY() / SIDE;
        int boxClicked = xBox + SQUARES_PER_SIDE * yBox;

        System.out.println("Clicked: " + boxClicked);

        if (this.lastClickedPiece == null) {
            if (this.board.getBoard()[boxClicked] != null) {
                if (this.board.getBoard()[boxClicked].getTeam() == turn) {

                    ArrayList<Integer> possibleMoves = this.board.getBoard()[boxClicked].tryMove(this.board);

                    this.lastClickedPiece = this.board.getBoard()[boxClicked];
                    for (int i : possibleMoves) {

                        int xCoord = i % SQUARES_PER_SIDE;
                        int yCoord = i / SQUARES_PER_SIDE;

                        boardPattern[xCoord + SQUARES_PER_SIDE * yCoord].setFillColor(Color.decode("#d1ecff"));
                    }
                }
            }
        } else {
            if (indexOf(this.board.getBoard()[this.lastClickedPiece.getPosition()].tryMove(this.board), boxClicked)) {

                // Move image
                moveImage(this.lastClickedPiece.getPosition(), boxClicked);

                this.lastClickedPiece.moveTo(boxClicked, this.board);
                turn = !turn;
            }
            this.lastClickedPiece = null;
            // Reset board colors
            for (int i = 0; i < SQUARES_PER_SIDE; i++) {
                for (int j = 0; j < SQUARES_PER_SIDE; j++) {
                    if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) boardPattern[i + 8 * j].setFillColor(Color.WHITE);
                    else boardPattern[i + 8 * j].setFillColor(Color.decode("#e3e3e3"));
                }
            }
        }
    }

    private static boolean indexOf(ArrayList<Integer> possibleMoves, int position) {
        for (int pos: possibleMoves) {
            if (pos == position) return true;
        }
        return false;
    }

    public void run() {

        addMouseListeners();

        this.board = new Board();
        this.turn = true;

        this.createBoard();
//        this.commandMove("d4");
//        this.commandMove("d5");
//        this.commandMove("c4");


    }
}
