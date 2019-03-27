package elements;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    // Keeps track of all pieces
    private List<Piece> boardPieces;
    // Explicit view of board. Useful to simplify collisions
    private char[][] board;
    // Both represent the board's dimensions
    private int heigth;
    private int width;
    // Represents the piece which needs to be moved to the target position
    private Piece mainPiece;
    // Stores the x coordinate of the target position
    private int targetX;
    // Stores the y coordinate of the target position
    private int targetY;

    public Board(int targetX, int targetY, int heigth, int width, Piece mainPiece) {
        this.mainPiece = mainPiece;
        this.heigth = heigth;
        this.width = width;
        board = new char[this.heigth][this.width];
        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }
        boardPieces = new ArrayList<Piece>();
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public Board(Board b) {
        targetX = b.targetX;
        targetY = b.targetY;
        heigth = b.heigth;
        width = b.width;
        mainPiece = b.mainPiece;
        board = new char[this.heigth][this.width];
        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }
        boardPieces = b.boardPieces;

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
         * piece : boardPieces) { xPosition = piece.getX(); yPosition = piece.getY();
         * isHorizontal = piece.isPieceHorizontal(); pieceSize = piece.getSize(); for
         * (int sizeIterator = 0; sizeIterator < piece.getSize(); ++sizeIterator) {
         * this.board[yPosition][xPosition] = piece.getIdentificationLetter(); if
         * (isHorizontal) xPosition++; else yPosition++; } }
         */

        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }

        for (int i = 0; i < this.boardPieces.size(); i++) {
            if (this.boardPieces.get(i).isPieceHorizontal()) {
                for (int j = 0; j < this.boardPieces.get(i).getSize(); j++) {
                    int x = this.boardPieces.get(i).getX();
                    int y = this.boardPieces.get(i).getY();

                    this.board[y][x + j] = this.boardPieces.get(i).getIdentificationLetter();
                }
            } else {
                for (int j = 0; j < this.boardPieces.get(i).getSize(); j++) {
                    int x = this.boardPieces.get(i).getX();
                    int y = this.boardPieces.get(i).getY();

                    this.board[y + j][x] = this.boardPieces.get(i).getIdentificationLetter();
                }
            }
        }
    }

    public boolean canPieceMoveUp(Piece piece, int distance) {
        if (piece.isPieceHorizontal() || distance <= 0)
            return false;

        try {
            int xPosition = piece.getX();
            int yPosition = piece.getY();
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
            int xPosition = piece.getX();
            int yPosition = piece.getY() + piece.getSize() - 1;
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
            int xPosition = piece.getX();
            int yPosition = piece.getY();
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
            int xPosition = piece.getX() + piece.getSize() - 1;
            int yPosition = piece.getY();
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

        return allMoves;

    }

    public ArrayList<Move> getAllPieceMoves(Board board, Piece piece) {

        ArrayList<Move> allPieceMoves;
        allPieceMoves = new ArrayList<Move>();
        int distance = 1;
        int storedX = piece.getX();
        int storedY = piece.getY();

        if (piece.isPieceHorizontal()) {

            // Gets all "left" an horizontal piece can make
            while (canPieceMoveLeft(piece, distance)) {
                Move m = new Move(board, piece, distance, 'l');
                allPieceMoves.add(m);
                updateBoard();

                // Undo the move just made in order to correctly generate the remaining moves
                new Move(board, piece, distance, 'r');
                updateBoard();

                distance++;
            }

            distance = 1;

            // Gets all "right" moves an horizontal piece can make
            while (canPieceMoveRight(piece, distance)) {
                Move m = new Move(board, piece, distance, 'r');
                allPieceMoves.add(m);
                updateBoard();

                // Undo the move just made in order to correctly generate the remaining moves
                Move n = new Move(board, piece, distance, 'l');
                updateBoard();

                distance++;

            }

        } else {

            // Gets all "up" moves a vertical piece can make
            while (canPieceMoveUp(piece, distance)) {
                Move m = new Move(board, piece, distance, 'u');
                allPieceMoves.add(m);
                updateBoard();

                // Undo the move just made in order to correctly generate the remaining moves
                Move n = new Move(board, piece, distance, 'd');
                updateBoard();

                distance++;
            }

            distance = 1;

            // Gets all "down" moves a vertical piece can make
            while (canPieceMoveDown(piece, distance)) {
                Move m = new Move(board, piece, distance, 'd');
                allPieceMoves.add(m);
                updateBoard();

                // Undo the move just made in order to correctly generate the remaining moves
                Move n = new Move(board, piece, distance, 'u');
                updateBoard();

                distance++;
            }

        }

        piece.setX(storedX);
        piece.setY(storedY);
        board.updateBoard();

        System.out.println(allPieceMoves.size());

        return allPieceMoves;
    }

    public int getTargetX() {
        return this.targetX;
    }

    public int getTargetY() {
        return this.targetY;
    }

    public boolean checkVictory() {
        if (this.mainPiece.getX() == this.targetX - 1 && this.mainPiece.getY() == this.targetY) {
            return true;
        }
        return false;
    }

}