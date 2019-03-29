package elements;

public class Move {
    // Represents the board of the game before the move is applied to a piece
    private Board oldBoard;
    // Represents the board of the game after the move is applied to a piece
    private Board newBoard;
    // Represents the original piece
    private Piece oldPiece;
    // Represents the piece to be moved
    private Piece newPiece;
    // Represents the distance the piece will travel
    private int distance;
    // Represents the direction a piece will be moved in
    private char direction;

    public Move(Board oldBoard, Piece piece, int distance, char direction) {
        this.oldBoard = oldBoard;
        this.oldPiece = piece;
        this.newBoard = new Board(this.oldBoard);
        for(Piece p : newBoard.getPieces()) {
            if(p.equals(piece))
                this.newPiece = p;
        }
        this.distance = distance;
        this.direction = direction;

        movePiece();
    }

    public boolean movePiece() {
        if (newPiece.isPieceHorizontal()) {
            if (direction == 'l')
                moveLeft(newPiece, distance);
            else if (direction == 'r')
                moveRight(newPiece, distance);
            else
                return false;
        } else {
            if (direction == 'u')
                moveUp(newPiece, distance);
            else if (direction == 'd')
                moveDown(newPiece, distance);
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

    public Piece getOldPiece() {
        return this.oldPiece;
    }
    public Piece getNewPiece() {
        return this.newPiece;
    }

    public int getDistance() {
        return this.distance;
    }

    public char getDirection() {
        return this.direction;
    }
}