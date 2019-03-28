package algorithm;

import elements.Board;
import java.util.Stack;
import java.util.ArrayList;

import elements.Vertex;

public abstract class Algorithm {
    protected Board initialBoard;
    protected Stack<Vertex> stack;
    protected ArrayList<Vertex> allVertexes = new ArrayList<Vertex>();

    Algorithm(Board board) {
        this.initialBoard = board;
        this.stack = new Stack<Vertex>();
    }

}