import java.util.Random;
import java.util.Scanner;

public class MagicMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of the magic machine (n): ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Random random = new Random();
        int[][] machine = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                machine[i][j] = random.nextInt(5) + 1;
            }
        }
        printMachine(machine);

        System.out.println("Enter the input string (length between 5 and 20 characters): ");
        String input = scanner.nextLine();

        if (input.length() < 5 || input.length() > 20) {
            System.out.println("Input string length must be between 5 and 20 characters.");
            return;
        }

        String output = magicMachineFunction(n, machine, input);
        System.out.println("Output: " + output);
    }

    public static String magicMachineFunction(int n, int[][] array, String input) {

        String[][] grid = new String[n][n];
        grid[0][0] = applyBlackFunction(array[0][0], input);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;

                int funcType = array[i][j];

                if (i == 0) {
                    grid[i][j] = applyBlackFunction(funcType, grid[i][j - 1]);
                } else if (j == 0) {
                    grid[i][j] = applyBlackFunction(funcType, grid[i - 1][j]);
                } else {
                    String fromLeft = grid[i][j - 1];
                    String fromTop = grid[i - 1][j];

                    if (i == n - 1 || j == n - 1) {
                        grid[i][j] = applyWhiteFunction(funcType, fromLeft, fromTop);
                    } else {
                        String bottomResult = applyBlackFunction(funcType, fromTop);
                        grid[i][j] = bottomResult;
                        String rightResult = applyBlackFunction(funcType, fromLeft);
                        if (j < n - 1) grid[i][j + 1] = rightResult;
                    }
                }
            }
        }

        return grid[n - 1][n - 1];
    }

    private static String applyBlackFunction(int func, String input) {
        switch (func) {
            case 1:
                return new StringBuilder(input).reverse().toString();
            case 2:
                String doubled = "";
                for (char c : input.toCharArray()) {
                    doubled += c;
                    doubled += c;
                }
                return doubled;
            case 3:
                return input + input;
            case 4:
                return input.charAt(input.length() - 1) + input.substring(0, input.length() - 1);
            case 5:
                String replaced = "";
                for (char c : input.toCharArray()) {
                    replaced += (char) ('z' - (c - 'a'));
                }
                return replaced;
            default:
                return null;
        }
    }
    private static String applyWhiteFunction(int func, String str1, String str2) {
        switch (func) {
            case 1:
                String merged = "";
                int maxLength = Math.max(str1.length(), str2.length());
                for (int i = 0; i < maxLength; i++) {
                    if (i < str1.length()) merged += str1.charAt(i);
                    if (i < str2.length()) merged += str2.charAt(i);
                }
                return merged;
            case 2:
                return str1 + new StringBuilder(str2).reverse().toString();
            case 3:
                String interleave = "";
                int length = Math.max(str1.length(), str2.length());
                for (int i = 0; i < length; i++) {
                    if (i < str1.length()) interleave += str1.charAt(i);
                    if (i < str2.length()) interleave += str2.charAt(str2.length() - 1 - i);
                }
                return interleave;
            case 4:
                return (str1.length() % 2 == 0) ? str1 : str2;
            case 5:
                String sumString = "";
                int minLength = Math.min(str1.length(), str2.length());
                for (int i = 0; i < minLength; i++) {
                    char newChar = (char) ((str1.charAt(i) - 'a' + str2.charAt(i) - 'a') % 26 + 'a');
                    sumString += newChar;
                }
                if (str1.length() > minLength) sumString += str1.substring(minLength);
                if (str2.length() > minLength) sumString += str2.substring(minLength);
                return sumString;
            default:
                return null ;
        }
    }
    private static void printMachine(int[][] machine) {
        System.out.println("Magic Machine Grid:");
        for (int[] row : machine) {
            for (int func : row) {
                System.out.print(func + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}