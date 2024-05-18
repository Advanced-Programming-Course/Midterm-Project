import java.util.*;
import java.util.Random;


public class MagicMachine {
    static class Two_Strings {
        String s1;
        String s2;

        public Two_Strings(String a, String b) {
            this.s1 = a;
            this.s2 = b;
        }
    }

    public static String b1(String str) {
        char[] a = str.toCharArray();
        char[] b = new char[a.length];
        for (int i = 0, j = a.length - 1; i < a.length; i++, j--)
            b[j] = a[i];
        return new String(b);
    }

    public static String b2(String str) {
        char[] a = str.toCharArray();
        char[] b = new char[a.length * 2];
        for (int i = 0, j = 0; i < a.length; i++, j += 2)
            b[j] = b[j + 1] = a[i];
        return new String(b);
    }

    public static String b3(String str) {
        return str + str;
    }

    public static String b4(String str) {
        char[] a = str.toCharArray();
        char temp = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; i--)
            a[i + 1] = a[i];
        a[0] = temp;
        return new String(a);
    }

    public static String b5(String str) {
        char[] a = str.toCharArray();
        char[] b = new char[a.length];
        for (int i = 0; i < a.length; i++)
            b[i] = (char) (122 - (a[i] - 'a'));
        return new String(b);
    }

    public static String b(int n, String s){
        switch (n){
            case 1: return b1(s);
            case 2: return b2(s);
            case 3: return b3(s);
            case 4: return b4(s);
            case 5: return b5(s);
        }
        return "";
    }

    public static String w1(String str1, String str2) {
        StringBuilder s = new StringBuilder();
        int i = 0, j = 0;
        while (i < str1.length() || j < str2.length()) {
            if (i < str1.length()) {
                s.append(str1.charAt(i));
                i++;
            }
            if (j < str2.length()) {
                s.append(str2.charAt(j));
                j++;
            }
        }
        return s.toString();
    }

    public static String w2(String str1, String str2) {
        StringBuilder s = new StringBuilder(str1);
        for (int i = str2.length() - 1; i >= 0; i--)
            s.append(str2.charAt(i));
        return s.toString();
    }

    public static String w3(String str1, String str2) {
        StringBuilder s = new StringBuilder();
        int maxLen = Math.max(str1.length(), str2.length());
        for (int i = 0, j=str2.length()-1; i < maxLen ; i++, j--) {
            if (i < str1.length())
                s.append(str1.charAt(i));

            if (j>-1)
                s.append(str2.charAt(j));
        }
        return s.toString();
    }

    public static String w4(String str1, String str2) {
        return (str1.length() % 2 == 0) ? str1 : str2;
    }

    public static String w5(String str1, String str2) {
        StringBuilder s = new StringBuilder();
        int maxLen = Math.max(str1.length(), str2.length());
        for (int i = 0; i < maxLen; i++) {
            int c1 = (i < str1.length()) ? str1.charAt(i)-97 : 0;
            int c2 = (i < str2.length()) ? str2.charAt(i)-97 : 0;
            int sum = (c1 + c2) % 26;
            s.append((char) (sum + 'a'));
        }
        return s.toString();
    }

    public static String w(int n, String s1, String s2){
        switch (n){
            case 1: return w1(s1, s2);
            case 2: return w2(s1, s2);
            case 3: return w3(s1, s2);
            case 4: return w4(s1, s2);
            case 5: return w5(s1, s2);
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int n = scanner.nextInt();
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                array[i][j] = random.nextInt(5) + 1;

        String input = scanner.next();

        String output = magicMachineFunction(n, array, input);

        System.out.println(output);
    }
    public static String magicMachineFunction(int n, int[][] array, String input) {
        Two_Strings[][] matrix = new Two_Strings[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = new Two_Strings("", "");

        for(int i=0; i<n ;i++){
            for(int j=0; j<n ; j++){
                if((i==0 && j<n-1) || (i<n-1 && j==0)) {//green
                    if(i==0 && j==0)
                        matrix[i][j].s1 = matrix[i][j].s2 = b(array[i][j], input);
                    else if(i==0)
                        matrix[i][j].s1 = matrix[i][j].s2 = b(array[i][j], matrix[i][j-1].s1);
                    else// j==0
                        matrix[i][j].s1 = matrix[i][j].s2 = b(array[i][j], matrix[i-1][j].s1);
                }

                else if((i==0 && j==n-1) || (i==n-1 && j==0)) {//yellow
                    if(i==0)
                        matrix[i][j].s1 = matrix[i][j].s2 = b(array[i][j], matrix[i][j-1].s1);
                    else//j==0
                        matrix[i][j].s1 = matrix[i][j].s2 =  b(array[i][j], matrix[i-1][j].s1);
                }

                else if((i==n-1 && j>0) || (i>0 && j==n-1)) {//pink
                    matrix[i][j].s1 = matrix[i][j].s2 = w(array[i][j], matrix[i][j-1].s1, matrix[i-1][j].s2);
                }
                else{//blue
                    matrix[i][j].s1 = b(array[i][j],  matrix[i][j-1].s1);
                    matrix[i][j].s2 = b(array[i][j],  matrix[i-1][j].s2);
                }
            }
        }
        return matrix[n-1][n-1].s1;
    }
}


