package elements;

import java.util.ArrayList;

public class Vertex {

    private Board board;
    private int depth;
    private Boolean visited;
    private Vertex parent;
    private ArrayList<Board> pastBoards;
    private ArrayList<Vertex> neighbours;

    public Vertex(Board board, int depth, ArrayList<Board> pastBoards){
        this.board = board;
        this.depth = depth;
        this.visited = false;
        this.pastBoards = pastBoards;
        this.neighbours = new ArrayList<Vertex>();
    }

    public Board getBoard(){
        return board;
    }

    public int getDepth(){
        return depth;
    }

    public Boolean getVisited(){
        return visited;
    }

    public ArrayList<Board> getPastBoards(){
        return pastBoards;
    }

    public ArrayList<Vertex> getNeighbours(){
        return neighbours;
    }

}