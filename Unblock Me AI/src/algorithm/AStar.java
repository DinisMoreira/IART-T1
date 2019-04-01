package algorithm;

import elements.*;
import java.util.ArrayList;

public class AStar extends Algorithm {

    public AStar(Board board) {
        super(board);
    }

    public Boolean solve(int maxDepth){

        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);
        ArrayList<Move> pastMoves = new ArrayList<Move>();

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards, pastMoves);

        Vertex solution = exploreRoot(root, maxDepth);

        if(solution == null){
            System.out.println("Could not find a solution with A Star algorithm, Max depth = " + maxDepth);
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

    }

    public Vertex exploreRoot(Vertex root, int maxDepth){
        Vertex solution = null;

        this.allVertexes.add(root);
        this.unexploredVertexes.add(root);

        if(maxDepth < 0){
            return null;
        }
        else{
            solution = exploreGraph(maxDepth);
        }

        return solution;
    }

    public Vertex exploreGraph(int maxDepth){
        Vertex vertex = unexploredVertexes.get(0);
        int closestIdx;

        do{
            closestIdx = getVertexIdxOfPossibleBestSolution();

            if(closestIdx != 2147483647){
                //System.out.println("ClosestIdx: " + closestIdx);
                //System.out.println("allVertexes.size: " + allVertexes.size());
                vertex = unexploredVertexes.get(closestIdx);
                unexploredVertexes.remove(closestIdx);
            }

            vertex.setVisited(true);

            if(vertex.getBoard().checkVictory()){
                return vertex;
            }

            if(vertex.getDepth() < maxDepth){
                generateVertexChildren(vertex);
            }

        }while(unexploredVertexes.size() > 0);

        return null;
    }

    public int getVertexIdxOfPossibleBestSolution(){
        int closestVal = 2147483647;
        int closestIdx = 2147483647;

        for(int i = 0; i < unexploredVertexes.size(); i++){
            if(closestVal > unexploredVertexes.get(i).getoptSolDistance()){
                closestVal = unexploredVertexes.get(i).getoptSolDistance();
                closestIdx = i;
            }
        }

        return closestIdx;
    }


}