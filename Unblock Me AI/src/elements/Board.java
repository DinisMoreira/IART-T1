package elements;

import java.util.*;

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
        boardPieces = new ArrayList<>();
        for(Piece piece : b.boardPieces)
            this.boardPieces.add(new Piece(piece));
    }

    public int getWidth(){
        return width;
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
                if (board[yPosition-yIterator][xPosition] != '.')
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
                if (board[yPosition+yIterator][xPosition] != '.')
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

        return allPieceMoves;
    }

    public int getTargetX() {
        return this.targetX;
    }

    public int getTargetY() {
        return this.targetY;
    }

    public List<Piece> getPieces() {
        return this.boardPieces;
    }

    public boolean checkVictory() {
        return getDistanceToTarget() == 0;
    }

    public int getDistanceToTarget() {

        final Piece keyPiece = boardPieces.get(0);
        final int pieceSize = keyPiece.getSize();

        // Since the same logic can be applied to both axis,
        // we opted to simplify the amount of code required
        final int keyPosition = keyPiece.isPieceHorizontal() ?
            keyPiece.getX() : keyPiece.getY();
        final int targetValue = keyPiece.isPieceHorizontal() ?
            this.targetX : this.targetY;

        if(keyPosition >= targetValue) // If the target is closer to (0,0) than keyPiece is
            return keyPosition - targetValue;
        else // If keyPiece if closer to (0, 0) than the target is
            return targetValue - (keyPosition + pieceSize - 1);
    }

    public int getAmountPiecesToTarget() {
        final Piece keyPiece = boardPieces.get(0);
        int xPosition = keyPiece.getX();
        int yPosition = keyPiece.getY();
        // Adjust positions in case it's size is needed for calculations
        xPosition += (keyPiece.getX() >= this.targetX) ? 0 : keyPiece.getSize()-1; 
        yPosition += (keyPiece.getY() >= this.targetY) ? 0 : keyPiece.getSize()-1; 
        
        char comparableChar = keyPiece.getIdentificationLetter();
        int amountOfPieces = 0;
        for(int i = 0; i <= getDistanceToTarget(); i++) {
            if( this.getBoard()[yPosition][xPosition] != '.' && 
                this.getBoard()[yPosition][xPosition] != comparableChar)
            {
                comparableChar = this.getBoard()[yPosition][xPosition];
                amountOfPieces++;
            }

            if(keyPiece.isPieceHorizontal())
                xPosition += (keyPiece.getX() >= this.targetX) ? -1 : 1;
            else
                yPosition += (keyPiece.getY() >= this.targetY) ? -1 : 1;
        }
        return amountOfPieces;
    }



        public int getBlockageToTarget() {
        final Piece keyPiece = this.boardPieces.get(0);
        final int amountOfPiecesToTarget = getAmountPiecesToTarget();
        int xPosition = keyPiece.getX();
        int yPosition = keyPiece.getY();
        // Adjust positions in case it's size is needed for calculations
        xPosition += (keyPiece.getX() >= this.targetX) ? 0 : keyPiece.getSize()-1; 
        yPosition += (keyPiece.getY() >= this.targetY) ? 0 : keyPiece.getSize()-1;
        Hashtable<Character, Character[]> foundChars = new Hashtable<>(); // (piece blocking, list of pieces it blocks)
        int blockage = amountOfPiecesToTarget;

        for(int plays = 0; plays < amountOfPiecesToTarget; plays++) {
            if(keyPiece.isPieceHorizontal())
                xPosition += (keyPiece.getX() >= this.targetX) ? -1 : 1;
            else
                yPosition += (keyPiece.getY() >= this.targetY) ? -1 : 1;

            char currentChar = board[yPosition][xPosition];
            // Look for blockage in directions away from (0, 0)
            try {
                char explorationChar = currentChar; //This variable is meant to iterate the currently found piece
                int xIterator = 0;
                int yIterator = 0;
                //Iterate through current piece, until letter is different
                while(explorationChar == currentChar){
                    if(keyPiece.isPieceHorizontal())
                        yIterator++;
                    else
                        xIterator++;
                    explorationChar = board[yPosition+yIterator][xPosition+xIterator];
                }
                if(explorationChar != '.') {
                    Character[] tempChars = new Character[2];
                    tempChars[0] = explorationChar;
                    tempChars[1] = '.';
                    foundChars.put(currentChar, tempChars);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
            // Look for blockage in directions towards (0, 0)
            try {
                char explorationChar = currentChar; //This variable is meant to iterate the currently found piece
                int xIterator = 0;
                int yIterator = 0;
                //Iterate through current piece, until letter is different
                while(explorationChar == currentChar){
                    if(keyPiece.isPieceHorizontal())
                        yIterator++;
                    else
                        xIterator++;
                    explorationChar = board[yPosition-yIterator][xPosition-xIterator];
                }
                if(explorationChar != '.') {
                    if(foundChars.get(currentChar) != null)
                        foundChars.get(currentChar)[1] = explorationChar;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }

        //Remove pieces that aren't blocked both ways
        Set<Character> keys = foundChars.keySet();
        ArrayList<Character> keysToRemove = new ArrayList<Character>();

        for(Character key: keys){
            if(foundChars.get(key)[0] == '.' || foundChars.get(key)[1] == '.'){
                keysToRemove.add(key);
            }
        }

        for(int i = 0; i < keysToRemove.size(); i++){
            foundChars.remove(keysToRemove.get(i));
        }

        blockage = blockage + keys.size();

        //Add blockage if it's the only time appering in table
        keys = foundChars.keySet();


        if(keys.size() > 1){
            Character firstKey = keys.iterator().next();
            for(Character key: keys){
                    if(key != firstKey){
                         if(foundChars.get(firstKey)[0] == foundChars.get(key)[0] && foundChars.get(firstKey)[1] == foundChars.get(key)[1]){
                             blockage--;
                         }
                  }
            }
        }

        return blockage;
    }

    private Boolean doesCharExistInList(List<Character> list, char character) {
        for(char c : list) {
            if(c == character)
               return true;
        }
        return false;
    }

    public Boolean equals(Board board) {
        if(this == board) return true;
        if(board == null) return false;
        for(int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++)
                if (this.board[y][x] != board.board[y][x])
                    return false;
        }
        return true;
    }

}