package algorithm;

import elements.*;
import java.util.ArrayList;

public class BFS extends Algorithm {

    public BFS(Board board) {
        super(board);
    }

    public void getHint(int maxDepth) {

        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);
        ArrayList<Move> pastMoves = new ArrayList<Move>();

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards, pastMoves, 0);

        Vertex solution = exploreRoot(root, maxDepth);

        System.out.println("\nSuggested Move: ");
        System.out.println("Piece: " + solution.getPastMoves().get(0).getNewPiece().getIdentificationLetter()
                + ", Distance: " + solution.getPastMoves().get(0).getDistance() + ", Direction: "
                + solution.getPastMoves().get(0).getDirection() + "\n");
        solution.getPastBoards().get(1).printBoard();
        System.out.println("\n");

        return;
    }

    public Boolean solve(int maxDepth) {

        ArrayList<Board> pastBoards = new ArrayList<Board>();
        pastBoards.add(this.initialBoard);
        ArrayList<Move> pastMoves = new ArrayList<Move>();

        Vertex root = new Vertex(this.initialBoard, 0, pastBoards, pastMoves, 0);

        Vertex solution = exploreRoot(root, maxDepth);

        if (solution == null) {
            System.out.println("Could not find a solution with Breadth First algorithm, Max depth = " + maxDepth);
            return false;
        } else {
            System.out.println("Found Solution!");
            // SHOW SOLUTION
            System.out.println("*********************");
            solution.displayPastBoards();
            System.out.println();
            System.out.println("* * * * * * * * * * *");
            System.out.println("Number of vertexes created: " + this.allVertexes.size());
            System.out.println("Number of vertexes seen: " + this.numVertexesSeen);
            return true;
        }

    }

    public Vertex exploreRoot(Vertex root, int maxDepth) {
        Vertex solution = null;

        this.allVertexes.add(root);

        if (maxDepth < 0) {
            return null;
        } else {
            solution = exploreGraph(maxDepth);
        }

        return solution;
    }

    public Vertex exploreGraph(int maxDepth) {
        Vertex vertex;
        int idx = 0;

        do {
            vertex = allVertexes.get(idx);

            vertex.setVisited(true);
            this.numVertexesSeen++;

            if (vertex.getBoard().checkVictory()) {
                return vertex;
            }

            if (vertex.getDepth() < maxDepth) {
                generateVertexChildren(vertex);
            }

            idx++;
        } while (allVertexes.size() >= (idx - 1));

        return null;
    }

    // Generates all Children of a Vertex (with all the possíble moves in the Board)
    public void generateVertexChildren(Vertex vertex) {
        ArrayList<ArrayList<Move>> allMoves = vertex.getBoard().getAllMoves();

        for (int i = 0; i < allMoves.size(); i++) {
            for (int j = 0; j < allMoves.get(i).size(); j++) {
                // Add current board to the new pastBoards List for children
                ArrayList<Board> newPastBoards = new ArrayList<Board>();
                ArrayList<Move> newPastMoves = new ArrayList<Move>();

                for (int k = 0; k < vertex.getPastBoards().size(); k++) {
                    newPastBoards.add(vertex.getPastBoards().get(k));
                }
                newPastBoards.add(allMoves.get(i).get(j).getNewBoard());

                for (int k = 0; k < vertex.getPastMoves().size(); k++) {
                    newPastMoves.add(vertex.getPastMoves().get(k));
                }
                newPastMoves.add(allMoves.get(i).get(j));

                // Create new Vertex
                Vertex newChild = new Vertex(allMoves.get(i).get(j).getNewBoard(), vertex.getDepth() + 1, newPastBoards, newPastMoves, 0);

                if (checkRepeatedVertex(newChild)) {

                    // ADD CHILD TO STACK
                    this.allVertexes.add(newChild);

                    // ADD CHILD TO Parent's neighbours
                    vertex.getNeighbours().add(newChild);
                }
            }
        }

        return;
    }

}