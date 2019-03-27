package algorithm;

import elements.Board;

import elements.*;
import java.util.ArrayList;

public class BFS extends Algorithm {
     public BFS(Board board) {
        super(board);
    }

    public void solve(){
        System.out.println("\nInitial Board");
        this.initialBoard.printBoard();



        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards);

        int maxDepth = 0;

        
        exploreGraph(root, 2);
        
        /*this.stack.push(root);

        Vertex poppedVertex = this.stack.pop();
        System.out.println("\nPopped Board");
        poppedVertex.getBoard().printBoard();
        System.out.println(poppedVertex.getDepth());
        */
    }

    //UNTESTED & TO DO
    public Vertex exploreGraph(Vertex root, int maxDepth){
        this.stack.push(root);

        System.out.println("\nRoot Board");
        root.getBoard().printBoard();

        if(maxDepth <= 0){
            return root;
        }
        else{

        }


        return root;

    }

    //UNTESTED & TO DO
    public ArrayList<Vertex> generateVertexChildren(Vertex vertex){
        ArrayList<Vertex> children = new ArrayList<Vertex>();
        ArrayList<Board> newPastBoards = new ArrayList<Board>();
        


        ArrayList<ArrayList<Move>> allMoves = vertex.getBoard().getAllMoves();

        for(int i=0; i<allMoves.size();i++){
            for(int j=0; j<allMoves.get(i).size();j++){
                //Create new pastBoards List
                newPastBoards = vertex.getPastBoards();
                newPastBoards.add(allMoves.get(i).get(j).getNewBoard());

                
                //Create new Vertex
                Vertex newChild = new Vertex(allMoves.get(i).get(j).getNewBoard(), vertex.getDepth()+1, vertex.getPastBoards());
                if(checkRepeatedVertex(newChild)){
                    //ADD CHILD TO STACK
                    //ADD CHILD TO vertex.neighbours
                }

                
                
            }
        }

        return children;


    }

    //UNTESTED
    public Boolean checkRepeatedVertex(Vertex vertex){
        //TO DO
        return true;
    }
}