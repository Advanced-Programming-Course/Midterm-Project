import java.util.Scanner;
import java.util.Random;
public class MagicMachine {
    /**
     * edit this main function and call the magicMachine() function in it.
     */
    public static void main(String[] args) {
        Scanner inp=new Scanner(System.in);
        Random random=new Random();
        int n=inp.nextInt();
        String input=inp.nextLine();
        int[][] array=new int[n][n];
        for(int i=0 ; i<n ; i++ ){
            for(int j=0 ; j<n ; j++){
                array[i][j]=random.nextInt(1,6);

            }


        }

        System.out.print( magicMachineFunction(n , array , input));
    }
    public static String blackfunctions(int x , String str){
        String result="";
        switch(x){
            case 1:
                result = reverse(str);
            break;
            case 2:
                result = duplicateCharacter(str);
            break;
            case 3:
                result = duplicate(str);
            break;
            case 4:
                result = shiftChar(str);
            break;
            case 5:
                result = displace(str);
            break;
        }
        return result;
    }
    public static String whitefunction(int x , String str1 , String str2){
        String result ="";
        switch(x){
            case 1:
                result = combine(str1 , str2);
            break;
            case 2:
                result = integrate(str1 , str2);
            break;
            case 3:
                result = concatenate(str1 , str2);
            break;
            case 4:
                result = evenChar(str1 , str2);
            break;
            case 5:
                result = remainder(str1 , str2);
            break;
        }
    return result;
    }

    public static String reverse(String str) {
        char[] characters = str.toCharArray();
        int left = 0;
        int right = characters.length - 1;
        while (left < right) {
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;
            left++;
            right--;
        }
        return new String(characters);
    }

    public static String duplicateCharacter(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result += str.charAt(i);
            result += str.charAt(i);
        }
        return result;
    }
    public static String duplicate(String str){
        String result = str.concat(str);
        return result;

    }
    public static String shiftChar(String str){
            char first = str.charAt(str.length()-1);
            String result = first+str.substring(0,str.length()-1);
            return result;
    }

    public static String displace(String str){
            StringBuilder result = new StringBuilder();
            for (char c : str.toCharArray()) {
                char replacement = (char) ('a'+'z'-c);
                result.append(replacement);
            }
            return result.toString();
    }

    public static String combine(String str1, String str2) {
        StringBuilder combine = new StringBuilder();
        combine.append(str1.charAt(0));
        int maxLength = Math.max(str1.length(), str2.length());
        for (int i = 0 ; i <= maxLength; i++) {
            if (i < str1.length() && i>0) {
                combine.append(str1.charAt(i));
            }
            if (i < str2.length()) {
                combine.append(str2.charAt(i));
            }
        }
        return combine.toString();
    }

    public static String integrate(String str1, String str2) {
        StringBuilder result = new StringBuilder(str2).reverse();
        return (str1 + result).toString();
    }

    public static String concatenate(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(str1.length(), str2.length());
        for (int i = 0; i < maxLength; i++) {
            if (i < str1.length()) {
                result.append(str1.charAt(i));
            }
            if (maxLength - 1 - i < str2.length()) {
                result.append(str2.charAt(maxLength - 1 - i));
            }
        }
        return result.toString();
    }

    public static String evenChar(String str1 , String str2){
        char[] string1=str1.toCharArray();
        String result="";
        int length=string1.length;
        if(length%2==0){
            result=str1;
        }else{
            result=str2;
        }
        return result;
    }

    public static String remainder(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        int maxLength = Math.max(str1.length(), str2.length());
        for (int i = 0; i < maxLength; i++) {
            if (i < str1.length() && i < str2.length()) {
                int sum = (str1.charAt(i) - 'a') + (str2.charAt(i) - 'a');
                result.append((char) ('a' + (sum % 26)));
            } else if (i < str1.length()) {
                result.append(str1.charAt(i));
            } else {
                result.append(str2.charAt(i));
            }
        }
        return result.toString();
    }



    public static String Green( int i , int j ,int[][] array, String str){
        int x =array[i][j];
        String result = blackfunctions(x,str);
        return result;

    }
    public static String Yellow(int i , int j,int[][] array,String str){
        int x =array[i][j];
        String result = blackfunctions(x,str);
        return result;
    }
    public static String Blue(int i , int j ,int[][] array, String str){
        int x =array[i][j];
        String result = blackfunctions(x,str);
        return result;
    }
    public static String Pink(  int i , int j ,int[][] array,String str1 , String str2){
        int x =array[i][j];
        String result = whitefunction(x , str1 , str2);
        return result;
    }



    /**
     * implement this method for creating a magic machine
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */
    public static String magicMachineFunction(int n, int[][] array, String input) {
        String[][] table = new String[n][n];
        String result = "";
        table[0][0] =input;
        table[0][1]= Green(0, 0, array, input);
        table[1][0]=Green(0, 0, array, input);
        String[][] table2 = new String[n][n];
        //برای خانه های آبی که از بالا ورودی میگیرند و خانه های صورتی که دو ورودی میگیرند
        // به عبارتی مقادیر ورودی بالایی تابع های صورتی و تابع های آبی که از بالا ورودی میگیرند
        // در این ماتریس نگهداری میشوند
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0 && j < n - 1) {
                    result = Green(i, j, array, table[0][j]);
                    table[i][j + 1] = result;
                    table2[i + 1][j] = result;
                } else if (j == 0 && i > 0 && i < n - 1) {
                    result = Green(i, j, array, table[i][0]);
                    table[i + 1][j] = result;
                    table[i][j + 1] = result;
                } else if (i == 0 && j == n - 1) {
                    result = Yellow(i, j, array, table[i][j]);
                    table2[i + 1][j] = result;
                } else if (i == n - 1 && j == 0) {
                    result = Yellow(i, j, array, table[i][j]);
                    table[i][j + 1] = result;
                } else if (i > 0 && j > 0 && j < n - 1 && i < n - 1) {
                    table2[i + 1][j] = Blue(i, j, array, table2[i][j]);
                    table[i][j + 1] = Blue(i, j, array, table[i][j]);
                } else if (i == n - 1 && j > 0 && j < n - 1) {
                    table[i][j + 1] = Pink(i, j, array, table[i][j], table2[i][j]);

                } else if (i > 0 && j == n - 1 && i < n - 1) {
                    table2[i + 1][j] = Pink(i, j, array, table[i][j], table2[i][j]);
                }


            }

        }

        String str1=table[n-1][n-1];
        String str2 = table2[n-1][n-1];

        String x = Pink(n-1 , n-1 , array, str1 , str2);
       return  x;
    }
}
