package elements;

public class Piece {
    // Position is the place within the board that is nearest to the (0, 0) position
    private Position position;
    // Size is the length of the piece. Typical sizes are 2 or 3, but others can be
    // used
    private int size;
    // Layout of the piece in the board. If true, piece is horizontal; if false,
    // piece is vertical
    private boolean isHorizontal;
    // Letter to identify each piece. Multiple positions occupied by the same piece
    // with have the same letter
    private char identificationLetter;

    public Piece(Position position, int size, boolean isHorizontal, char identificationLetter) {
        this.position = position;
        this.size = size;
        this.isHorizontal = isHorizontal;
        this.identificationLetter = identificationLetter;
    }

    public Position getPosition() {
        return this.position;
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

    public void setPosition(Position position) {
        this.position = position;
    }
}