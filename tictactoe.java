package com.company;
import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {
	// write your code here
        char[][] board = {{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '}};
        char[][] posBoard = {{'1','|','2','|','3'},{'-','+','-','+','-'},{'4','|','5','|','6'},{'-','+','-','+','-'},{'7','|','8','|','9'}};

        System.out.println("Shown below is the empty grid");
        printBoard(board);
        System.out.println("Positions on the grid are as indicated below");
        printBoard(posBoard);
        int p=0;

        while(true)
        {
            Scanner obj=new Scanner(System.in);

            System.out.println("Enter the position (1-9) where you want to place your number (Enter -1 if you want to quit): ");
            int playerPos = obj.nextInt();

            while(playerPosition.contains(playerPos) || cpuPosition.contains(playerPos))
            {
                System.out.println("Position already taken! Enter a correct position");
                playerPos=obj.nextInt();
            }

            if(playerPos==-1)
            {p=-1; break;}

            placePiece(board,playerPos,"Player");

            String ans = checkWinner();
            if(ans.length()>0)
            {
                System.out.println(ans);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9)+1;

            while(playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos))
            {
                cpuPos = rand.nextInt(9)+1;
            }

            placePiece(board,cpuPos,"Computer");

            printBoard(board);

            ans = checkWinner();
            if(ans.length()>0)
            {
                System.out.println(ans);
                break;
            }
        }
        //if(p==-1)
            System.out.println("Game terminated successfully");
    }

    public static void printBoard(char[][] board)
    {
        for(char[] row: board)
        {
            for(char r: row)
            {
                System.out.print(r);
            }
            System.out.println();
        }
    }
    public static void placePiece(char[][] board, int pos, String user)
    {
        char symbol='X';
        if(user=="Player") {
            symbol = 'X';
            playerPosition.add(pos);
        }
        else {
            symbol = 'O';
            cpuPosition.add(pos);
        }

        switch(pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                System.out.println("Please enter a valid position (i.e from 1-9) ");
                break;
        }
    }

    public static String checkWinner()
    {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);
        List firstCol = Arrays.asList(1,4,7);
        List secCol = Arrays.asList(2,5,8);
        List thirdCol = Arrays.asList(3,6,9);
        List dia1 = Arrays.asList(1,5,9);
        List dia2 = Arrays.asList(3,5,7);

        List<List> win = new ArrayList<List>();
        win.add(topRow);
        win.add(midRow);
        win.add(bottomRow);
        win.add(firstCol);
        win.add(secCol);
        win.add(thirdCol);
        win.add(dia1);
        win.add(dia2);

        for(List l:win)
        {
            if(playerPosition.containsAll(l)){
                return "Congratulations, you won!! ";
            }
            else if(cpuPosition.containsAll(l)){
                return "Sorry, CPU won :(";
            }
            else if(playerPosition.size()+cpuPosition.size()==9)
                return "It's a draw";
        }

        return "";

    }
}
