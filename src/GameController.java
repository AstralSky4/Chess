import acm.graphics.GImage;
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
    private static final int SIZE = WIDTH / SQUARES_PER_SIDE;
    private static final int IMG_SIDE = 60;
    private static final int IMG_OFFSET = (SIZE - IMG_SIDE) / 2;
    private boolean turn;

    private int wKingPos = 60;
    private int bKingPos = 4;

    private GRect[] boardPattern = new GRect[64];

    private ChessObject lastClickedPiece;

    // Developer
    private String[] convertString(String pos) {
        String[] out = new String[3];
        pos = pos.replace("x", "");
        pos = pos.replace("+", "");

        if (pos.length() == 2){
            out[0] = pos;
        }
        else if (pos.length() == 3) {
            String[] split = pos.split("");

            out[0] = split[0] + split[1];
            out[1] = split[2];
        }

        return out;
    }

    // Developer
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
    private int intConvDict(String pos) {
        for (int i = 0; i < letterConvDict.length; i++)
            if (pos.equals(letterConvDict[i])) return i;
        return -1;
    }

    // Developer
    private void commandMove(String pos) {
        int from = this.findPiece(pos);
        int to = ChessObject.toPos(this.intConvDict(pos.split("")[0]), Integer.parseInt(pos.split("")[1]));
        this.board.getBoard()[from].moveTo(to, board);
        this.turn = !this.turn;
        if (this.board.getBoard()[to] != null) removeImage(to);
        this.moveImage(from, to);
    }

    private void createBoard() {

        this.resize(WIDTH, HEIGHT);

        for (int i = 0; i < SQUARES_PER_SIDE; i++) {
            for (int j = 0; j < SQUARES_PER_SIDE; j++) {

                boardPattern[i + 8 * j] = new GRect(i * SIZE, j * SIZE, SIZE, SIZE);
                boardPattern[i + 8 * j].setFilled(true);

                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) boardPattern[i + 8 * j].setFillColor(Color.WHITE);
                else boardPattern[i + 8 * j].setFillColor(Color.decode("#e3e3e3"));

                add(boardPattern[i + 8 * j]);

                if (board.getBoard()[i + SQUARES_PER_SIDE * j] != null) { // if there's a piece
                    // Images from https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
                    String name = board.getBoard()[i + SQUARES_PER_SIDE * j].getClass().getSimpleName();
                    boolean team = board.getBoard()[i + SQUARES_PER_SIDE * j].getTeam();
                    String target = ((name.equals("Knight")) ? 'n' : Character.toLowerCase(name.charAt(0))) +(team ? "l" : "d");
                    add(new GImage("images/Chess_" + target + "t60.png", i * SIZE + IMG_OFFSET, j * SIZE + IMG_OFFSET));
                }
            }
        }

    }

    // Move image
    private void moveImage(int current, int moveTo) {

        double xCoordCurrent = (current % SQUARES_PER_SIDE + 0.5) * SIZE;
        double yCoordCurrent = (current / SQUARES_PER_SIDE + 0.5) * SIZE;
        int xCoordNext = moveTo % SQUARES_PER_SIDE * SIZE + IMG_OFFSET;
        int yCoordNext = moveTo / SQUARES_PER_SIDE * SIZE + IMG_OFFSET;

        GImage selectedImage = (GImage) getElementAt(xCoordCurrent, yCoordCurrent);
        selectedImage.setLocation(xCoordNext, yCoordNext);
        selectedImage.sendToFront();
    }

    // Remove image
    private void removeImage(int place) {

        double xCoordCurrent = (place % SQUARES_PER_SIDE + 0.5) * SIZE;
        double yCoordCurrent = (place / SQUARES_PER_SIDE + 0.5) * SIZE;

        GImage selectedImage = (GImage) getElementAt(xCoordCurrent, yCoordCurrent);
        selectedImage.setLocation(800, 0); // off canvas
    }

    // mouse click
    public void mouseClicked(MouseEvent e) {
        // position in grid:
        int xBox = e.getX() / SIZE;
        int yBox = e.getY() / SIZE;
        int boxClicked = xBox + SQUARES_PER_SIDE * yBox;

        System.out.println("Clicked: " + boxClicked);

        if (this.lastClickedPiece == null) {
            if (this.board.getBoard()[boxClicked] != null) {
                if (this.board.getBoard()[boxClicked].getTeam() == turn) {

                    if (turn) {
                        for (int i = 32; i < 40; i++) {
                            if (this.board.getBoard()[i] instanceof Pawn && this.board.getBoard()[i].getTeam() != turn) {
                                ((Pawn) board.getBoard()[i]).setJumped(false);
                            }
                        }
                    } else {
                        for (int i = 40; i < 48; i++) {
                            if (this.board.getBoard()[i] instanceof Pawn && this.board.getBoard()[i].getTeam() != turn) {
                                ((Pawn) board.getBoard()[i]).setJumped(false);
                            }
                        }
                    }

                    ArrayList<Integer> testMoves = this.board.getBoard()[boxClicked].tryMove(this.board);
                    ArrayList<Integer> possibleMoves  = new ArrayList<>();

                    for (int move: testMoves) {
                        if (this.board.getBoard()[move] != null) {

                            ChessObject temp = this.board.getBoard()[move];
                            this.board.getBoard()[boxClicked].moveTo(move, this.board);

                            if (this.board.getBoard()[boxClicked] instanceof King) { if (!checkCheck(move)) possibleMoves.add(move);}
                            else { if (!checkCheck()) possibleMoves.add(move); }

                            System.out.println("boxClicked null: " + (this.board.getBoard()[boxClicked] == null));

                            this.board.getBoard()[move].moveTo(boxClicked, this.board);
                            this.board.addPiece(move, temp);

                        } else {

                            this.board.getBoard()[boxClicked].moveTo(move, this.board);

                            if (this.board.getBoard()[boxClicked] instanceof King) { if (!checkCheck(move)) possibleMoves.add(move);}
                            else { if (!checkCheck()) possibleMoves.add(move); }

                            System.out.println("boxClicked null 2: " + (this.board.getBoard()[boxClicked] == null));

                            this.board.getBoard()[move].moveTo(boxClicked, this.board);
                        }
                    }

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
                if (this.board.getBoard()[boxClicked] != null) removeImage(boxClicked);
                moveImage(this.lastClickedPiece.getPosition(), boxClicked);

                if (this.lastClickedPiece instanceof  Pawn && Math.abs(this.lastClickedPiece.getPosition() - boxClicked) == 16) ((Pawn) this.lastClickedPiece).setJumped(true);

                if (this.lastClickedPiece instanceof King) {
                    if (this.lastClickedPiece.getTeam()) {
                        wKingPos = boxClicked;
                    } else {
                        bKingPos = boxClicked;
                    }
                    if (Math.abs(this.lastClickedPiece.getPosition() - boxClicked) == 2) {
                        if (boxClicked == 62) {
                            this.board.getBoard()[63].moveTo(61, this.board);
                            moveImage(63, 61);
                        } else if (boxClicked == 58) {
                            this.board.getBoard()[56].moveTo(59, this.board);
                            moveImage(56, 59);
                        } else if (boxClicked == 6) {
                            this.board.getBoard()[7].moveTo(5, this.board);
                            moveImage(7, 5);
                        } else {
                            this.board.getBoard()[0].moveTo(3, this.board);
                            moveImage(0, 3);
                        }
                    }
                }

                // check en passant
                // if is pawn, moved diagonal and nothing in new spot it's en passant
                if (this.lastClickedPiece instanceof Pawn && this.board.getBoard()[boxClicked] == null) {
                    if (this.lastClickedPiece.getPosition() - boxClicked == 9) {
                        removeImage(this.lastClickedPiece.getPosition() - 1);
                        this.board.removePiece(this.lastClickedPiece.getPosition() - 1);
                    } else if (this.lastClickedPiece.getPosition() - boxClicked == 7) {
                        removeImage(this.lastClickedPiece.getPosition() + 1);
                        this.board.removePiece(this.lastClickedPiece.getPosition() + 1);
                    } else if (this.lastClickedPiece.getPosition() - boxClicked == -7) {
                        removeImage(this.lastClickedPiece.getPosition() - 1);
                        this.board.removePiece(this.lastClickedPiece.getPosition() - 1);
                    } else if (this.lastClickedPiece.getPosition() - boxClicked == -9) {
                        removeImage(this.lastClickedPiece.getPosition() + 1);
                        this.board.removePiece(this.lastClickedPiece.getPosition() + 1);
                    }
                }

                this.lastClickedPiece.moveTo(boxClicked, this.board);

                // Reset pawns en passant
                if (turn) {
                    for (int i = 24; i < 32; i++) {
                        if (this.board.getBoard()[i] instanceof Pawn && this.board.getBoard()[i].getTeam() != turn) {
                            ((Pawn) this.board.getBoard()[i]).setJumped(false);
                        }
                    }
                } else {
                    for (int i = 32; i < 40; i++) {
                        if (this.board.getBoard()[i] instanceof Pawn && this.board.getBoard()[i].getTeam() != turn) {
                            ((Pawn) this.board.getBoard()[i]).setJumped(false);
                        }
                    }
                }

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

    private boolean checkCheck() {
        boolean inCheck = false;
        if (this.board.getBoard()[this.wKingPos].getTeam() == turn) {
            if (this.findPiece(letterConvDict[ChessObject.toCoords(this.wKingPos)[0]] + (8 - ChessObject.toCoords(this.wKingPos)[1])) != -1) {
                inCheck = true;
            }
        } else {
            if (this.findPiece(letterConvDict[ChessObject.toCoords(this.bKingPos)[0]] + (8 - ChessObject.toCoords(this.bKingPos)[1])) != -1) {
                inCheck = true;
            }
        }
        System.out.println("In check: " + inCheck);
        return inCheck;
    }

    private boolean checkCheck(int position) {
        boolean inCheck = false;
        if (this.findPiece(letterConvDict[ChessObject.toCoords(position)[0]] + (8 - ChessObject.toCoords(position)[1])) != -1) {
            inCheck = true;
        }
        return inCheck;
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
