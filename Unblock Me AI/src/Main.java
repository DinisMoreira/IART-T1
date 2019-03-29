import elements.*;
import algorithm.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example board:
        /* #AA....#
        ** #...B..#
        ** #CXXBFT#
        ** #C..BF.#
        ** #C.....#
        ** #......#
        */

        Piece key = new Piece(1, 2, 2, true, 'X');
        Piece a   = new Piece(0, 0, 2, true, 'A');
        Piece b   = new Piece(3, 1, 3, false, 'B');
        Piece c   = new Piece(0, 2, 3, false, 'C');
        Piece d   = new Piece(4, 2, 2, false, 'D');
        Piece e   = new Piece(2, 0, 2, true, 'E');
        Piece f   = new Piece(2, 4, 3, true, 'F');
        
        Board board = new Board(5, 2, 6, 6, key);
        board.addPiece(a);
        board.addPiece(b);
        board.addPiece(c);
        board.addPiece(d);
        board.addPiece(e);
        board.addPiece(f);

        for(int i = 0; i < 100; i++){
            DFS depth = new DFS(board);
            if(depth.solve(i)){
                break;
            }
        }



    }
}