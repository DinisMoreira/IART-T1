package algorithm;

import elements.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class DFS extends Algorithm {

    public DFS(Board board) {
        super(board);
    }

    public Boolean solve(int maxDepth){

        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);
        ArrayList<Move> pastMoves = new ArrayList<Move>();

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards, pastMoves);

        Vertex solution = exploreRoot(root, maxDepth);

        if(solution == null){
            System.out.println("Could not find a solution with Depth First algorithm, Max depth = " + maxDepth);
            return false;
        }
        else{
            System.out.println("Found Solution!");
            //SHOW SOLUTION
            System.out.println("*********************");
            solution.displayPastBoards();
            System.out.println();  
            System.out.println("*********************");  
            return true;
        }

        
        /*this.stack.push(root);

        Vertex poppedVertex = this.stack.pop();
        System.out.println("\nPopped Board");
        poppedVertex.getBoard().printBoard();
        System.out.println(poppedVertex.getDepth());
        */
    }

    public Vertex exploreRoot(Vertex root, int maxDepth){
        Vertex solution = null;

        this.stack.push(root);
        this.allVertexes.add(root);

        if(maxDepth < 0){
            return null;
        }
        else{
            solution = exploreGraph(maxDepth);
        }

        return solution;
    }


    public Vertex exploreGraph(int maxDepth){
        Vertex vertex;
        if(this.stack.size() <= 0){
            return null;
        }
        
        vertex = this.stack.pop();

        /*System.out.println("\nPopped Board");
        vertex.getBoard().printBoard();
        System.out.println(vertex.getDepth());
        System.out.println(stack.size());*/

        vertex.setVisited(true);

        //PRINT VERTEX BOARD
        //displayVertex(vertex);
        
        if(vertex.getBoard().checkVictory()){
            return vertex;
        }

        if(maxDepth > vertex.getDepth()){
            generateVertexChildren(vertex);
        }

        return exploreGraph(maxDepth);
    }

}