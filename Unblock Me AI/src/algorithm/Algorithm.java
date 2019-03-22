package algorithm;

import elements.Board;

public abstract class Algorithm {
    private Board initialBoard;

    Algorithm(Board board) {
        this.initialBoard = board;
    }
}