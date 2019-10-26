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

    // Images from https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
    private static final String whiteBishop = "images/Chess_blt60.png";
    private static final String blackBishop = "images/Chess_bdt60.png";
    private static final String whiteKing = "images/Chess_klt60.png";
    private static final String blackKing = "images/Chess_kdt60.png";
    private static final String whiteKnight = "images/Chess_nlt60.png";
    private static final String blackKnight = "images/Chess_ndt60.png";
    private static final String whitePawn = "images/Chess_plt60.png";
    private static final String blackPawn = "images/Chess_pdt60.png";
    private static final String whiteQueen = "images/Chess_qlt60.png";
    private static final String blackQueen = "images/Chess_qdt60.png";
    private static final String whiteRook = "images/Chess_rlt60.png";
    private static final String blackRook = "images/Chess_rdt60.png";

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

                    if (board.getBoard()[i + SQUARES_PER_SIDE * j].getTeam()) { // white team

                        switch (board.getBoard()[i + SQUARES_PER_SIDE * j].getClass().getSimpleName()) {
                            case "Bishop":
                                GImage wBishop = new GImage(whiteBishop, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(wBishop);
                                break;
                            case "King":
                                GImage wKing = new GImage(whiteKing, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(wKing);
                                break;
                            case "Knight":
                                GImage wKnight = new GImage(whiteKnight, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(wKnight);
                                break;
                            case "Pawn":
                                GImage wPawn = new GImage(whitePawn, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(wPawn);
                                break;
                            case "Queen":
                                GImage wQueen = new GImage(whiteQueen, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(wQueen);
                                break;
                            case "Rook":
                                GImage wRook = new GImage(whiteRook, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(wRook);
                                break;
                        }

                    } else { // black team

                        switch (board.getBoard()[i + SQUARES_PER_SIDE * j].getClass().getSimpleName()) {
                            case "Bishop":
                                GImage bBishop = new GImage(blackBishop, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(bBishop);
                                break;
                            case "King":
                                GImage bKing = new GImage(blackKing, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(bKing);
                                break;
                            case "Knight":
                                GImage bKnight = new GImage(blackKnight, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(bKnight);
                                break;
                            case "Pawn":
                                GImage bPawn = new GImage(blackPawn, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(bPawn);
                                break;
                            case "Queen":
                                GImage bQueen = new GImage(blackQueen, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(bQueen);
                                break;
                            case "Rook":
                                GImage bRook = new GImage(blackRook, i * SIDE + IMG_OFFSET, j * SIDE + IMG_OFFSET);
                                add(bRook);
                                break;
                        }

                    }
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

        System.out.println("Clicked " + this.board.getBoard()[boxClicked].getPosition());

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
            }
            // show possible moves
        } else {
            // possible move? --> do stuff
            // if was moved, change lastMovedPiece
        }

    }


}
