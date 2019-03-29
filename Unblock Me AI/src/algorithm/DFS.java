package algorithm;

import elements.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class DFS extends Algorithm {
    public int index = 0;

    public DFS(Board board) {
        super(board);
    }

    public Boolean solve(int maxDepth){



        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards);


        
        Vertex solution = exploreRoot(root, maxDepth);

        if(solution == null){
            System.out.println("Could not find a solution with DFS algorithm, Max depth = " + maxDepth);
            return false;
        }
        else{
            System.out.println("Found Solution!");
            //SHOW SOLUTION
            solution.displayPastBoards();    
            return true;
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
        this.allVertexes.add(root);


        if(maxDepth <= 0){
            return null;
        }
        else{
            solution = exploreGraph(maxDepth);

        }

        return solution;
    }

    
    //UNTESTED & TO DO
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

    //UNTESTED & TO DO
    public void generateVertexChildren(Vertex vertex){
        ArrayList<ArrayList<Move>> allMoves = vertex.getBoard().getAllMoves();

        /*System.out.println();
        System.out.println("//////////////////////////////////");
        System.out.println("Children of:");
        vertex.getBoard().printBoard();
        System.out.println("-----------------------------------");*/

        
        /*System.out.println("+++++++++ PARENT PAST BOARDS +++++++++");
        vertex.displayPastBoards();
        System.out.println("++++++++++++++++++++++++++++++++++++++");*/

        for(int i=0; i<allMoves.size();i++){
            for(int j=0; j<allMoves.get(i).size();j++){
                //Add current board to the new pastBoards List for children
                ArrayList<Board> newPastBoards = new ArrayList<Board>();
                for(int k = 0; k < vertex.getPastBoards().size(); k++){
                    newPastBoards.add(vertex.getPastBoards().get(k));
                }
                newPastBoards.add(allMoves.get(i).get(j).getNewBoard());


                
                if(checkRepeatedVertex(allMoves.get(i).get(j).getNewBoard())){
                    //Create new Vertex
                    Vertex newChild = new Vertex(allMoves.get(i).get(j).getNewBoard(), vertex.getDepth()+1, newPastBoards);

                    //ADD CHILD TO STACK
                    this.stack.push(newChild);
                    this.allVertexes.add(newChild);

                    //ADD CHILD TO vertex.neighbours
                    vertex.getNeighbours().add(newChild);

                    

                    //SEE CHILD
                    /*newChild.getBoard().printBoard();
                    System.out.println("NEW CHILD");
                    System.out.println("******** PAST BOARDS *********");
                    newChild.displayPastBoards();
                    System.out.println("******************************");*/
                }

                
                //newPastBoards.remove(newPastBoards.size()-1);
            }
        }

        //System.out.println("//////////////////////////////////");


        return;
    }

    //UNTESTED
    public Boolean checkRepeatedVertex(Board board){
        
        for(int i = 0; i < this.allVertexes.size(); i++){
            if(board.equals(this.allVertexes.get(i).getBoard())){
                //System.out.println("REPEATED BOARD!!!");
                return false;
            }
        }

        return true;
    }
    

    public void displayVertex(Vertex vertex){
        System.out.println();
        System.out.println("V. index: " + index);
        vertex.getBoard().printBoard();
        System.out.println("Depth: " + vertex.getDepth());
        System.out.println("Stack Size: " + this.stack.size());
        System.out.println("Number of V.: " + this.allVertexes.size());           
        index++;
    }


}