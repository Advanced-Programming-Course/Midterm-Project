public class Node {
    public String arrow;
    public int num;
    public String color;
    public String result;
    public Node(String arrow, int num, String color) {
        this.arrow = arrow;
        this.num = num;
        this.color = color;
    }

    public String White(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        int len1 = str1.length();
        int len2 = str2.length();
        if (this.num == 1) {
            for (int m = 0; m < Math.max(len1, len2); m++) {
                if (m < len1) {
                    result.append(str1.charAt(m));
                }
                if (m < len2) {
                    result.append(str2.charAt(m));
                }
            }
        }
        else if (num == 2) {
            result.append(str1);
            result.append(new StringBuilder(str2).reverse());
        }
        else if (num == 3) {
            for (int m = 0; m < Math.max(len1, len2); m++) {
                if (m < len1) {
                    result.append(str1.charAt(m));
                }
                if (m < len2) {
                    result.append(str2.charAt(len2 - 1 - m));
                }
            }
        }
        else if (num == 4) {
            if (len1 % 2 == 0) {
                return str1;
            } else {
                return str2;
            }
        }
        else if (num == 5) {
            int minLength = Math.min(len1, len2);
            for (int m = 0; m < minLength; m++) {
                char char1 = str1.charAt(m);
                char char2 = str2.charAt(m);
                int sum = (char1 - 'a' + char2 - 'a') % 26 + 'a';
                result.append((char) sum);
            }
            if (len1 > len2) {
                result.append(str1.substring(len2));
            }
            else if (len2 > len1) {
                result.append(str2.substring(len1));
            }
        }
        else {
            return "";
        }
        return result.toString();
    }

    public String Black(String str1) {
        StringBuilder result = new StringBuilder();
        if (num == 1) {
            result.append(new StringBuilder(str1).reverse());
        }
        else if (num == 2) {
            for (char ch : str1.toCharArray()) {
                result.append(ch).append(ch);
            }
        }
        else if (num == 3) {
            result.append(str1).append(str1);
        }
        else if (num == 4) {
            if (str1.length() > 1) {
                result.append(str1.substring(1)).append(str1.charAt(0));
            }
            else {
                result.append(str1);
            }
        }
        else if (num == 5) {
            for (char ch : str1.toCharArray()) {
                if (Character.isLowerCase(ch)) {
                    result.append((char) ('z' - (ch - 'a')));
                }
                else if (Character.isUpperCase(ch)) {
                    result.append((char) ('Z' - (ch - 'A')));
                }
                else {
                    result.append(ch);
                }
            }
        }
        else {
            result.append(str1);
        }
        return result.toString();
    }
}