import java.util.Scanner;
import elements.*;
import algorithm.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int maxDepth = 0;
        int algNum = 0;
        int levelNum = 0;

        long startTime = 0L;
        long endTime = 0L;
        long duration = 0L;

        
        while(levelNum <= 0 || levelNum > 4){
            System.out.println("What Level would you like? (1-4)");
            levelNum = scn.nextInt();
        }

        Board board = selectLevel(levelNum);

        while (true) {
            System.out.println();
            board.printBoard();
            System.out.println();
            System.out.println("1 - Solve using Depth First");
            System.out.println("2 - Solve using Iterative Deepening Depth First");
            System.out.println("3 - Solve using Breadth First");
            System.out.println("4 - Solve using A*");
            System.out.println("5 - Solve using Greedy");
            System.out.println("6 - Solve using Iterative Deepening Greedy");
            System.out.println("7 - Try to solve it yourself!");
            System.out.println("8 - Select Level");
            System.out.println("9 - Exit");
            System.out.println("What do you want to do?");
            algNum = scn.nextInt();

            if (algNum <= 6 && algNum > 0) {
                System.out.println("Maximum Graph Depth: ");
                maxDepth = scn.nextInt();
            }

            System.out.println();

            switch (algNum) {
            case 1:
                DFS depth = new DFS(board);
                startTime = System.nanoTime();
                depth.solve(maxDepth);
                endTime = System.nanoTime();
                break;

            case 2:
                startTime = System.nanoTime();
                for (int i = 0; i <= maxDepth; i++) {
                    DFS iterDepth = new DFS(board);
                    if (iterDepth.solve(i)) {
                        break;
                    }
                }
                endTime = System.nanoTime();
                break;

            case 3:
                BFS breadth = new BFS(board);
                startTime = System.nanoTime();
                breadth.solve(maxDepth);
                endTime = System.nanoTime();
                break;

            case 4:
                AStar aStar = new AStar(board);
                startTime = System.nanoTime();
                aStar.solve(maxDepth);
                endTime = System.nanoTime();
                break;

            case 5:
                Greedy greedy = new Greedy(board);
                startTime = System.nanoTime();
                greedy.solve(maxDepth);
                endTime = System.nanoTime();
                break;

            case 6:
                startTime = System.nanoTime();
                for (int i = 0; i <= maxDepth; i++) {
                    Greedy iterGreedy = new Greedy(board);
                    if (iterGreedy.solve(i)) {
                        break;
                    }
                }
                endTime = System.nanoTime();
                break;

            case 7:
                UI ui = new UI(board);
                ui.UILoop();
                break;

            case 8:
                levelNum = 0;
                while(levelNum <= 0 || levelNum > 4){
                    System.out.println("What Level would you like? (1-4)");
                    levelNum = scn.nextInt();
                }
                board = selectLevel(levelNum);
                break;

            case 9:
                return;

            default:
            }

            duration = (endTime - startTime);
            System.out.println("Time: " + duration/1000000 + " ms");
        }

    }


    public static Board selectLevel(int levelNum) throws Exception {
        int i = 0;
        String[] parts;
        String str;

        int boardX = 6;
        int boardY = 6;
        int objectiveX = 5;
        int objectiveY = 2;
        int keyX = 0;
        int keyY = 0;
        int keySize = 2;
        int keyOrientation = 1;
        char keyChar = 'K';
        ArrayList<Integer> piecesX = new ArrayList<Integer>();
        ArrayList<Integer> piecesY = new ArrayList<Integer>();
        ArrayList<Integer> piecesSize = new ArrayList<Integer>();
        ArrayList<Integer> piecesOrientation = new ArrayList<Integer>();
        ArrayList<Character> piecesChar = new ArrayList<Character>();

        File file = new File("boards/board" + levelNum + ".txt"); 
  
        BufferedReader br = new BufferedReader(new FileReader(file)); 
  
        //Read From File
        while ((str = br.readLine()) != null) {

            parts = str.split(","); 

            if(i==0){ //BOARD
                boardX = Integer.parseInt(parts[0]);
                boardY = Integer.parseInt(parts[1]);
                objectiveX = Integer.parseInt(parts[2]);
                objectiveY = Integer.parseInt(parts[3]);
            }

            else if(i==1){
                keyX = Integer.parseInt(parts[0]);
                keyY = Integer.parseInt(parts[1]);
                keySize = Integer.parseInt(parts[2]);
                keyOrientation = Integer.parseInt(parts[3]);
                keyChar = parts[4].charAt(0);
            }
            
            else{
                piecesX.add(Integer.parseInt(parts[0]));
                piecesY.add(Integer.parseInt(parts[1]));
                piecesSize.add(Integer.parseInt(parts[2]));
                piecesOrientation.add(Integer.parseInt(parts[3]));
                piecesChar.add(parts[4].charAt(0));
            }

            i++;
        }

        //Build Board
        Boolean orientation = true;

        if(keyOrientation == 0)
            orientation = false;
        else{
            orientation = true;
        }

        Piece key = new Piece(keyX, keyY, keySize, orientation, keyChar);

        Board board = new Board(objectiveX, objectiveY, boardX, boardY, key);

        for(int j=0; j<piecesX.size(); j++){
            if(piecesOrientation.get(j) == 0)
                orientation = false;
            else{
                orientation = true;
            }

            Piece p = new Piece(piecesX.get(j), piecesY.get(j), piecesSize.get(j), orientation, piecesChar.get(j));
            board.addPiece(p);
        }

        return board;
    }
}