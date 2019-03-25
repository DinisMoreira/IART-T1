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
        this.newBoard = oldBoard;
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

        return true;
    }

    public boolean moveUp(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveUp(p, d))
            return false;

        p.getPosition().setY(p.getPosition().getY() - d);
        return true;
    }

    public boolean moveDown(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveDown(p, d))
            return false;

        p.getPosition().setY(p.getPosition().getY() + d);
        return true;
    }

    public boolean moveLeft(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveLeft(p, d))
            return false;

        p.getPosition().setX(p.getPosition().getX() - d);
        return true;
    }

    public boolean moveRight(Piece p, int d) {
        if (!this.oldBoard.canPieceMoveRight(p, d))
            return false;

        deleteLastPosition(p, piece.getPosition().getX(), piece.getPosition().getY());
        p.getPosition().setX(p.getPosition().getX() + d);
        return true;
    }

    public void deleteLastPosition(Piece p, int x, int y) {

        for (int sizeIterator = 0; sizeIterator < piece.getSize(); ++sizeIterator) {
            newBoard.getBoard()[y][x] = '.';
            if (p.isPieceHorizontal())
                x++;
            else
                y++;
        }
    }

    public Board getNewBoard() {
        return this.newBoard;
    }

}