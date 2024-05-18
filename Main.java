import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String input = sc.next();
        int[][] randomMatrix = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                randomMatrix[i][j] = r.nextInt(1, 6);
            }
        }
        Node[][] objectsMatrix = new Node[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Green
                if ((i == 0 && j != n - 1) || (j == 0 && i != n - 1)) {
                    objectsMatrix[i][j] = new Node("Black", randomMatrix[i][j], "Green");
                }
                //Yellow
                else if ((i == 0 && j == n - 1) || (j == 0 && i == n - 1)) {
                    objectsMatrix[i][j] = new Node("Black", randomMatrix[i][j], "Yellow");
                }
                //Pink
                else if ((i != 0 && j == n - 1) || (j != 0 && i == n - 1)) {
                    objectsMatrix[i][j] = new Node("White", randomMatrix[i][j], "Pink");
                }
                //Blue
                else if ((i != 0 && j != n - 1) || (j != 0 && i != n - 1)) {
                    objectsMatrix[i][j] = new Node("Black", randomMatrix[i][j], "Blue");
                }
            }
        }
        objectsMatrix[0][0].result = objectsMatrix[0][0].Black(input);
        calculate(objectsMatrix, n);
        Node[][] transpose = new Node[n][n];
        transpose(transpose, objectsMatrix, n);
        objectsMatrix = transpose;
        calculate(objectsMatrix, n);
        String str1 = objectsMatrix[n - 2][n - 1].result;
        String str2 = objectsMatrix[n - 1][n - 2].result;

        String ans = objectsMatrix[n - 1][n - 1].White(str1, str2);
        System.out.println(ans);
    }
    public static void calculate(Node[][] objectsMatrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (objectsMatrix[i][j].color.equalsIgnoreCase("Pink")) {
                    String topResult = (i > 0) ? objectsMatrix[i - 1][j].result : "0";
                    String leftResult = (j > 0) ? objectsMatrix[i][j - 1].result : "0";
                    objectsMatrix[i][j].result = objectsMatrix[i][j].White(topResult, leftResult);
                }
                else if (j == 0) {
                    if (i > 0) {
                        objectsMatrix[i][j].result = objectsMatrix[i][j].Black(objectsMatrix[i - 1][j].result);
                    }
                }
                else {
                    objectsMatrix[i][j].result = objectsMatrix[i][j].Black(objectsMatrix[i][j - 1].result);
                }
            }
        }
    }
    public static Node[][] transpose(Node[][] transposedMatrix, Node[][] objectsMatrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposedMatrix[i][j] = objectsMatrix[j][i];
            }
        }
        return transposedMatrix;
    }
}