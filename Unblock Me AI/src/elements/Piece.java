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

    public Piece(Piece piece) {
        this(piece.x,
            piece.y,
            piece.size,
            piece.isHorizontal,
            piece.identificationLetter);
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

    public Boolean equals(Piece piece) {
        if(this == piece) return true;
        if(piece == null) return false;
        final Boolean checkX = this.getX() == piece.getX();
        final Boolean checkY = this.getY() == piece.getY();
        final Boolean checkSize = this.getSize() == piece.getSize();
        final Boolean checkHorizontal = this.isPieceHorizontal() == piece.isPieceHorizontal();
        final Boolean checkChar = this.getIdentificationLetter() == piece.getIdentificationLetter();

        return checkX && checkY && checkSize && checkHorizontal && checkChar;
    }
}