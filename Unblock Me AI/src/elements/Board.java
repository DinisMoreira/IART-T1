package elements;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    // Position to move key piece to.
    private Position targetPosition;
    // Keeps track of all pieces
    private List<Piece> boardPieces;
    // Explicit view of board. Useful to simplify collisions
    private char[][] board;
    // Both represent the board's dimensions
    private int heigth;
    private int width;

    public Board(Position targetPosition, int heigth, int width) {
        this.targetPosition = targetPosition;
        this.heigth = heigth;
        this.width = width;
        board = new char[this.heigth][this.width];
        for(char[] heightIterator : board) {
            Arrays.fill(heightIterator, '0');
        }
        boardPieces = new ArrayList<Piece>();
    }

    public void printBoard() {
        for(char[] heightIterator : board) {
                System.out.print("#");
                System.out.print(heightIterator);
                System.out.println("#");
            }
        }
        
    public void addPiece(Piece piece) {
        boardPieces.add(piece);
        updateBoard();
    }
    
    private void updateBoard() {
        int xPosition;
        int yPosition;
        Boolean isHorizontal;
        for(Piece piece : boardPieces) {
            xPosition = piece.getPosition().getX();
            yPosition = piece.getPosition().getY();
            isHorizontal = piece.isPieceHorizontal();
            for(int sizeIterator = 0; sizeIterator < piece.getSize(); ++sizeIterator) {
                this.board[yPosition][xPosition] = piece.getIdentificationLetter();
                if(isHorizontal)
                xPosition++;
                else
                yPosition++;
            }
        }
    }

    public Boolean canPieceMoveUp(Piece piece, int distance) {
        if(piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX();
            int yPosition = piece.getPosition().getY();
            for(int yIterator = 1; yIterator <= distance; ++yIterator)
                if(board[xPosition][yPosition - yIterator] != '0')
                    return false;
            return true;
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public Boolean canPieceMoveDown(Piece piece, int distance) {
        if(piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX();
            int yPosition = piece.getPosition().getY() + piece.getSize() - 1;
            for(int yIterator = 1; yIterator <= distance; ++yIterator)
                if(board[xPosition][yPosition + yIterator] != '0')
                    return false;
            return true;
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public Boolean canPieceMoveLeft(Piece piece, int distance) {
        if(! piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX();
            int yPosition = piece.getPosition().getY();
            for(int xIterator = 1; xIterator <= distance; ++xIterator)
                if(board[xPosition - xIterator][yPosition] != '0')
                    return false;
            return true;
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public Boolean canPieceMoveRight(Piece piece, int distance) {
        if(! piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX() + piece.getSize() - 1;
            int yPosition = piece.getPosition().getY();
            for(int xIterator = 1; xIterator <= distance; ++xIterator)
                if(board[xPosition + xIterator][yPosition] != '0')
                    return false;
            return true;
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }
}