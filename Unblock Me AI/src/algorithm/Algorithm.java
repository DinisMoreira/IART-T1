package algorithm;

import elements.Board;
import java.util.Stack;

import elements.Vertex;

public abstract class Algorithm {
    protected Board initialBoard;
    protected Stack<Vertex> stack;

    Algorithm(Board board) {
        this.initialBoard = board;
        this.stack = new Stack<Vertex>();
    }

}