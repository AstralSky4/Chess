import java.util.ArrayList;

public class Bishop extends ChessObject {

    Bishop(int position, boolean team) {
        super(position, team);
    }

    @Override
    public ArrayList<Integer> tryMove(Board board) {

        ArrayList<Integer> possibleMoves = new ArrayList<>();

        // Array of directions
        int[][] dir = new int[4][2];
        dir[0][0] = 1;
        dir[0][1] = 1;
        dir[1][0] = 1;
        dir[1][1] = -1;
        dir[2][0] = -1;
        dir[2][1] = 1;
        dir[3][0] = -1;
        dir[3][1] = -1;


        // Loop through each direction
        for (int[] ints : dir) {
            // Set initial X and Y coordinates.
            int xPos = ChessObject.toCoords(this.getPosition())[0];
            int yPos = ChessObject.toCoords(this.getPosition())[1];

            // While: so the bishop stay on the board
            while (((xPos += ints[0]) < 8 && xPos >= 0) && ((yPos += ints[1]) < 8 && yPos >= 0)) {
                // Modify x and y position to move 1 square diagonally in the current direction

                // If something is detected.
                if (board.getBoard()[ChessObject.toPos(xPos, yPos)] != null) {
                    // Check if it is on the same team
                    if (board.getBoard()[ChessObject.toPos(xPos, yPos)].getTeam() != this.getTeam()) {
                        // If it is on the opposite team, make the tile that is on a possible move.
                        possibleMoves.add(ChessObject.toPos(xPos, yPos));
                    }
                    // Break the while loop so you can move past.
                    break;
                } else {
                    // If nothing is detected add that position as a possible move.
                    possibleMoves.add(ChessObject.toPos(xPos, yPos));
                }
            }
        }
        return possibleMoves;
    }
}
