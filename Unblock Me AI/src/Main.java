import java.util.Scanner;
import elements.*;
import algorithm.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int maxDepth = 0;
        int algNum = 0;


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

        while(true){
            System.out.println();
            board.printBoard();
            System.out.println();
            System.out.println("1 - Depth First");
            System.out.println("2 - Iterative Deepening Depth First");
            System.out.println("9 - Exit");
            System.out.println("Choose an algorithm:");
            algNum = scn.nextInt();

            if(algNum < 3){
                System.out.println("Maximum Graph Depth: ");
                maxDepth = scn.nextInt();
            }

        
            System.out.println();

            switch(algNum){
                case 1:
                    DFS depth = new DFS(board);
                    depth.solve(maxDepth);
                    break;
            

                case 2:

                    for(int i = 0; i <= maxDepth; i++){
                        DFS iterDepth = new DFS(board);
                        if(iterDepth.solve(i)){
                            break;
                        }
                    }
                    break;

                case 9:
                return;
            
                default:
            }

        }



    }
}