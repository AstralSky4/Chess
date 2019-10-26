import acm.graphics.GImage;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.awt.*;

public class GameController extends GraphicsProgram {

    private Board board;

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private static final int SQUARES_PER_SIDE = 8;
    private static final int SIDE = WIDTH / SQUARES_PER_SIDE;
    private static final int IMG_SIDE = 60;

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
                if (board.getBoard()[i + SQUARES_PER_SIDE * j] != null) {
                    switch (board.getBoard()[i + SQUARES_PER_SIDE * j].getClass().getSimpleName()) {
                        case "Bishop":
                            if (board.getBoard()[i + SQUARES_PER_SIDE * j].getTeam()) { // white bishop
                                GImage wBishop = new GImage("images/Chess_blt60.png", i * SIDE + (SIDE - IMG_SIDE) / 2, j * SIDE + (SIDE - IMG_SIDE) / 2);
                                add(wBishop);
                            } else { // black bishop
                                GImage bBishop = new GImage("images/Chess_bdt60.png", i * SIDE + (SIDE - IMG_SIDE) / 2, j * SIDE + (SIDE - IMG_SIDE) / 2);
                                add(bBishop);
                            }
                            break;
                        case "King":
                            if (board.getBoard()[i + SQUARES_PER_SIDE * j].getTeam()) { // white king
                                GImage wKing = new GImage("images/Chess_klt60.png", i * SIDE + (SIDE - IMG_SIDE) / 2, j * SIDE + (SIDE - IMG_SIDE) / 2);
                                add(wKing);
                            } else { // black king
                                GImage bKing = new GImage("images/Chess_kdt60.png", i * SIDE + (SIDE - IMG_SIDE) / 2, j * SIDE + (SIDE - IMG_SIDE) / 2);
                                add(bKing);
                            }
                            break;
                        case "Knight":

                            break;

                        case "Pawn":

                            break;

                        case "Queen":

                            break;

                        case "Rook":

                            break;

                    }
                }
            }
        }

    }

    public void run() {

        this.board = new Board();

        this.createBoard();
        this.drawBoard(this.board);

    }


}
