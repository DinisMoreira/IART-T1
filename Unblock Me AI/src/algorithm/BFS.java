package algorithm;

import elements.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class BFS extends Algorithm {

    public BFS(Board board) {
        super(board);
    }

    public Boolean solve(int maxDepth){

        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);
        ArrayList<Move> pastMoves = new ArrayList<Move>();

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards, pastMoves);

        Vertex solution = exploreRoot(root, maxDepth);

        if(solution == null){
            System.out.println("Could not find a solution with DFS algorithm, Max depth = " + maxDepth);
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

    //STILL DEPTH
    public Vertex exploreGraph(int maxDepth){
        Vertex vertex;
        int idx = 0;


        do{
            vertex = allVertexes.get(idx);

            vertex.setVisited(true);

            if(vertex.getBoard().checkVictory()){
                return vertex;
            }

            if(vertex.getDepth() < maxDepth){
                generateVertexChildren(vertex);
            }

            idx++;
        }while(allVertexes.size() >= (idx -1));

        return null;
    }


}