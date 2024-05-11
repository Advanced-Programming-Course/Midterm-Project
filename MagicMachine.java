// Midterm-Project
// Student id : 402222062

import java.util.Scanner;
import java.util.Random;

public class MagicMachine {

    public static void main(String[] args) {
        // get input
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String s = input.nextLine();

        // make random array
        Random random = new Random();
        int[][] arr = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                arr[i][j] =  random.nextInt(1,6);
                // arr[i][j] = input.nextInt();
                // System.out.print(arr[i][j]);
            }
            // System.out.println(' ');
        }
        
        // call magicMachineFunction
        String output = magicMachineFunction(n, arr, s);
        
        // print output
        System.out.println(output);
    }
    
    
    public static String magicMachineFunction(int n, int[][] arr, String input) {
        
        // this is project board. its 3d because every square has maximum of 2 inputs.
        // up input = 0, left input = 1
        String[][][] board = new String[n+1][n+1][2];
        board[0][0][0] = input;
        // run on the board
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                // green
                if((i==0 || j==0) && i+j!=n-1){
                    green(i, j, arr[i][j], board);
                }
                // yellow
                else if((i==0 || j==0) && i+j==n-1){
                    yellow(i, j, arr[i][j], board);
                }
                // blue
                else if(!(i==0 || j==0) && !(i==n-1 || j==n-1)){
                    blue(i, j, arr[i][j], board);
                }
                // pink
                else{
                    pink(i, j, arr[i][j], board, n);
                }
            }
        }
        return board[n][n-1][0];

    }
    
    public static void green(int i, int j, int job, String[][][] board) {
        if(j==0){
            // up input
            board[i+1][j][0] = black(board[i][j][0], job);
            board[i][j+1][1] = board[i+1][j][0];
        }
        else{
            // left input
            board[i+1][j][0] = black(board[i][j][1], job);
            board[i][j+1][1] = board[i+1][j][0];
        }
    }
    
    public static void yellow(int i, int j, int job, String[][][] board) {
        if(j==0){
            // up input
            board[i][j+1][1] = black(board[i][j][0], job);
        }
        else{
            // left input
            board[i+1][j][0] = black(board[i][j][1], job);
        }
    }
    
    public static void blue(int i, int j, int job, String[][][] board) {
        // up to down
        board[i+1][j][0] = black(board[i][j][0], job);
        // left to right
        board[i][j+1][1] = black(board[i][j][1], job);
    }
    
    public static void pink(int i, int j, int job, String[][][] board, int n) {
        if(j==n-1){
            // down output
            board[i+1][j][0] = white(board[i][j][1], board[i][j][0], job);
        }
        else{
            // right output
            board[i][j+1][1] = white(board[i][j][1], board[i][j][0], job);
        }
    }
    
    public static String black(String input, int job) {
        String ans = "";
        switch(job){
            case 1: ans = reverse(input); break;
            case 2: ans = dubChar(input); break;
            case 3: ans = dubStr(input); break;
            case 4: ans = shiftChar(input); break;
            case 5: ans = reAscii(input); break;
        }
        return ans;
    }
    
    public static String white(String input1, String input2, int job) {
        String ans = "";
        switch(job){
            case 1: ans = mixOnebyOne(input1,input2); break;
            case 2: ans = input1 + reverse(input2); break;
            case 3: ans = mixOnebyOne(input1,reverse(input2)); break;
            case 4: ans = isOdd(input1,input2); break;
            case 5: ans = AsciiSum(input1,input2); break;
        }
        return ans;
    }
    
    public static String reverse(String input) {
        String ans = "";
        for(int i=0; i < input.length(); i++){
            ans = input.charAt(i) + ans;
        }
        return ans;
    }
    
    public static String dubChar(String input) {
        String ans = "";
        for(int i=0; i < input.length(); i++){
            char c = input.charAt(i);
            ans += c;
            ans += c;
        }
        return ans;
    }

    public static String dubStr(String input) {
        return input+input;
    }

    public static String shiftChar(String input) {
        String ans = "";
        for(int i=0 ; i < input.length()-1; i++){
            ans += input.charAt(i);
        }
        return input.charAt(input.length()-1)+ans;
    }

    public static String reAscii(String input){
        String ans = "";
        for(int i=0 ; i < input.length(); i++){
            ans += (char) ('z'-input.charAt(i)+'a');
        }
        return ans;
    }
    
    public static String mixOnebyOne(String input1, String input2){
        String ans = "";
        int m = Math.max(input1.length(), input2.length());
        for(int i=0 ; i < m ; i++){
            if(i<input1.length()){
                ans += input1.charAt(i);
            }
            if(i<input2.length()){
                ans += input2.charAt(i);
            }
        }
        return ans;
    }

    public static String isOdd(String input1, String input2){
        if(input1.length()%2==1){
            return input2;
        }
        return input1;
    }

    public static String AsciiSum(String input1, String input2){
        String ans = "";
        int m = Math.max(input1.length(), input2.length());
        for(int i=0 ; i < m ; i++){
            if(i<input1.length() && i<input2.length()){
                ans += (char) (((input1.charAt(i)-'a')+(input2.charAt(i)-'a'))%26 + 'a');
            }
            else if(i<input1.length()){
                ans += input1.charAt(i);
            }
            else{
                ans += input2.charAt(i);
            }

        }
        return ans;
    }


}
