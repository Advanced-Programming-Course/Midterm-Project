import java.util.Scanner;
import java.util.Random;

public class Main{


    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String duplicateCharacters(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(c).append(c);
        }
        return result.toString();
    }

    public static String duplicateString(String input) {
        return input + input;
    }

    public static String shiftRight(String input) {
        return input.charAt(input.length() - 1) + input.substring(0, input.length() - 1);
    }

    public static String swapWithAlphabetEnd(String input) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(alphabet.charAt(25 - alphabet.indexOf(c)));
        }
        return result.toString();
    }

    public static String interleaveStrings(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.max(input1.length(), input2.length()); i++) {
            if (i < input1.length()) result.append(input1.charAt(i));
            if (i < input2.length()) result.append(input2.charAt(i));
        }
        return result.toString();
    }

    public static String appendReverse(String input1, String input2) {
        return input1 + new StringBuilder(input2).reverse().toString();
    }

    public static String mergeAlternating(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.max(input1.length(), input2.length()); i++) {
            if (i < input1.length()) result.append(input1.charAt(i));
            if (input2.length() - i - 1 >= 0) result.append(input2.charAt(input2.length() - i - 1));
        }
        return result.toString();
    }

    public static String returnBasedOnLength(String input1, String input2) {
        return input1.length() % 2 == 0 ? input1 : input2;
    }

    public static String sumCharactersModulo26(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.max(input1.length(), input2.length()); i++) {
            if (i < input1.length() && i < input2.length()) {
                int charSum = ((input1.charAt(i) - 'a') + (input2.charAt(i) - 'a')) % 26;
                result.append((char) ('a' + charSum));
            } else if (i < input1.length()) {
                result.append(input1.charAt(i));
            } else {
                result.append(input2.charAt(i));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the magic machine (n):");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.println("Enter the input string:");
        String inputString = scanner.nextLine();

        // Initialize the magic machine with random functions
        int[][] magicMachine = new int[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                magicMachine[i][j] = 1 + random.nextInt(5);
            }
        }

        String[][] machineOutput = new String[n][n];
        machineOutput[0][0] = inputString; // Initial input

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Apply black functions for green and yellow cells
                if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
                    String functionResult = "";
                    switch (magicMachine[i][j]) {
                        case 1:
                            functionResult = reverse(machineOutput[i][j]);
                            break;
                        case 2:
                            functionResult = duplicateCharacters(machineOutput[i][j]);
                            break;
                        case 3:
                            functionResult = duplicateString(machineOutput[i][j]);
                            break;
                        case 4:
                            functionResult = shiftRight(machineOutput[i][j]);
                            break;
                        case 5:
                            functionResult = swapWithAlphabetEnd(machineOutput[i][j]);
                            break;
                    }
                    if (j < n - 1) {
                        machineOutput[i][j + 1] = functionResult;
                    }
                    if (i < n - 1) {
                        machineOutput[i + 1][j] = functionResult;
                    }
                } else {
                    String leftInput = machineOutput[i][j - 1];
                    String topInput = machineOutput[i - 1][j];
                    switch (magicMachine[i][j]) {
                        case 1:
                            machineOutput[i][j] = interleaveStrings(leftInput, topInput);
                            break;
                        case 2:
                            machineOutput[i][j] = appendReverse(leftInput, topInput);
                            break;
                        case 3:
                            machineOutput[i][j] = mergeAlternating(leftInput, topInput);
                            break;
                        case 4:
                            machineOutput[i][j] = returnBasedOnLength(leftInput, topInput);
                            break;
                        case 5:
                            machineOutput[i][j] = sumCharactersModulo26(leftInput, topInput);
                            break;
                    }
                }
            }
        }
        System.out.println("The output of the magic machine is: " + machineOutput[n-1][n-1]);

        scanner.close();
    }
}