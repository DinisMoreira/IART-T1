package elements;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Move {

    // Represents the board of the game before the move is applied to a piece
    private Board oldBoard;
    // Represents the board of the game after the move is applied to a piece
    private Board newBoard;
    // Represents the piece to be moved
    private Piece piece;
    // Represents the distance the piece will travel
    private int distance;
    // Represents the direction a piece will be moved in
    private char direction;

    public Move(Board oldBoard, Piece piece, int distance, char direction) {
        this.oldBoard = oldBoard;
        this.newBoard = new Board(this.oldBoard);
        this.piece = piece;
        this.distance = distance;
        this.direction = direction;

        movePiece();
    }

    public boolean movePiece() {
        if (piece.isPieceHorizontal()) {
            if (direction == 'l')
                moveLeft(piece, distance);
            else if (direction == 'r')
                moveRight(piece, distance);
            else
                return false;
        } else {
            if (direction == 'u')
                moveUp(piece, distance);
            else if (direction == 'd')
                moveDown(piece, distance);
            else
                return false;
        }

        this.newBoard.updateBoard();

        return true;
    }

    public boolean moveUp(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveUp(p, d))
            return false;

        p.setY(p.getY() - d);
        return true;
    }

    public boolean moveDown(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveDown(p, d))
            return false;

        p.setY(p.getY() + d);
        return true;
    }

    public boolean moveLeft(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveLeft(p, d))
            return false;

        p.setX(p.getX() - d);
        return true;
    }

    public boolean moveRight(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveRight(p, d))
            return false;

        p.setX(p.getX() + d);
        return true;
    }

    public Board getNewBoard() {
        return this.newBoard;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getDistance() {
        return this.distance;
    }

}