import java.util.*;
import java.util.Random;

public class MagicMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int n = scanner.nextInt();
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                array[i][j] = random.nextInt(5) + 1;
        String input = scanner.next();
        System.out.println(magicMachineFunction(n, array, input));
    }

    public static String magicMachineFunction(int n, int[][] array, String input) {
        Random random = new Random();
        Cell[][] matrix = new Cell[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                matrix[i][j] = new Cell();
                matrix[i][j].n = array[i][j];

                if((i==0 && j<n-1) || (i<n-1 && j==0))
                    matrix[i][j].color = "green";
                else if((i==n-1 && j>0) || (i>0 && j==n-1))
                    matrix[i][j].color = "pink";
                else if((i==0 && j==n-1) || (i==n-1 && j==0))
                    matrix[i][j].color = "yellow";
                else
                    matrix[i][j].color = "blue";
            }

        matrix[0][0].input_up = input;
        matrix[0][0].input_left = input;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++){
                matrix[i][j].run();
                if(i+1<n)
                    matrix[i+1][j].input_up = matrix[i][j].output_down;
                if(j+1<n)
                    matrix[i][j+1].input_left = matrix[i][j].output_right;
            }
        return matrix[n - 1][n - 1].output_down;
    }
}


