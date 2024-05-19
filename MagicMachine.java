import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        String input = s.nextLine();
        Random r = new Random();

        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = r.nextInt(4)+1;
            }
        }

        System.out.print(magicMachineFunction(n, arr, input));
    }

    public static String reverse (String input) {
        String res = "";
        for (char c: input.toCharArray()) {
            res = c+res;
        }
        return res;
    }

    public static String repeatedChars (String input) {
        String res = "";
        for (char c: input.toCharArray()) {
            res += c;
            res += c;
        }
        return res;
    }

    // Repeat
    public static String repeat (String input) {
        return input+input;
    }

    // Shift
    public static String shift (String input) {
        return input.substring(input.length()-1) + input.substring(0, input.length()-1);
    }

    // Change
    public static String change (String input) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String res = "";
        for (char c: input.toCharArray()) {
            int index = alphabets.indexOf(c);
            res += alphabets.charAt(25-index);
        }
        return res;
    }

    public static String twoInputF1 (String input1, String input2) {
        int n1 = input1.length();
        int n2 = input2.length();
        int n;
        if (n1 > n2) {
            n = n1;
        } else {
            n = n2;
        }

        String res = "";
        for (int i = 0; i < n; i++) {
            if (i < n1) {
                res += input1.charAt(i);
            }
            if (i < n2) {
                res += input2.charAt(i);
            }
        }
        return res;
    }

    public static String twoInputF2 (String input1, String input2) {
        return input1+reverse(input2);
    }

    public static String twoInputF3 (String input1, String input2) {
        String reversed2 = reverse(input2);
        return twoInputF1(input1, reversed2);
    }

    public static String twoInputF4 (String input1, String input2) {
        if (input1.length() % 2 == 0) {
            return input1;
        }
        return input2;
    }

    public static String twoInputF5 (String input1, String input2) {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";

        int n1 = input1.length();
        int n2 = input2.length();
        int n;
        if (n1 > n2) {
            n = n1;
        } else {
            n = n2;
        }

        String res = "";
        for (int i = 0; i < n; i++) {
            int i1, i2;
            i1 = 0;
            i2 = 0;
            if (i < n1) {
                i1 = alphabets.indexOf(input1.charAt(i));
            }
            if (i < n2) {
                i2 = alphabets.indexOf(input2.charAt(i));
            }
            res += alphabets.charAt((i1+i2+2)%26);
        }
        return res;
    }

    public static String callSingleInput(int i, String input) {
        switch (i) {
            case 1:
                return reverse(input);
            case 2:
                return repeatedChars(input);
            case 3:
                return repeat(input);
            case 4:
                return shift(input);
            case 5:
                return change(input);
        }
        return "";
    }

    public static String callTwoInput(int i, String input1, String input2) {
        switch (i) {
            case 1:
                return twoInputF1(input1, input2);
            case 2:
                return twoInputF2(input1, input2);
            case 3:
                return twoInputF3(input1, input2);
            case 4:
                return twoInputF4(input1, input2);
            case 5:
                return twoInputF5(input1, input2);
        }
        return "";
    }

    /**
     * implement this method for creating a magic machine
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */
    public static String magicMachineFunction(int n, int[][] array, String input) {
        String[][][] inputs = new String[n][n][2];
        inputs[0][0][0] = input;
        inputs[0][0][1] = input;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <n; j++) {
                // Green
                if (i == 0 && j < n-1) {
                    String output = callSingleInput(array[i][j], inputs[i][j][1]);
                    inputs[i][j+1][1] = output;
                }
                if (j == 0 && i < n-1) {
                    String output = callSingleInput(array[i][j], inputs[i][j][1]);
                    inputs[i+1][j][0] = output;
                }
                
                // Yellow
                if (i == n-1 && j == 0) {
                    inputs[i][j+1][1] = callSingleInput(array[i][j], inputs[i][j][1]);
                }
                if (i == 0 && j == n-1) {
                    inputs[i+1][j][0] = callSingleInput(array[i][j], inputs[i][j][0]);
                }

                // Blue
                if ((i > 0 && j > 0) && (i < n-1 && j < n-1)) {
                    inputs[i+1][j][0] = callSingleInput(array[i][j], inputs[i][j][0]);
                    inputs[i][j+1][1] = callSingleInput(array[i][j], inputs[i][j][1]);
                }

                // Pink
                if (i == n-1 && j > 0) {
                    inputs[i][j+1][1] = callTwoInput(array[i][j], inputs[i][j][0], inputs[i][j][1]);
                }
                if (j == n-1 && i > 0) {
                    inputs[i+1][j][0] = callTwoInput(array[i][j], inputs[i][j][0], inputs[i][j][1]);
                }
            }
        }
        return "";
    }
}
