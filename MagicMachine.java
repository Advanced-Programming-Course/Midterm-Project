import java.util.Random;
import java.util.Scanner;
public class MagicMachine {
    private Random rand = new Random();
    private int size;
    private int[][] cells;
    public String inputStr;
    public MagicMachine(int size, String inputStr) {
        this.size = size;
        this.inputStr = inputStr;
        this.cells = new int[size][size];
        initialize();
    }
    private void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = rand.nextInt(5) + 1;
            }
    }
}
    public void print() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }
    private String right(String str) {
        return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
    }
    private String charShif(String str) {
        StringBuilder shifted = new StringBuilder();
        for (char c : str.toCharArray()) {
            char nChar = (char) ('a' + (c - 'a' + 1) % 26);
            shifted.append(nChar);
        }
        return shifted.toString();
    }
    private String combStr(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < Math.max(str1.length(), str2.length()); i++) {
            if (i < str1.length())
                result.append(str1.charAt(i));
            if (i < str2.length())
                result.append(str2.charAt(i));
        }
        return result.toString();
    }
    private String Makos(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    private String oddP(String str1, String str2) {
        return str1.length() % 2 == 0 ? str1 : str2;
    }
    private String sMod(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);
            char sum = (char) ((char1 - 'a' + char2 - 'a') % 26 + 'a');
            result.append(sum);
        }
        if (str1.length() != str2.length()) {
            result.append(str1.length() > str2.length() ? str1.substring(minLength) : str2.substring(minLength));
        }
        return result.toString();
    }
    private String bfChoice(String str, int type) {
        switch (type) {
            case 1:
                return Makos(str);
            case 2:
                return str + str;
            case 3:
                return right(str);
            case 4:
                return charShif(str);
            default:
                return str;
        }
    }
    private String afChoice(String str1, String str2, int type) {
        switch (type) {
            case 1:
                return combStr(str1, str2);
            case 2:
                return str1 + Makos(str2);
            case 3:
                return combStr(str1, str2);
            case 4:
                return oddP(str1, str2);
            case 5:
                return sMod(str1, str2);
            default:
                return str1 + str2;
        }
    }
    public String Khoroji(String str) {
        String[] rRes = new String[size];
        String[] colRes = new String[size];
        for (int i = 0; i < size; i++) {
            rRes[i] = str;
            colRes[i] = str;
        }
        for (int i = 0; i < size; i++) {
            rRes[0] = bfChoice(str, cells[0][i]);
            colRes[0] = bfChoice(str, cells[i][0]);
        }
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size + i - 1; j++) {
                if (i > j) {
                    rRes[i] = bfChoice(str, cells[i - 1][0]);
                    colRes[i] = bfChoice(str, cells[0][i - 1]);
                } else {
                    rRes[i] = bfChoice(str, cells[i][j - i]);
                    colRes[i] = bfChoice(str, cells[j - i][i]);
                }
            }
        }
        String avalRes = rRes[0];
        for (int i = 1; i < size - 3; i++) {
            avalRes = afChoice(rRes[i], avalRes, cells[i][size - 1]);
        }
        String DovomRes = colRes[0];
        for (int i = 1; i < size - 3; i++) {
            DovomRes = afChoice(colRes[i], avalRes, cells[size - 1][i]);
        }
        return afChoice(avalRes, DovomRes, cells[size - 1][size - 1]);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int vorodi = scanner.nextInt();
        scanner.nextLine();
        String inputString = scanner.nextLine();
        MagicMachine machine = new MagicMachine(vorodi, inputString);
        System.out.println();
        System.out.println(machine.Khoroji(machine.inputStr));
    }
}
