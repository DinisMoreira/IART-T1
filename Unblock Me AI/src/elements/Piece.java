package elements;

public class Piece {
    // Size is the length of the piece. Typical sizes are 2 or 3, but others can be
    // used
    private int size;
    // Layout of the piece in the board. If true, piece is horizontal; if false,
    // piece is vertical
    private boolean isHorizontal;
    // Letter to identify each piece. Multiple positions occupied by the same piece
    // with have the same letter
    private char identificationLetter;
    // Stores the x coordinate of the piece
    private int x;
    // Stores the y coordinate of the piece
    private int y;

    public Piece(int x, int y, int size, boolean isHorizontal, char identificationLetter) {
        this.size = size;
        this.isHorizontal = isHorizontal;
        this.identificationLetter = identificationLetter;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isPieceHorizontal() {
        return this.isHorizontal;
    }

    public char getIdentificationLetter() {
        return this.identificationLetter;
    }
}