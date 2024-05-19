
public class MagicMachine {

    // Black functions
    public static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public static String repeatCharacters(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(c).append(c);
        }
        return result.toString();
    }

    public static String repeatString(String input) {
        return input + input;
    }

    public static String shiftRight(String input) {
        if (input.length() == 0)
            return input;
        return input.charAt(input.length() - 1) + input.substring(0, input.length() - 1);
    }

    public static String replaceWithAlphabetEnd(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append((char) ('z' - (c - 'a')));
        }
        return result.toString();
    }

    // White functions
    public static String interleave(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        int i = 0, j = 0;
        while (i < input1.length() && j < input2.length()) {
            result.append(input1.charAt(i++));
            result.append(input2.charAt(j++));
        }
        while (i < input1.length())
            result.append(input1.charAt(i++));
        while (j < input2.length())
            result.append(input2.charAt(j++));
        return result.toString();
    }

    public static String reverseAndConcat(String input1, String input2) {
        return input1 + new StringBuilder(input2).reverse().toString();
    }

    public static String alternateAndReverse(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        int len1 = input1.length(), len2 = input2.length();
        for (int i = 0; i < Math.max(len1, len2); i++) {
            if (i < len1)
                result.append(input1.charAt(i));
            if (i < len2)
                result.append(input2.charAt(len2 - 1 - i));
        }
        return result.toString();
    }

    public static String conditionalReverse(String input1, String input2) {
        return input1.length() % 2 == 0 ? new StringBuilder(input1).reverse().toString()
                : new StringBuilder(input2).reverse().toString();
    }

    public static String sumCharacters(String input1, String input2) {
        StringBuilder result = new StringBuilder();
        int len1 = input1.length(), len2 = input2.length();
        for (int i = 0; i < Math.max(len1, len2); i++) {
            if (i < len1 && i < len2) {
                result.append((char) ((input1.charAt(i) - 'a' + input2.charAt(i) - 'a') % 26 + 'a'));
            } else if (i < len1) {
                result.append(input1.charAt(i));
            } else {
                result.append(input2.charAt(i));
            }
        }
        return result.toString();
    }

    // Main function to process the magic machine
    public static String magicMachineFunction(int n, int[][] array, String input) {
        String[][] results = new String[n][n];
        results[0][0] = applyBlackFunction(array[0][0], input);

        // Process the first row
        for (int j = 1; j < n; j++) {
            results[0][j] = applyBlackFunction(array[0][j], results[0][j - 1]);
        }

        // Process the first column
        for (int i = 1; i < n; i++) {
            results[i][0] = applyBlackFunction(array[i][0], results[i - 1][0]);
        }

        // Process the inner cells
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i == n - 1 || j == n - 1) {
                    results[i][j] = applyWhiteFunction(array[i][j], results[i - 1][j], results[i][j - 1]);
                } else {
                    results[i][j] = applyBlackFunction(array[i][j], results[i - 1][j], results[i][j - 1]);
                }
            }
        }

        // Return the final result
        return results[n - 1][n - 1];
    }

    private static String applyBlackFunction(int functionNumber, String input) {
        switch (functionNumber) {
            case 1:
                return reverse(input);
            case 2:
                return repeatCharacters(input);
            case 3:
                return repeatString(input);
            case 4:
                return shiftRight(input);
            case 5:
                return replaceWithAlphabetEnd(input);
            default:
                throw new IllegalArgumentException("Invalid function number");
        }
    }

    private static String applyBlackFunction(int functionNumber, String input1, String input2) {
        switch (functionNumber) {
            case 1:
                return reverse(input1);
            case 2:
                return repeatCharacters(input1);
            case 3:
                return repeatString(input1);
            case 4:
                return shiftRight(input1);
            case 5:
                return replaceWithAlphabetEnd(input1);
            default:
                throw new IllegalArgumentException("Invalid function number");
        }
    }

    private static String applyWhiteFunction(int functionNumber, String input1, String input2) {
        switch (functionNumber) {
            case 1:
                return interleave(input1, input2);
            case 2:
                return reverseAndConcat(input1, input2);
            case 3:
                return alternateAndReverse(input1, input2);
            case 4:
                return conditionalReverse(input1, input2);
            case 5:
                return sumCharacters(input1, input2);
            default:
                throw new IllegalArgumentException("Invalid function number");
        }
    }

    public static void main(String[] args) {
        int n = 8;
        String input = "qmiqwnhwnrckeirepjgv";

        int[][] array = {
                { 2, 5, 5, 4, 2, 1, 5, 5 },
                { 2, 1, 2, 4, 4, 1, 5, 4 },
                { 4, 4, 1, 1, 1, 5, 1, 4 },
                { 4, 1, 4, 4, 1, 4, 5, 1 },
                { 1, 1, 1, 5, 1, 4, 4, 5 },
                { 4, 4, 5, 4, 5, 1, 5, 5 },
                { 1, 4, 4, 4, 1, 1, 1, 4 },
                { 4, 5, 4, 5, 5, 1, 4, 4 }
        };

        // Process the magic machine and print the result
        String result = magicMachineFunction(n, array, input);
        System.out.println(result);
    }
}
