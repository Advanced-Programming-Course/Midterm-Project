import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){;

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        String inp = scn.next();
        int[][] randomMatrix = new int[n][n];
        Random r = new Random();

        // makes a random matrix of size nxn
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                randomMatrix[i][j] = r.nextInt(1, 6);
            }
        }

        Node[][] objectsMatrix = new Node[n][n];

        // gives initial values to objects
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){

                // green
                if ((i == 0 && j != n-1) || (j == 0 && i != n-1)){
                    objectsMatrix[i][j] = new Node("black", randomMatrix[i][j], "green");
                }

                // yellow
                else if ((i == 0 && j == n-1) || (j == 0 && i == n-1)){
                    objectsMatrix[i][j] = new Node("black", randomMatrix[i][j], "yellow");
                }

                // pink
                else if ((i != 0 && j == n-1) || (j != 0 && i == n-1)){
                    objectsMatrix[i][j] = new Node("white", randomMatrix[i][j], "pink");
                }

                // blue
                else if ((i != 0 && j != n-1) || (j != 0 && i != n-1)){
                    objectsMatrix[i][j] = new Node("black", randomMatrix[i][j], "blue");
                }


            }
        }

        objectsMatrix[0][0].result = objectsMatrix[0][0].black(inp);

        calculate(objectsMatrix, n);

        Node[][] transpose = new Node[n][n];
        transpose(transpose, objectsMatrix, n);
        objectsMatrix = transpose;

        calculate(objectsMatrix, n);

        String str1 = objectsMatrix[n-2][n-1].result;
        String str2 = objectsMatrix[n-1][n-2].result;

        String ans = objectsMatrix[n-1][n-1].white(str1, str2);
        System.out.println(ans);
    }

    // calculates results for all objects
    public static void calculate(Node[][] objectsMatrix, int n){
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0){
                    continue;
                }
                if (objectsMatrix[i][j].color.equals("pink") ){
                    objectsMatrix[i][j].result = objectsMatrix[i][j].white(objectsMatrix[i - 1][j].result, objectsMatrix[i][j - 1].result);
                }
                else if (j == 0){
                    objectsMatrix[i][j].result = objectsMatrix[i][j].black(objectsMatrix[i - 1][j].result);
                } else {
                    objectsMatrix[i][j].result = objectsMatrix[i][j].black(objectsMatrix[i][j - 1].result);
                }
            }
        }
    }
    // returns transpose of the objects matrix
    public static Node[][] transpose(Node[][] transpose, Node[][] objectsMatrix, int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                transpose[i][j]=objectsMatrix[j][i];
            }
        }
        return transpose;
    }

    // function used for debugging
    public static void printMatrix(Node[][] objectsMatrix){
        int n = objectsMatrix[0].length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(objectsMatrix[i][j].result);
                System.out.print(" ");
                System.out.print(objectsMatrix[i][j].num);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}