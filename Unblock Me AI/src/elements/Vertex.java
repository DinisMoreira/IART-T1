package elements;

import java.util.ArrayList;

public class Vertex {

    private Board board;
    private int depth;
    private Boolean visited;
    private Vertex parent;
    private ArrayList<Board> pastBoards;
    private ArrayList<Move> pastMoves;
    private int optSolDistance;
    private ArrayList<Vertex> neighbours;

    public Vertex(Board board, int depth, ArrayList<Board> pastBoards, ArrayList<Move> pastMoves, int type){
        this.board = board;
        this.depth = depth;
        this.visited = false;
        this.pastBoards = pastBoards;
        this.pastMoves = pastMoves;
        if(type == 1)
            this.optSolDistance = depth + board.getDistanceToTarget() + (board.getWidth() * board.getAmountPiecesToTarget());
        else if(type == 2)
            this.optSolDistance = board.getDistanceToTarget() + (board.getWidth() * board.getAmountPiecesToTarget());
        else if(type == 3)
            this.optSolDistance = depth + board.getDistanceToTarget() + (board.getWidth() * board.getAmountPiecesToTarget());
        else{
            this.optSolDistance = 0;
        }
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

    public void setVisited(Boolean isVisited){
        this.visited = isVisited;
    }

    public ArrayList<Board> getPastBoards(){
        return pastBoards;
    }

    public ArrayList<Move> getPastMoves(){
        return pastMoves;
    }

    public ArrayList<Vertex> getNeighbours(){
        return neighbours;
    }

    public int getoptSolDistance(){
        return optSolDistance;
    }

    public void displayPastBoards(){
        for(int i = 0; i < pastBoards.size(); i++){
                System.out.println();
                if(i > 0){
                    System.out.println("Move " + i + ":");
                    System.out.println("Piece: " + pastMoves.get(i-1).getNewPiece().getIdentificationLetter() + ", Distance: " + pastMoves.get(i-1).getDistance() + ", Direction: " + pastMoves.get(i-1).getDirection() + "\n");
                }
                pastBoards.get(i).printBoard();
            } 
    }

}