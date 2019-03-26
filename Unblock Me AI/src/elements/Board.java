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
    // Represents the piece which needs to be moved to the target position
    private Piece mainPiece;

    public Board(Position targetPosition, int heigth, int width, Piece mainPiece) {
        this.targetPosition = targetPosition;
        this.mainPiece = mainPiece;
        this.heigth = heigth;
        this.width = width;
        board = new char[this.heigth][this.width];
        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }
        boardPieces = new ArrayList<Piece>();
    }

    public void printBoard() {
        for (char[] heightIterator : board) {
            System.out.print("#");
            System.out.print(heightIterator);
            System.out.println("#");
        }
    }

    public void addPiece(Piece piece) {
        boardPieces.add(piece);
        updateBoard();
    }

    public void updateBoard() {
        /*
         * int xPosition; int yPosition; boolean isHorizontal; int pieceSize; for (Piece
         * piece : boardPieces) { xPosition = piece.getPosition().getX(); yPosition =
         * piece.getPosition().getY(); isHorizontal = piece.isPieceHorizontal();
         * pieceSize = piece.getSize(); for (int sizeIterator = 0; sizeIterator <
         * piece.getSize(); ++sizeIterator) { this.board[yPosition][xPosition] =
         * piece.getIdentificationLetter(); if (isHorizontal) xPosition++; else
         * yPosition++; } }
         */

        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }

        for (int i = 0; i < this.boardPieces.size(); i++) {
            if (this.boardPieces.get(i).isPieceHorizontal()) {
                for (int j = 0; j < this.boardPieces.get(i).getSize(); j++) {
                    int x = this.boardPieces.get(i).getPosition().getX();
                    int y = this.boardPieces.get(i).getPosition().getY();

                    this.board[y][x + j] = this.boardPieces.get(i).getIdentificationLetter();
                }
            } else {
                for (int j = 0; j < this.boardPieces.get(i).getSize(); j++) {
                    int x = this.boardPieces.get(i).getPosition().getX();
                    int y = this.boardPieces.get(i).getPosition().getY();

                    this.board[y + j][x] = this.boardPieces.get(i).getIdentificationLetter();
                }
            }
        }
    }

    public boolean canPieceMoveUp(Piece piece, int distance) {
        if (piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX();
            int yPosition = piece.getPosition().getY();
            for (int yIterator = 1; yIterator <= distance; ++yIterator)
                if (board[xPosition][yPosition - yIterator] != '.')
                    return false;
            return true;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public boolean canPieceMoveDown(Piece piece, int distance) {
        if (piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX();
            int yPosition = piece.getPosition().getY() + piece.getSize() - 1;
            for (int yIterator = 1; yIterator <= distance; ++yIterator)
                if (board[xPosition][yPosition + yIterator] != '.')
                    return false;
            return true;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public boolean canPieceMoveLeft(Piece piece, int distance) {
        if (!piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX();
            int yPosition = piece.getPosition().getY();
            for (int xIterator = 1; xIterator <= distance; ++xIterator)
                if (board[xPosition - xIterator][yPosition] != '.')
                    return false;
            return true;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public boolean canPieceMoveRight(Piece piece, int distance) {
        if (!piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getPosition().getX() + piece.getSize() - 1;
            int yPosition = piece.getPosition().getY();
            for (int xIterator = 1; xIterator <= distance; ++xIterator)
                if (board[xPosition + xIterator][yPosition] != '.')
                    return false;
            return true;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public char[][] getBoard() {
        return this.board;
    }

    public ArrayList<ArrayList<Move>> getAllMoves() {

        ArrayList<ArrayList<Move>> allMoves;
        allMoves = new ArrayList<ArrayList<Move>>();

        for (int i = 0; i < this.boardPieces.size(); i++) {
            allMoves.add(getAllPieceMoves(this, this.boardPieces.get(i)));
        }

        // allMoves.get(0).get(0).getNewBoard().printBoard();

        return allMoves;

    }

    public ArrayList<Move> getAllPieceMoves(Board board, Piece piece) {

        ArrayList<Move> allPieceMoves;
        allPieceMoves = new ArrayList<Move>();
        int distance = 1;

        if (piece.isPieceHorizontal()) {

            // Gets all "left" an horizontal piece can make
            while (canPieceMoveLeft(piece, distance)) {
                allPieceMoves.add(new Move(board, piece, distance, 'l'));
                distance++;
            }

            distance = 1;

            // Gets all "right" moves an horizontal piece can make
            while (canPieceMoveRight(piece, distance)) {
                Move m = new Move(board, piece, distance, 'r');
                allPieceMoves.add(m);

                board = m.getNewBoard();

                m.getNewBoard().printBoard();

                Move n = new Move(board, piece, distance, 'l');

                distance++;
            }

        } else {

            // Gets all "up" moves a vertical piece can make
            while (canPieceMoveUp(piece, distance)) {
                allPieceMoves.add(new Move(board, piece, distance, 'u'));
                distance++;
            }

            distance = 1;

            // Gets all "down" moves a vertical piece can make
            while (canPieceMoveDown(piece, distance)) {
                allPieceMoves.add(new Move(board, piece, distance, 'd'));
                distance++;
            }

        }

        allPieceMoves.get(1).getNewBoard().printBoard();

        return allPieceMoves;
    }

}