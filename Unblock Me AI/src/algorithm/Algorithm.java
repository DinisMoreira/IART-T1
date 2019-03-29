package algorithm;

import elements.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import elements.Vertex;

public abstract class Algorithm {
    
    protected int index = 0;
    protected Board initialBoard;
    protected Stack<Vertex> stack;
    protected ArrayList<Vertex> allVertexes = new ArrayList<Vertex>();

    Algorithm(Board board) {
        this.initialBoard = board;
        this.stack = new Stack<Vertex>();
    }

    //Generates all Children of a Vertex (with all the possíble moves in the Board)
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

                    //ADD CHILD TO STACK and allVertexes ARRAYLIST
                    this.stack.push(newChild);
                    this.allVertexes.add(newChild);

                    //ADD CHILD TO Parent's neighbours
                    vertex.getNeighbours().add(newChild);

                    //SEE CHILD
                    /*newChild.getBoard().printBoard();
                    System.out.println("NEW CHILD");
                    System.out.println("******** PAST BOARDS *********");
                    newChild.displayPastBoards();
                    System.out.println("******************************");*/
                }
            }
        }

        //System.out.println("//////////////////////////////////");

        return;
    }


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