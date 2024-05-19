import java.util.Scanner;

public class MagicalMachineSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the dimensions of the magical machine (n x n)
        System.out.print("Enter the dimensions (n) of the magical machine: ");
        int n = scanner.nextInt();

        // Initialize the grid with random values (1 to 5)
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = (int) (Math.random() * 5) + 1;
            }
        }

        // Read the input string
        System.out.print("Enter the input string: ");
        String inputString = scanner.next();

        // Process the input string through the magical machine
        String outputString = processInput(grid, inputString);

        // Display the output
        System.out.println("Output string: " + outputString);
    }

    // Function to process the input string through the magical machine
    private static String processInput(int[][] grid, String inputString) {
        int x = 0; // Starting position (top-left corner)
        int y = 0;

        for (char c : inputString.toCharArray()) {
            int value = grid[x][y];
            if (value == 1) {
                // Invert the input string
                inputString = new StringBuilder(inputString).reverse().toString();
            } else if (value == 2) {
                // Repeat each character
                StringBuilder repeatedString = new StringBuilder();
                for (char ch : inputString.toCharArray()) {
                    repeatedString.append(ch).append(ch);
                }
                inputString = repeatedString.toString();
            } else if (value == 3) {
                // Shift the input string to the right
                inputString = inputString.charAt(inputString.length() - 1)
                        + inputString.substring(0, inputString.length() - 1);
            } else if (value == 4) {
                // Swap letters from the input string
                StringBuilder swappedString = new StringBuilder();
                for (char ch : inputString.toCharArray()) {
                    swappedString.append((char) ('z' - (ch - 'a')));
                }
                inputString = swappedString.toString();
            }

            if (x < grid.length - 1) {
                x++;
            } else {
                y++;
            }
        }

        return inputString;
    }
}
