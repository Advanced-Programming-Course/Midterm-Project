import java.util.Random;
import java.util.Scanner;

public class MagicMachine {
    /**
     * edit this main function and call the magicMachine() function in it.
     */
    public static void main(String[] args) {
        Scanner in1 = new Scanner(System.in);
        int n = in1.nextInt();

        Scanner in2 = new Scanner(System.in);
        String mystring = in2.nextLine();

        Random random = new Random();
        int[][] array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = random.nextInt(1, 2);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }

        String result = magicMachineFunction(n, array, mystring);
        System.out.println(result);
    }

    /**
     * implement this method for creating a magic machine
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */
    public static String magicMachineFunction(int n, int[][] array, String input) {
        String str = input;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if((i == n-1 && j != 0) || (i != 0 && j == n-1)) {
                    if (array[i][j] == 1) {
                        str = WhiteFunction(str, str, array[i][j]);
                    } else if (array[i][j] == 2) {
                        str = WhiteFunction(str, str, array[i][j]);
                    } else if (array[i][j] == 3) {
                        str = WhiteFunction(str, str, array[i][j]);
                    } else if (array[i][j] == 4) {
                        str = WhiteFunction(str, str, array[i][j]);
                    } else if (array[i][j] == 5) {
                        str = WhiteFunction(str, str, array[i][j]);
                    }
                } else {
                    if (array[i][j] == 1) {
                        str = BlackFunction(str, array[i][j]);
                    } else if (array[i][j] == 2) {
                        str = BlackFunction(str, array[i][j]);
                    } else if (array[i][j] == 3) {
                        str = BlackFunction(str, array[i][j]);
                    } else if (array[i][j] == 4) {
                        str = BlackFunction(str, array[i][j]);
                    } else if (array[i][j] == 5) {
                        str = BlackFunction(str, array[i][j]);
                    }
                }
            }
        }
        return str;
    }

    public static String BlackFunction(String str, int num) {
        StringBuilder result = new StringBuilder();
        switch (num) {
            case 1:
                str = new StringBuilder(str).reverse().toString();
                break;
            case 2:
                StringBuilder repeatChar = new StringBuilder();
                for (char c : str.toCharArray()) {
                    repeatChar.append(c).append(c);
                }
                str = repeatChar.toString();
                break;
            case 3:
                str += str;
                break;
            case 4:
                char[] charArray = str.toCharArray();
                char temp = charArray[charArray.length - 1];

                for (int i = charArray.length - 1; i > 0; i--) {
                    charArray[i] = charArray[i - 1];
                }

                charArray[0] = temp;
                for (char value : charArray) {
                    result.append(value);
                }
                str = result.toString();
                break;
            case 5:
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (Character.isLetter(c)) {
                        char reversedChar = (char) ('a' + ('z' - Character.toLowerCase(c)));
                        result.append(reversedChar);
                    } else {
                        result.append(c);
                    }
                }
                str = result.toString();
                break;
        }
        return str;
    }

    public static String WhiteFunction(String str1, String str2, int num) {
        String str = null;
        StringBuilder result = new StringBuilder();
        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = Math.min(len1, len2);

        switch (num) {
            case 1:
                for (int i = 0; i < minLen; i++) {
                    result.append(str1.charAt(i));
                    result.append(str2.charAt(i));
                }

                if (len1 > len2) {
                    result.append(str1.substring(minLen));
                } else if (len2 > len1) {
                    result.append(str2.substring(minLen));
                }
                str = result.toString();
                break;
            case 2:
                str = new StringBuilder(str2).reverse().toString();
                str = str1 + str;
                break;
            case 3:
                for (int i = 0; i < minLen; i++) {
                    result.append(str1.charAt(i));
                    result.append(str2.charAt(len2 - i - 1));
                }

                if (len1 > len2) {
                    result.append(str1.substring(minLen));
                } else if (len2 > len1) {
                    result.append(new StringBuilder(str2.substring(0, len2 - minLen)).reverse());
                }

                str = result.toString();
                break;
            case 4:
                if (str1.length() % 2 == 0) {
                    str = str1;
                } else {
                    str = str2;
                }
                break;
            case 5:

                for (int i = 0; i < minLen; i++) {
                    int sum = (str1.charAt(i) - 'a' + 1) + (str2.charAt(i) - 'a' + 1);
                    result.append((char) ((sum - 1) % 26 + 'a'));
                }

                if (len1 > len2) {
                    result.append(str1.substring(minLen));
                } else if (len2 > len1) {
                    result.append(str2.substring(minLen));
                }

                str = result.toString();
                break;
        }
        return str;
    }
}