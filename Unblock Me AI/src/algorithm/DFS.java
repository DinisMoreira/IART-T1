package algorithm;

import elements.*;
import java.util.ArrayList;

public class DFS extends Algorithm {
    public DFS(Board board) {
        super(board);
    }

    public void solve(){
        System.out.println("\nInitial Board");
        this.initialBoard.printBoard();



        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards);

        int maxDepth = 2;

        
        Vertex solution = exploreRoot(root, maxDepth);

        if(solution == null){
            System.out.println("Could not fin a solution with DFS algorithm, Max depth = " + maxDepth);
        }
        else{
            //SHOW SOLUTION
        }
        
        /*this.stack.push(root);

        Vertex poppedVertex = this.stack.pop();
        System.out.println("\nPopped Board");
        poppedVertex.getBoard().printBoard();
        System.out.println(poppedVertex.getDepth());
        */
    }

    //UNTESTED & TO DO
    public Vertex exploreRoot(Vertex root, int maxDepth){
        Vertex solution = null;

        this.stack.push(root);

        System.out.println("\nRoot Board");
        root.getBoard().printBoard();

        root.setVisited(true);

        if(maxDepth <= 0){
            return null;
        }
        else{
            solution = exploreGraph(root, maxDepth);

        }

        return solution;
    }

    
    //UNTESTED & TO DO
    public Vertex exploreGraph(Vertex root, int maxDepth){
        return null;
    }

    //UNTESTED & TO DO
    public void generateVertexChildren(Vertex vertex, int maxDepth){
        ArrayList<ArrayList<Move>> allMoves = vertex.getBoard().getAllMoves();


        for(int i=0; i<allMoves.size();i++){
            for(int j=0; j<allMoves.get(i).size();j++){
                //Add current board to the new pastBoards List for children
                ArrayList<Board> newPastBoards = new ArrayList<Board>();
                newPastBoards = vertex.getPastBoards();
                newPastBoards.add(allMoves.get(i).get(j).getNewBoard());

                
                
                if(checkRepeatedVertex(allMoves.get(i).get(j).getNewBoard())){
                    //Create new Vertex
                    Vertex newChild = new Vertex(allMoves.get(i).get(j).getNewBoard(), vertex.getDepth()+1, newPastBoards);

                    //ADD CHILD TO STACK
                    this.stack.push(newChild);

                    //ADD CHILD TO vertex.neighbours
                    vertex.getNeighbours().add(newChild);
                }

                
                newPastBoards.remove(newPastBoards.size()-1);
            }
        }


        return;
    }

    //UNTESTED
    public Boolean checkRepeatedVertex(Board board){
        //TO DO
        return true;
    }



}