package algorithm;

import elements.*;
import java.util.ArrayList;

public class Greedy extends Algorithm {

    public Greedy(Board board) {
        super(board);
    }

    public Boolean solve(int maxDepth){

        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);
        ArrayList<Move> pastMoves = new ArrayList<Move>();

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards, pastMoves, 2);

        Vertex solution = exploreRoot(root, maxDepth);

        if(solution == null){
            System.out.println("Could not find a solution with Greedy algorithm, Max depth = " + maxDepth);
            return false;
        }
        else{
            System.out.println("Found Solution!");
            //SHOW SOLUTION
            System.out.println("*********************");
            solution.displayPastBoards();
            System.out.println();  
            System.out.println("* * * * * * * * * * *");
            System.out.println("Number of vertexes created: " + this.allVertexes.size());
            System.out.println("Number of vertexes seen: " + this.numVertexesSeen);
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
                vertex = unexploredVertexes.get(closestIdx);
                unexploredVertexes.remove(closestIdx);
            }

            vertex.setVisited(true);
            this.numVertexesSeen++;

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

    //Generates all Children of a Vertex (with all the possíble moves in the Board)
    public void generateVertexChildren(Vertex vertex){
        ArrayList<ArrayList<Move>> allMoves = vertex.getBoard().getAllMoves();

        for(int i=0; i<allMoves.size();i++){
            for(int j=0; j<allMoves.get(i).size();j++){
                //Add current board to the new pastBoards List for children
                ArrayList<Board> newPastBoards = new ArrayList<Board>();
                ArrayList<Move> newPastMoves = new ArrayList<Move>();

                for(int k = 0; k < vertex.getPastBoards().size(); k++){
                    newPastBoards.add(vertex.getPastBoards().get(k));
                }
                newPastBoards.add(allMoves.get(i).get(j).getNewBoard());

                for(int k = 0; k < vertex.getPastMoves().size(); k++){
                    newPastMoves.add(vertex.getPastMoves().get(k));
                }
                newPastMoves.add(allMoves.get(i).get(j));

                 //Create new Vertex
                Vertex newChild = new Vertex(allMoves.get(i).get(j).getNewBoard(), vertex.getDepth()+1, newPastBoards, newPastMoves, 2);

                if(checkRepeatedVertex(newChild)){
                   
                    //ADD CHILD TO allVertexes ARRAYLIST AND unexploredVertexes ARRAYLIST
                    this.allVertexes.add(newChild);
                    this.unexploredVertexes.add(newChild);

                    //ADD CHILD TO Parent's neighbours
                    vertex.getNeighbours().add(newChild);
                }
            }
        }

        return;
    }


}