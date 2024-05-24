import java.util.Random;
import java.util.Scanner;

public class MagicMachine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String inputString = scanner.nextLine();
        int[][] magicMachine = generateMagicMachine(n);

        String outputString = processMagicMachine(magicMachine, inputString);
        System.out.println(outputString);
    }

    private static String processMagicMachine(int[][] magicMachine, String inputString) {
        String[][] intermediateResults = new String[magicMachine.length][magicMachine.length];
        intermediateResults[0][0] = inputString;

        for (int i = 0; i < magicMachine.length; i++) {
            for (int j = 0; j < magicMachine.length; j++) {
                if (intermediateResults[i][j] != null) {
                    switch (magicMachine[i][j]) {
                        case 1:
                            if (i < magicMachine.length - 1) {
                                intermediateResults[i + 1][j] = reverseString(intermediateResults[i][j]);
                            }
                            if (j < magicMachine.length - 1) {
                                intermediateResults[i][j + 1] = reverseString(intermediateResults[i][j]);
                            }
                            break;
                        case 2:
                            if (i < magicMachine.length - 1) {
                                intermediateResults[i + 1][j] = duplicateCharacters(intermediateResults[i][j]);
                            }
                            if (j < magicMachine.length - 1) {
                                intermediateResults[i][j + 1] = duplicateCharacters(intermediateResults[i][j]);
                            }
                            break;
                        case 3:
                            if (i < magicMachine.length - 1) {
                                intermediateResults[i + 1][j] = duplicateString(intermediateResults[i][j]);
                            }
                            if (j < magicMachine.length - 1) {
                                intermediateResults[i][j + 1] = duplicateString(intermediateResults[i][j]);
                            }
                            break;
                        case 4:
                            if (i < magicMachine.length - 1) {
                                intermediateResults[i + 1][j] = shiftRight(intermediateResults[i][j]);
                            }
                            if (j < magicMachine.length - 1) {
                                intermediateResults[i][j + 1] = shiftRight(intermediateResults[i][j]);
                            }
                            break;
                        case 5:
                            if (i < magicMachine.length - 1) {
                                intermediateResults[i + 1][j] = swapCharacters(intermediateResults[i][j]);
                            }
                            if (j < magicMachine.length - 1) {
                                intermediateResults[i][j + 1] = swapCharacters(intermediateResults[i][j]);
                            }
                            break;
                        default:
                            if (i > 0 && j > 0) {
                                switch (magicMachine[i][j]) {
                                    case 1:
                                        if (j < magicMachine.length - 1) {
                                            intermediateResults[i][j + 1] = interleaveStrings(intermediateResults[i][j - 1], intermediateResults[i - 1][j]);
                                        }
                                        break;
                                    case 2:
                                        if (j < magicMachine.length - 1) {
                                            intermediateResults[i][j + 1] = concatReverse(intermediateResults[i][j - 1], intermediateResults[i - 1][j]);
                                        }
                                        break;
                                    case 3:
                                        if (j < magicMachine.length - 1) {
                                            intermediateResults[i][j + 1] = interleaveAlternating(intermediateResults[i][j - 1], intermediateResults[i - 1][j]);
                                        }
                                        break;
                                    case 4:
                                        if (j < magicMachine.length - 1) {
                                            intermediateResults[i][j + 1] = evenLengthFirst(intermediateResults[i][j - 1], intermediateResults[i - 1][j]);
                                        }
                                        break;
                                    case 5:
                                        if (j < magicMachine.length - 1) {
                                            intermediateResults[i][j + 1] = addModulo26(intermediateResults[i][j - 1], intermediateResults[i - 1][j]);
                                        }
                                        break;
                                }
                            }
                    }
                }
            }
        }
        return intermediateResults[magicMachine.length - 1][magicMachine.length - 1];
    }
    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static String duplicateCharacters(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(c).append(c);
        }
        return sb.toString();
    }

    private static String duplicateString(String str) {
        return str + str;
    }

    private static String shiftRight(String str) {
        return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
    }

    private static String swapCharacters(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append((char) ('z' - (c - 'a')));
        }
        return sb.toString();
    }

    private static String interleaveStrings(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < str1.length() || j < str2.length()) {
            if (i < str1.length()) {
                sb.append(str1.charAt(i++));
            }
            if (j < str2.length()) {
                sb.append(str2.charAt(j++));
            }
        }
        return sb.toString();
    }

    private static String concatReverse(String str1, String str2) {
        return str1 + new StringBuilder(str2).reverse().toString();
    }

    private static String interleaveAlternating(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = str2.length() - 1;
        while (i < str1.length() || j >= 0) {
            if (i < str1.length()) {
                sb.append(str1.charAt(i++));
            }
            if (j >= 0) {
                sb.append(str2.charAt(j--));
            }
        }
        return sb.toString();
    }

    private static String evenLengthFirst(String str1, String str2) {
        return (str1.length() % 2 == 0) ? str1 : str2;
    }

    private static String addModulo26(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < str1.length() || i < str2.length()) {
            if (i < str1.length() && i < str2.length()) {
                sb.append((char) (((str1.charAt(i) - 'a') + (str2.charAt(i) - 'a')) % 26 + 'a'));
            } else if (i < str1.length()) {
                sb.append(str1.charAt(i));
            } else {
                sb.append(str2.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }

    private static int[][] generateMagicMachine(int n) {
        Random random = new Random();
        int[][] magicMachine = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                magicMachine[i][j] = random.nextInt(5) + 1;
            }
        }
        return magicMachine;
    }
}


