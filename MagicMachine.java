import java.util.Random;
import java.util.Scanner;

public class MagicMachine {
    /**
     * edit this main function and call the magicMachine() function in it.
     */

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        input.nextLine();
        String name = input.nextLine();

        int[][] adad = new int[n][n];
        Random x = new Random();
        for (int i = 0; i < adad.length; i++) {
            for (int j = 0; j < adad.length; j++) {
                adad[i][j] = x.nextInt(1,6);
                System.out.print(adad[i][j]+ " ");
            }
            System.out.println();
        }

        String result = magicMachineFunction(n, adad, name);
        System.out.print(result);

    }

    public static String black(int x, String input) {
        switch (x) {
            case 1:
                return revers(input);
            case 2:
                return doubleString(input);
            case 3:
                return repeat(input);
            case 4:
                return shift(input);
            case 5:
                return replace(input);
            default:
                return input;
        }

    }

    public static String white(int x, String name1, String name2) {
      /* if (name1 == null) {
            return "null";
        }*/
        switch (x) {
            case 1:
                return tarkib(name1, name2);
            case 2:
                return addReverse(name1, name2);
            case 3:
                return jaBeja(name1, name2);
            case 4:
                return zoj(name1, name2);
            case 5:
                return combineStrings(name1, name2);
            default:
                return "null";
        }

    }

    public static String revers(String name) {
        String names = "";
        for (int i = 0; i < name.length(); i++) {
            names = name.charAt(i) + names;
        }
        return names;
    }

    public static String doubleString(String name) {
        StringBuilder doubleString = new StringBuilder();

        for (int i = 0; i < name.length(); i++) {
            char currentChar = name.charAt(i);
            doubleString.append(String.valueOf(currentChar).repeat(2));
        }

        return doubleString.toString();
    }


    public static String repeat(String name) {
        return name + name;
    }

    public static String shift(String name) {
        if (name.isEmpty()) {
            return name;
        }
        String names = String.valueOf(name.charAt(name.length() - 1));
        for (int i = 0; i < name.length() - 1; i++) {
            names += name.charAt(i);
        }
        return names;
    }


    public static String replace(String name) {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (Character.isLetter(c)) {
                char replacement = (char) ('a' + 'z' - c);
                names.append(replacement);
            } else {
                names.append(c);
            }
        }
        return names.toString();
    }

    public static String tarkib(String name1, String name2) {
        StringBuilder tarkib = new StringBuilder();
        int length1 = name1.length();
        int length2 = name2.length();
        int maxLength = Math.max(length1, length2);

        for (int i = 0; i < maxLength; i++) {
            if (i < length1) {
                tarkib.append(name1.charAt(i));
            }
            if (i < length2) {
                tarkib.append(name2.charAt(i));
            }
        }

        return tarkib.toString();
    }

    public static String addReverse(String name1, String name2) {
        String names = "";
        for (int i = 0; i < name2.length(); i++) {
            names = name2.charAt(i) + names;
        }
        return name1 + names;
    }

    public static String jaBeja(String name1, String name2) {
        StringBuilder jaBeja = new StringBuilder();
        int length1 = name1.length();
        int length2 = name2.length();
        int maxLength = Math.max(length1, length2);

        for (int i = 0; i < maxLength; i++) {
            if (i < length1) {
                jaBeja.append(name1.charAt(i));
            }
            if (i < length2) {
                jaBeja.append(name2.charAt(length2 - i - 1));
            }
        }

        return jaBeja.toString();
    }

    public static String zoj(String name1, String name2) {
        if (name1.isEmpty()) {
            return "null";
        }
        int y = name1.length();
        if (y % 2 == 0) {
            return name1;
        }
        return name2;
    }

    public static String combineStrings(String name1, String name2) {
        if (name1.isEmpty() && name2.isEmpty()) {
            return "null";
        }
        StringBuilder result = new StringBuilder();
        int minLength = Math.min(name1.length(), name2.length());
        for (int i = 0; i < minLength; i++) {
            char char1 = name1.charAt(i);
            char char2 = name2.charAt(i);
            char newChar = (char) (((char1 - 'a' + char2 - 'a') % 26) + 'a');
            result.append(newChar);
        }

        if (name1.length() > name2.length()) {
            result.append(name1.substring(minLength));
        } else if (name2.length() > name1.length()) {
            result.append(name2.substring(minLength));
        }

        return result.toString();
    }


    /**
     * implement this method for creating a magic machine
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */

    public static String magicMachineFunction(int n, int[][] array, String input) {

        String DoBodi[][][] = new String[n][n][2];
    //greenSatr
        for (int j = 0; j < n; j++) {
        input = black(array[0][j], input);
        DoBodi[0][j][0] = input;
        DoBodi[0][j][1] = input;
    }
    //greenSotoon
    String input1 = DoBodi[0][0][0];
        for (int i = 1; i < n; i++) {
        input1 = black(array[i][0], input1);
        DoBodi[i][0][0] = input1;
        DoBodi[i][0][1] = input1;
    }
    //blueSatr
        for (int i = 1; i < n - 1; i++) {
        for (int j = 1; j < n - 1; j++) {
            DoBodi[i][j][0] = black(array[i][j], DoBodi[i][j - 1][0]);
        }
    }
    //blueSotoon
        for (int j = 1; j < n - 1; j++) {
        for (int i = 1; i < n-1; i++) {
            DoBodi[i][j][1] = black(array[i][j], DoBodi[i - 1][j][1]);
        }
    }
    //pinkSotoon
        for (int j = 1; j < n - 1; j++) {
        DoBodi[n - 1][j][1] = white(array[n - 1][j], DoBodi[n - 2][j][1], DoBodi[n - 1][j - 1][1]);
    }
    //pinkSatr
        for (int i = 1; i < n - 1; i++) {
        DoBodi[i][n - 1][0] = white(array[i][n - 1], DoBodi[i - 1][n - 1][0], DoBodi[i][n - 2][0]);
    }

    String result1= white(array[n - 1][n - 1], DoBodi[n - 1][n - 2][1], DoBodi[n - 2][n - 1][0]);
        return result1;
}
}





        /*for (int i = 0; i < n; i++) {
            String  input0 =black(array[0][0], input);
             input0 = black(array[i][0],input0);
            for (int j = 1; j < n; j++) {
              //  if (i == 0 && j == 0) {
                 //   input 0 =black(array[i][j], input);
                if (i==0){
                        string input1 =input0;
                        input1= black(array[i][j], input1);
                }else if(i<n-1) {
                    string input1 = input0;
                    input1 = black(array[i][j], input1);
                }else{
                    for(int c = 0;c<n;c++){

                    }
                }
                   /*s[0][i][0]=input1;
                    s[0][i][1]=input1;
                } else if (i==2) {
                    input1 = black(array[0][j], input1);
                }

                }

                /* else if (i == 0 && j!=n-1) {
                    if(j==1)
                        black(array[i][j], input);
                    for(int q=2;q<=j;q++){
                        input= black(array[i][q-1],input);
                    }
                    black(array[i][j], black(array[i][j - 1], input));
                } else if (j == 0 && i!=n-1) {
                    black(array[i][j], black(array[i - 1][j], input));
                } else if (i == n - 1 || j == n - 1) {
                    if(i==1 && j==n-1){
                        white(array[i][j],black(array[i-1][j],input),black(array[i][j-1],input));
                    }
                    if (i==2&&j==n-1){
                        white(array[i][j],black(array[i][j-1],input),white(array[i-1][j],));
                    }
                    if (i == n - 1 && j == n - 1) {
                        white(array[i][j], white(array[i-1][j],input, input),white(array[i][j-1],input, input));
                    }
                    if (i == n - 1 && j == 0) {
                         black(array[i][j], black(array[i - 1][j], input));
                    } else if (i == 0 && j == n - 1) {
                          black(array[i][j], black(array[i][j - 1], input));
                    } else if(i==n-1){
                         white(array[i][j],black(array[i-1][j],input),white(array[i][j-1],input, input));
                    } else if(j==n-1) {
                        input = white(array[i][j], black(array[i ][j-1], input), white(array[i-1][j ], input, input));
                    }
                } else {
                    input = black(array[i][j], black(array[i][j - 1], input));
                    input = black(array[i][j], black(array[i - 1][j], input));
                }
            }
        }
            return input;
        }
    }
*/