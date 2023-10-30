import javax.swing.plaf.IconUIResource;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Initializing the base of board
        int n = 10;
        int[][] board = new int[n][n];
        int[] pointer = new int[2];
        int[] count = new int[1];
        pointer[0] = 9;
        BoardStructure(board);
        input(board,pointer,count);

    }

    public static void BoardStructure(int[][] board){
        int n = board.length-1;
        int m = board[0].length-1;
        int num = 1;

        for (int i = n;i>=0;i--){
            if (i%2==1){
                for (int j =0;j<=m;j++){
                    board[i][j] = num;
                    num++;
                }
            }else {
                for (int j =m;j>=0;j--){
                    board[i][j] = num;
                    num++;
                }
            }
        }
    }

    public static void input(int[][] board,int[] pointer,int[] count){
        int min = 1;
        int max = 6;
        Random random = new Random();
        int input = random.nextInt(max - min + 1) + min;
        Scanner scn = new Scanner(System.in);
        if (count[0] == 0){
            System.out.println("Game Starts when the Dice give number 1!!");
        }else {
            System.out.println("Enter 0 for move and another number fo exit the game!!");
        }
        int i = scn.nextInt();
        System.out.println(input);
        if (count[0] == 0 && input == 1){
            move(board,pointer,input,count);
        }else if (count[0]>=1){
            removeBackMove(board,pointer);
            move(board,pointer,input,count);
            snakeBite(board,pointer);
            putLeaders(board,pointer);
        }

        printBoard(board);
        if (pointer[0] == 0 && pointer[1] == 0){
            System.out.println("You complete the Game!!!!");
            System.out.println(count[0]);
            return;
        }

//        System.out.println(pointer[0] + " - " + pointer[1]);
        while (true){

            if (i == 0){
                input(board,pointer,count);
            }{
                break;
            }

        }
    }


    public static void printBoard(int[][] board){
        for (int i = 0;i<board.length;i++){
            for (int j = 0;j<board.length;j++){
                System.out.print(board[i][j] + "  |  ");
            }
            System.out.println();
        }
    }


    public static void move(int[][] board , int[] pointer, int inputMove,int[] count){

        int walk;
        if (pointer[0] % 2 == 1){
             walk = pointer[1] + inputMove;
        }else {
            walk = pointer[1] - inputMove;
        }


        if (pointer[0]%2 == 1 && walk < board.length){
// ------> normal walk!!
//            int i = walk/10;
// ------>pointer[0] = pointer[0] - i;
            pointer[1] = walk;
            board[pointer[0]][pointer[1]] = 7777;
        }else if (pointer[0]%2 == 1 && walk >= board.length){
//------->jump to even and reverse walk!!
//            int i = walk/10;
            int j = walk%10;
            pointer[0]--;
            pointer[1] = 9 - j;
            board[pointer[0]][pointer[1]] = 7777;
        }else if (pointer[0] == 0 && walk>=0){
            pointer[1] = walk;
            board[pointer[0]][pointer[1]] = 7777;
        }else if (pointer[0]%2 == 0 && walk >=0){
//    reverse walk!!
//            int i = walk/10;
            pointer[1] = walk;
            board[pointer[0]][pointer[1]] = 7777;
        }else if(pointer[0]%2 == 0 && walk < 0 && pointer[0] != 0){
//------> jump to odd and walk normal
            pointer[0]--;
            walk = Math.abs(walk);
            pointer[1] =  walk - 1;
            board[pointer[0]][pointer[1]] = 777;
        }else {
            board[pointer[0]][pointer[1]] = 777;
            System.out.println("No move Sorry !!!");
        }

        count[0]++;



    }

    public static void removeBackMove(int[][] board,int[] pointer){

        int i = pointer[0];
        int j = pointer[1];
        if(i%2==1){
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + j + 1;
        }else {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
        }

    }

    public static void putLeaders(int[][] board,int[] pointer){
//------> WE have 8 stairs:-
//------> At value:-4,9,20,28,40,51,63,71

       int i = pointer[0];
       int j = pointer[1];

       if (i == 9 && j == 3){
           board[pointer[0]][pointer[1]] =  j + 1;
           pointer[0] = 8;
           pointer[1] = 6;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       } else if (i == 9 && j == 8) {
           board[pointer[0]][pointer[1]] = j + 1;
           pointer[0] = 8;
           pointer[1] = 6;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       } else if (i == 8 && j == 0) {
           board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
           pointer[0] = 6;
           pointer[1] = 9;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       } else if (i == 7 && j == 7) {
           board[pointer[0]][pointer[1]] = ((9-i) * 10) + j + 1;
           pointer[0] = 1;
           pointer[1] = 3;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       }else if (i == 4 && j == 9){
           board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
           pointer[0] = 3;
           pointer[1] = 6;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       } else if (i == 3 && j == 2) {
           board[pointer[0]][pointer[1]] = ((9-i) * 10) + j + 1;
           pointer[0] = 1;
           pointer[1] = 0;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       } else if (i == 2 && j == 9) {
           board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
           pointer[0] = 0;
           pointer[1] = 8;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       }else if (i == 6 && j == 0) {
           board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
           pointer[0] = 4;
           pointer[1] = 1;
           board[pointer[0]][pointer[1]] = 7777;
           System.out.println("You just use a leader and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
       }
    }


    public static void snakeBite(int[][] board,int[] pointer){
        //------> WE have 8 stairs:-

        int i = pointer[0];
        int j = pointer[1];

        if (i == 0 && j == 1){
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
            pointer[0] = 2;
            pointer[1] = 2;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        } else if (i == 0 && j == 5) {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
            pointer[0] = 2;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        } else if (i == 0 && j == 7) {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
            pointer[0] = 2;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        } else if (i == 1 && j == 6) {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + j + 1;
            pointer[0] = 7;
            pointer[1] = 3;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        }else if (i == 3 && j == 1){
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + j + 1;
            pointer[0] = 8;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        } else if (i == 8 && j == 3) {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
            pointer[0] = 9;
            pointer[1] = 6;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        } else if (i == 4 && j == 6) {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + (10 - j);
            pointer[0] = 6;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        }else if (i == 3 && j == 3) {
            board[pointer[0]][pointer[1]] = ((9-i) * 10) + j + 1;
            pointer[0] = 4;
            pointer[1] = 0;
            board[pointer[0]][pointer[1]] = 7777;
            System.out.println("You are bitten by snake !!🐍 and move from" + i +"-" + j +" to " +pointer[0] +" - " + pointer[1]);
        }
    }


}