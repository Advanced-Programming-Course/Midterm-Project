import java.util.Scanner;

// Abstract class representing a house in the magic machine
abstract class House {
    abstract String process(String input);
}

// Class representing a green house
class GreenHouse extends House {
    @Override
    String process(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

// Class representing a yellow house
class YellowHouse extends House {
    @Override
    String process(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            output.append(c).append(c);
        }
        return output.toString();
    }
}

// Class representing a blue house
class BlueHouse extends House {
    @Override
    String process(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            output.append(c);
        }
        return output.toString();
    }
}

// Class representing a pink house
class PinkHouse extends House {
    @Override
    String process(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            output.append((char) ('a' + ('z' - c)));
        }
        return output.toString();
    }
}

// Class representing a white house
class WhiteHouse extends House {
    @Override
    String process(String input) {
        return "";
    }

}

// Class representing the magic machine
public class MagicMachine {
    private final House[][] houses;

    public MagicMachine(int n, int[][] houseTypes) {
        houses = new House[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (houseTypes[i][j]) {
                    case 1:
                        houses[i][j] = new GreenHouse();
                        break;
                    case 2:
                        houses[i][j] = new YellowHouse();
                        break;
                    case 3:
                        houses[i][j] = new BlueHouse();
                        break;
                    case 4:
                        houses[i][j] = new PinkHouse();
                        break;
                    case 5:
                        houses[i][j] = new WhiteHouse();
                        break;
                }
            }
        }
    }

    public String process(String input) {
        int n = houses.length;
        String[][] inputs = new String[n][n];
        inputs[0][0] = input;

        // Process green and yellow houses
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i][j] instanceof GreenHouse || houses[i][j] instanceof YellowHouse) {
                    if (i - 1 >= 0) {
                        inputs[i][j] = houses[i - 1][j].process(inputs[i - 1][j]);
                    } else if (j - 1 >= 0) {
                        inputs[i][j] = houses[i][j - 1].process(inputs[i][j - 1]);
                    }
                }
            }
        }

        // Process blue houses
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (houses[i][j] instanceof BlueHouse) {
                    String input1 = (i - 1 >= 0) ? inputs[i - 1][j] : "";
                    String input2 = (j - 1 >= 0) ? inputs[i][j - 1] : "";
                    inputs[i][j] = houses[i][j].process(input1 + input2);
                }
            }
        }

        // Process pink houses
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (houses[i][j] instanceof PinkHouse) {
                    String input1 = (i + 1 < n) ? inputs[i + 1][j] : "";
                    String input2 = (j + 1 < n) ? inputs[i][j + 1] : "";
                    inputs[i][j] = houses[i][j].process(input1 + input2);
                }
            }
        }

        return inputs[n - 1][n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the size of the magic machine
        int n = scanner.nextInt();
        scanner.nextLine();

        // Read the magic machine structure
        int[][] houseTypes = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                houseTypes[i][j] = Integer.parseInt(line[j]);
            }
        }

        // Create the magic machine
        MagicMachine machine = new MagicMachine(n, houseTypes);

        // Read the input string
        String input = scanner.nextLine();

        // Process the input string
        String output = machine.process(input);

        // Print the output
        System.out.println(output);

        scanner.close();
    }
}
