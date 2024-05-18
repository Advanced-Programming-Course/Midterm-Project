import java.util.Objects;

public class Cell {
    String input_up = "";
    String input_left = "";
    String output_right = "";
    String output_down = "";
    int n;
    String color;

    public void run(){
        if(Objects.equals(color, "green")){
            String s = Objects.equals(input_up, "") ? input_left : input_up;
            output_down = output_right = black(n, s);
        }

        else if(Objects.equals(color, "blue")){
            output_down = black(n, input_up);
            output_right = black(n, input_left);
        }

        else if(Objects.equals(color, "yellow")){
            if (Objects.equals(input_up, ""))
                output_down = black(n, input_left);
            else
                output_right = black(n, input_up);
        }
        else{
            output_down = output_right = white(n, input_left, input_up);
        }

    }

    public String black(int n, String s){
        return switch (n) {
            case 1 -> black_1(s);
            case 2 -> black_2(s);
            case 3 -> black_3(s);
            case 4 -> black_4(s);
            case 5 -> black_5(s);
            default -> "";
        };
    }

    public static String black_1(String str) {
        StringBuilder reversed = new StringBuilder(str);
        return reversed.reverse().toString();
    }

    public static String black_2(String str) {
        StringBuilder s = new StringBuilder();
        for (char ch : str.toCharArray())
            s.append(ch).append(ch);
        return s.toString();
    }

    public static String black_3(String str) {
        return str + str;
    }

    public static String black_4(String str) {
        return str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
    }

    public static String black_5(String str) {
        StringBuilder s = new StringBuilder();
        for (char ch : str.toCharArray())
            s.append((char) (122 - (ch - 'a')));
        return s.toString();
    }

    public String white(int n, String s1, String s2){
        return switch (n) {
            case 1 -> white_1(s1, s2);
            case 2 -> white_2(s1, s2);
            case 3 -> white_3(s1, s2);
            case 4 -> white_4(s1, s2);
            case 5 -> white_5(s1, s2);
            default -> "";
        };
    }

    public static String white_1(String str1, String str2) {
        StringBuilder s = new StringBuilder();
        int i = 0, j = 0;
        while (i < str1.length() || j < str2.length()) {
            if (i < str1.length())
                s.append(str1.charAt(i++));

            if (j < str2.length())
                s.append(str2.charAt(j++));
        }
        return s.toString();
    }

    public static String white_2(String str1, String str2) {
        return str1 + new StringBuilder(str2).reverse().toString();
    }

    public static String white_3(String str1, String str2) {
        StringBuilder s = new StringBuilder();
        int i = 0, j = str2.length()-1;
        while (i < str1.length() || j >= 0) {
            if (i < str1.length())
                s.append(str1.charAt(i++));

            if (j >= 0)
                s.append(str2.charAt(j--));
        }
        return s.toString();
    }

    public static String white_4(String str1, String str2) {
        return str1.length() % 2 == 0 ? str1 : str2;
    }

    public static String white_5(String str1, String str2) {
        StringBuilder s = new StringBuilder();
        int i = 0, j = 0;
        while (i < str1.length() || j < str2.length()) {
            if (i < str1.length() && j < str2.length())
                s.append((char)((((str1.charAt(i++) - 97) + (str2.charAt(j++) - 97)) % 26) + 97));
            else if (i < str1.length())
                s.append(str1.charAt(i++));
            else
                s.append(str2.charAt(j++));
        }
        return s.toString();
    }

}
