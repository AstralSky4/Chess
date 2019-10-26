class Board {

    private ChessObject[] board = new ChessObject[64];

    Board() {
        for (int i = 8; i < 16; i++) {
            board[i] = new Pawn(i, false);
        }
        for (int i = 48; i < 56; i++) {
            board[i] = new Pawn(i, true);
        }

        board[0] = new Rook(0, false);
        board[7] = new Rook(7, false);
        board[56] = new Rook(56, true);
        board[63] = new Rook(63, true);

        board[1] = new Knight(1, false);
        board[6] = new Knight(6, false);
        board[57] = new Knight(57, true);
        board[62] = new Knight(62, true);

        board[2] = new Bishop(2, false);
        board[5] = new Bishop(5, false);
        board[58] = new Bishop(58, true);
        board[61] = new Bishop(61, true);

        board[3] = new Queen(3, false);
        board[59] = new Queen(59, true);

        board[4] = new King(4, false);
        board[60] = new King(60, true);
    }

    ChessObject[] getBoard() { return board; }

    void addPiece(int position, ChessObject piece) {
        this.board[position] = piece;
    }

    void removePiece(int position) {
        this.board[position] = null;
    }
}
