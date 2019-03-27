import elements.*;
import algorithm.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Piece p1 = new Piece(0, 0, 2, true, 'A');
        // Piece mainPiece = new Piece(0, 1, 2, true, 'Z');
        // Piece p3 = new Piece(0, 2, 3, false, 'C');

        Board testBoard = new Board(5, 2, 6, 6, p1);

        testBoard.addPiece(p1);
        // testBoard.addPiece(mainPiece);
        // testBoard.addPiece(p3);

        testBoard.printBoard();
        System.out.println();

        ArrayList<ArrayList<Move>> allMoves;

        allMoves = testBoard.getAllMoves();

        for (int i = 0; i < allMoves.size(); i++) {
            for (int j = 0; j < allMoves.get(i).size(); j++) {
                allMoves.get(i).get(j).getNewBoard().printBoard();
                System.out.println();
            }
        }

        return;
    }

}