import elements.*;
import algorithm.*;

public class Main {
    public static void main(String[] args) {
        Piece p1 = new Piece(new Position(0, 0), 2, true, 'A');
        Piece mainPiece = new Piece(new Position(0, 1), 2, true, 'Z');
        Piece p3 = new Piece(new Position(0, 2), 3, false, 'C');

        Board testBoard = new Board(new Position(5, 2), 6, 6, mainPiece);

        testBoard.addPiece(p1);
        testBoard.addPiece(mainPiece);
        testBoard.addPiece(p3);

        testBoard.printBoard();
        System.out.println();

        Move m1 = new Move(testBoard, mainPiece, 2, 'r');
        testBoard = m1.getNewBoard();

        testBoard.updateBoard();

        testBoard.printBoard();
    }
}