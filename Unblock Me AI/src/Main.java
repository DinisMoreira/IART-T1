import elements.*;

public class Main {
    public static void main(String[] args) {
        Board testBoard = new Board(new Position(5, 2), 6, 6);

        Piece p1 = new Piece(new Position(0, 0), 2, true, 'A');
        Piece p2 = new Piece(new Position(0, 1), 2, true, 'B');
        Piece p3 = new Piece(new Position(0, 2), 3, false, 'C');

        testBoard.addPiece(p1);
        testBoard.addPiece(p2);
        testBoard.addPiece(p3);

        testBoard.printBoard();
    }
}