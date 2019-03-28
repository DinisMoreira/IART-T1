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
    private int height;
    private int width;
    // Stores the x coordinate of the target position
    private int targetX;
    // Stores the y coordinate of the target position
    private int targetY;

    public Board(int targetX, int targetY, int height, int width, Piece mainPiece) {
        this.height = height;
        this.width = width;
        board = new char[this.height][this.width];
        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }
        boardPieces = new ArrayList<>();
        boardPieces.add(mainPiece);
        this.updateBoard();
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public Board(Board b) {
        targetX = b.targetX;
        targetY = b.targetY;
        height = b.height;
        width = b.width;
        board = new char[this.height][this.width];
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

        for (char[] heightIterator : board) {
            Arrays.fill(heightIterator, '.');
        }

        for (Piece piece : this.boardPieces) {
            if (piece.isPieceHorizontal()) {
                for (int j = 0; j < piece.getSize(); j++) {
                    int x = piece.getX();
                    int y = piece.getY();

                    this.board[y][x + j] = piece.getIdentificationLetter();
                }
            } else {
                for (int j = 0; j < piece.getSize(); j++) {
                    int x = piece.getX();
                    int y = piece.getY();

                    this.board[y + j][x] = piece.getIdentificationLetter();
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
                if (board[yPosition - yIterator][xPosition] != '.')
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
                if (board[yPosition + yIterator][xPosition] != '.')
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
                if (board[yPosition][xPosition - xIterator] != '.')
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
                if (board[yPosition][xPosition + xIterator] != '.')
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

                // Undo the move just made in order to correctly generate the remaining moves
                new Move(board, piece, distance, 'r');

                distance++;
            }

            distance = 1;

            // Gets all "right" moves an horizontal piece can make
            while (canPieceMoveRight(piece, distance)) {
                Move m = new Move(board, piece, distance, 'r');
                allPieceMoves.add(m);

                // Undo the move just made in order to correctly generate the remaining moves
                Move n = new Move(board, piece, distance, 'l');

                distance++;

            }

        } else {

            // Gets all "up" moves a vertical piece can make
            while (canPieceMoveUp(piece, distance)) {
                Move m = new Move(board, piece, distance, 'u');
                allPieceMoves.add(m);

                // Undo the move just made in order to correctly generate the remaining moves
                Move n = new Move(board, piece, distance, 'd');

                distance++;
            }

            distance = 1;

            // Gets all "down" moves a vertical piece can make
            while (canPieceMoveDown(piece, distance)) {
                Move m = new Move(board, piece, distance, 'd');
                allPieceMoves.add(m);

                // Undo the move just made in order to correctly generate the remaining moves
                Move n = new Move(board, piece, distance, 'u');

                distance++;
            }
        }

        return allPieceMoves;
    }

    public int getTargetX() {
        return this.targetX;
    }

    public int getTargetY() {
        return this.targetY;
    }

    public boolean checkVictory() {

        final Piece keyPiece = boardPieces.get(0);
        int xPosition = keyPiece.getX();
        int yPosition = keyPiece.getY();

        try {
            for (int sizeIterator = 1; sizeIterator <= keyPiece.getSize(); ++sizeIterator) {
                if (xPosition == targetX && yPosition == targetY)
                    return true;
                if (keyPiece.isPieceHorizontal())
                    xPosition++;
                else
                    yPosition++;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return false;
        }
    }

    public Boolean equals(Board board) {
        if (this == board)
            return true;
        if (board == null)
            return false;
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++)
                if (this.board[y][x] != board.board[y][x])
                    return false;
        }
        return true;
    }

}