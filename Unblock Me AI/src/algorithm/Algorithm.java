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
    protected ArrayList<Vertex> unexploredVertexes = new ArrayList<Vertex>();
    protected int numVertexesSeen = 0;

    Algorithm(Board board) {
        this.initialBoard = board;
        this.stack = new Stack<Vertex>();
    }

    


    public Boolean checkRepeatedVertex(Vertex vertex){
        for(int i = 0; i < this.allVertexes.size(); i++){
            if(vertex.getDepth() >= this.allVertexes.get(i).getDepth() && vertex.getBoard().equals(this.allVertexes.get(i).getBoard())){
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