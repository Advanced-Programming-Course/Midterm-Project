import java.util.Scanner;
import java.util.Random;

class Machine {
	int n;
	int[][] num;
	String first;
	public Machine(int k, String s) {
		Random rand = new Random();
		n = k;
		num = new int[k + 5][k + 5];
		first = s;
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				num[i][j] = rand.nextInt(5) + 1;
	}
	private String b1(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++)
			res = s.charAt(i) + res;
		return res;
	}
	private String b2(String s) {
		String res = "";
		for (int i = 0; i < s.length(); i++)
			res = res + s.charAt(i) + s.charAt(i);
		return res;
	}
	private String b3(String s) {
		return s + s;
	}
	private String b4(String s) {
		String res = "" + s.charAt(s.length() - 1);
		for (int i = 0; i < s.length() - 1; i++)
			res += s.charAt(i);
		return res;
	}
	private String b5(String s) {
		String res = "";
		int n;
		for (int i = 0; i < s.length(); i++) {
			n = 'z' - (int) s.charAt(i);
			res = res + (char) (n + 'a');
		}
		return res;
	}
	private String w1(String s1, String s2) {
		String res = "";
		boolean fl = false;
		if (s1.length() > s2.length()) {
			fl = true;
			String tmp = s1;
			s1 = s2;
			s2 = tmp;
		}
		for (int i = 0; i < s1.length(); i++)
			if (fl == false)
				res = res + s1.charAt(i) + s2.charAt(i);
			else
				res = res + s2.charAt(i) + s1.charAt(i);
		for (int i = s1.length(); i < s2.length(); i++)
			res = res + s2.charAt(i);
		return res;
	}
	private String w2(String s1, String s2) {
		String res = "";
		for (int i = s2.length() - 1; i >= 0; i--)
			res = res + s2.charAt(i);
		return s1 + res;
	}
	private String w3(String s1, String s2) {
		String res = "";
		for (int i = 0, j = s2.length() - 1; ; i++, j--)
			if (i >= s1.length() && j < 0)
				break;
			else if (i >= s1.length() && j >= 0)
				res = res + s2.charAt(j);
			else if (i < s1.length() && j < 0)
				res = res + s1.charAt(i);
			else
				res = res + s1.charAt(i) + s2.charAt(j);
		return res;
	}
	private String w4(String s1, String s2) {
		if (s1.length() % 2 == 0)
			return s1;
		return s2;
	}
	private String w5(String s1, String s2) {
		String res = "";
		if (s1.length() > s2.length()) {
			String tmp = s1;
			s1 = s2;
			s2 = tmp;
		}
		for (int i = 0; i < s1.length(); i++)
			res = res + (char) ('a' + ((s1.charAt(i) + s2.charAt(i) - 'a' - 'a') % 26));
		for (int i = s1.length(); i < s2.length(); i++)
			res = res + s2.charAt(i);
		return res;
	}
	private String green(int x, int y) {
		if (x == 0 && y == 0)
			return first;
		else if (y == 0)
			switch (num[x][y]) {
				case 1:
					return b1(green(x - 1, 0));
				case 2:
					return b2(green(x - 1, 0));
				case 3:
					return b3(green(x - 1, 0));
				case 4:
					return b4(green(x - 1, 0));
				case 5:
					return b5(green(x - 1, 0));
			}
		else if (x == 0)
			switch (num[x][y]) {
				case 1:
					return b1(green(0, y - 1));
				case 2:
					return b2(green(0, y - 1));
				case 3:
					return b3(green(0, y - 1));
				case 4:
					return b4(green(0, y - 1));
				case 5:
					return b5(green(0, y - 1));
			}
		return "";
	}
	private String[] cyan(int x, int y) {
		String[] res = new String[2];
		if (x == 1 && y == 1) {
			res[0] = b1(green(1, 0));
			res[1] = b1(green(0, 1));
		}
		else if (x == 1)
			switch (num[x][y]) {
				case 1:
					res[0] = b1(cyan(x, y - 1)[0]);
					res[1] = b1(green(x - 1, y));
					break;
				case 2:
					res[0] = b2(cyan(x, y - 1)[0]);
					res[1] = b2(green(x - 1, y));
					break;
				case 3:
					res[0] = b3(cyan(x, y - 1)[0]);
					res[1] = b3(green(x - 1, y));
					break;
				case 4:
					res[0] = b4(cyan(x, y - 1)[0]);
					res[1] = b4(green(x - 1, y));
					break;
				case 5:
					res[0] = b5(cyan(x, y - 1)[0]);
					res[1] = b5(green(x - 1, y));
					break;
			}
		else if (y == 1)
			switch (num[x][y]) {
				case 1:
					res[0] = b1(green(x, y - 1));
					res[1] = b1(cyan(x - 1, y)[1]);
					break;
				case 2:
					res[0] = b2(green(x, y - 1));
					res[1] = b2(cyan(x - 1, y)[1]);
					break;
				case 3:
					res[0] = b3(green(x, y - 1));
					res[1] = b3(cyan(x - 1, y)[1]);
					break;
				case 4:
					res[0] = b4(green(x, y - 1));
					res[1] = b4(cyan(x - 1, y)[1]);
					break;
				case 5:
					res[0] = b5(green(x, y - 1));
					res[1] = b5(cyan(x - 1, y)[1]);
					break;
			}
		else
			switch (num[x][y]) {
				case 1:
					res[0] = b1(cyan(x, y - 1)[0]);
					res[1] = b1(cyan(x - 1, y)[1]);
					break;
				case 2:
					res[0] = b2(cyan(x, y - 1)[0]);
					res[1] = b2(cyan(x - 1, y)[1]);
					break;
				case 3:
					res[0] = b3(cyan(x, y - 1)[0]);
					res[1] = b3(cyan(x - 1, y)[1]);
					break;
				case 4:
					res[0] = b4(cyan(x, y - 1)[0]);
					res[1] = b4(cyan(x - 1, y)[1]);
					break;
				case 5:
					res[0] = b5(cyan(x, y - 1)[0]);
					res[1] = b5(cyan(x - 1, y)[1]);
					break;
			}
		return res;
	}
	private String pink(int x, int y) {
		if (x == n - 1 && y == n - 1)
			switch (num[x][y]) {
				case 1:
					return w1(pink(n - 2, n - 1), pink(n - 1, n - 2));
				case 2:
					return w2(pink(n - 2, n - 1), pink(n - 1, n - 2));
				case 3:
					return w3(pink(n - 2, n - 1), pink(n - 1, n - 2));
				case 4:
					return w4(pink(n - 2, n - 1), pink(n - 1, n - 2));
				case 5:
					return w5(pink(n - 2, n - 1), pink(n - 1, n - 2));
			}
		else if (x == 1 && y == n - 1)
			switch (num[x][y]) {
				case 1:
					return w1(green(0, n - 1), cyan(1, n - 2)[0]);
				case 2:
					return w2(green(0, n - 1), cyan(1, n - 2)[0]);
				case 3:
					return w3(green(0, n - 1), cyan(1, n - 2)[0]);
				case 4:
					return w4(green(0, n - 1), cyan(1, n - 2)[0]);
				case 5:
					return w5(green(0, n - 1), cyan(1, n - 2)[0]);
			}
		else if (x == n - 1 && y == 1)
			switch (num[x][y]) {
				case 1:
					return w1(cyan(n - 2, 1)[1], green(n - 1, 0));
				case 2:
					return w2(cyan(n - 2, 1)[1], green(n - 1, 0));
				case 3:
					return w3(cyan(n - 2, 1)[1], green(n - 1, 0));
				case 4:
					return w4(cyan(n - 2, 1)[1], green(n - 1, 0));
				case 5:
					return w5(cyan(n - 2, 1)[1], green(n - 1, 0));
			}
		else if (x == n - 1)
			switch (num[x][y]) {
				case 1:
					return w1(cyan(x - 1, y)[1], pink(x, y - 1));
				case 2:
					return w2(cyan(x - 1, y)[1], pink(x, y - 1));
				case 3:
					return w3(cyan(x - 1, y)[1], pink(x, y - 1));
				case 4:
					return w4(cyan(x - 1, y)[1], pink(x, y - 1));
				case 5:
					return w5(cyan(x - 1, y)[1], pink(x, y - 1));
			}
		else if (y == n - 1)
			switch (num[x][y]) {
				case 1:
					return w1(pink(x - 1, y), cyan(x, y - 1)[0]);
				case 2:
					return w2(pink(x - 1, y), cyan(x, y - 1)[0]);
				case 3:
					return w3(pink(x - 1, y), cyan(x, y - 1)[0]);
				case 4:
					return w4(pink(x - 1, y), cyan(x, y - 1)[0]);
				case 5:
					return w5(pink(x - 1, y), cyan(x, y - 1)[0]);
			}
		return "";
	}
	public String output() {
		return pink(n - 1, n - 1);
	}
}

public class ap {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int sz = input.nextInt();
		String st = input.next();
		Machine mch = new Machine(sz, st);
		System.out.print(mch.output());
	}
}