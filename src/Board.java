class Board {

    private ChessObject[] board = new ChessObject[64];

    Board() {

    }

    ChessObject[] getBoard() { return board; }

    void addPiece(int position, ChessObject piece) {
        this.board[position] = piece;
    }

    void removePiece(int position) {
        this.board[position] = null;
    }
}
