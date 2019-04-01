package elements;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class UI {

    private Board board;

    private Scanner scanner;

    private Piece piece;

    public UI(Board board) {
        this.board = board;
        this.scanner = new Scanner(System.in);
        this.piece = new Piece();
    }

    public void UILoop() {

        while (!this.board.checkVictory()) {
            this.board.printBoard();
            askForPiece();
            askForDirection();
        }

        this.board.printBoard();

        System.out.println("\nYou Won!\n");
    }

    public void askForDirection() {

        boolean isValidDirection = false, isValidMove = false;
        char directionChar = 'a';
        int distance = 0;

        System.out.println("\nIn which direction do you want to move the piece?");

        while (!isValidDirection) {

            if (this.piece.isPieceHorizontal())
                System.out.print("Choose the direction - left(l) or right(r): ");
            else
                System.out.print("Choose the direction - up(u) or down(d): ");

            directionChar = scanner.next().charAt(0);

            if (directionChar == 'l' | directionChar == 'r' | directionChar == 'u' | directionChar == 'd')
                isValidDirection = true;
            else
                System.out.println("That direction is not valid\n");

        }

        while (!isValidMove) {

            System.out.println("\nHow many squares do you want to advance?");
            System.out.print("Insert the number here: ");
            distance = this.scanner.nextInt();

            if (directionChar == 'u')
                isValidMove = this.board.canPieceMoveUp(this.piece, distance);
            else if (directionChar == 'd')
                isValidMove = this.board.canPieceMoveDown(this.piece, distance);
            else if (directionChar == 'l')
                isValidMove = this.board.canPieceMoveLeft(this.piece, distance);
            else if (directionChar == 'r')
                isValidMove = this.board.canPieceMoveRight(this.piece, distance);

            if (!isValidMove)
                System.out.println("That distance is not valid\n");

        }

        Move m = new Move(this.board, this.piece, distance, directionChar);
        this.board = m.getNewBoard();
        this.board.updateBoard();

    }

    public void askForPiece() {

        boolean isValidPiece = false;

        System.out.println("\nWhich piece do you want to move?");

        while (!isValidPiece) {
            System.out.print("Select the letter of the piece you want to move: ");
            char pieceChar = scanner.next().charAt(0);

            for (int i = 0; i < board.getPieces().size(); i++) {
                if (pieceChar == board.getPieces().get(i).getIdentificationLetter()) {
                    this.piece = board.getPieces().get(i);
                    isValidPiece = true;
                }
            }

            if (!isValidPiece)
                System.out.println("That piece does not exist\n");

        }
    }

}