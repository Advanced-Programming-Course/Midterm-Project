import java.util.Scanner;
import java.util.Random;
public class MagicMachine {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Enter the string : ");
    	String input = scanner.nextLine();
    	String input1 = input;
    	System.out.println("Enter the number : ");
    	int n = scanner.nextInt();
    	Random r = new Random(0);
    	int arr[][] = new int[n][n];
    	String s[][][] = new String[n][n][2];
    	for (int i = 0;i<n;i++) {
    		for(int j =0;j<n;j++) {
    			arr[i][j]=r.nextInt(1,6);
    			//System.out.print(arr[i][j] + " ");
    		}
    		//System.out.println();
    	}
    	for(int i =0;i<n;i++) {
    		input1 = blackFunc(input1,arr[0][i]);
    		s[0][i][0]=input1;
    		s[0][i][1]=input1;
    	}
    	String input2 = s[0][0][0];
    	for(int i=1 ; i<n ; i++) {
    		input2 = blackFunc(input2,arr[i][0]);
    		s[i][0][0]=input2;
    		s[i][0][1]=input2;}
    	//blue_satr
    	for(int i =1;i<n-1;i++) {
    		for(int j =1;j<n-1;j++) {
    			s[i][j][0]=blackFunc(s[i][j-1][0],arr[i][j]);
    		}
    	}
    	//blue_sotoon
    	for(int i=1 ; i<n-1 ; i++) {
    		for(int j =1 ; j<n-1 ; j++) {
    			s[j][i][1]=blackFunc(s[j-1][i][1],arr[j][i]);
    		}
    	}
    	//pink_sotoon
    	for(int j=1 ; j<n-1 ; j++) {
    		s[n-1][j][1]=whiteFunc(s[n-2][j][1],s[n-1][j-1][1],arr[n-1][j]);
    	}
    	//pink_sotoon
    	for(int i =1 ;i<n-1 ; i++) {
    		s[i][n-1][0]=whiteFunc(s[i-1][n-1][0],s[i][n-2][0],arr[i][n-1]);
    	}
    	
    	/*for(int i =0;i<n;i++) {
    		for(int j =0;j<n ;j++) {
    			System.out.print(s[i][j][1]+"     ");
    		}
    		System.out.println();}*/
    	System.out.print(whiteFunc(s[n-1][n-2][1],s[n-2][n-1][0],arr[n-1][n-1]));

    	
    }


    public static String blackFunc(String str , int arr_ij) {
    	switch(arr_ij) {
    	case 1 :
    		return reverse(str);
    	case 2 :
    		return duplicateChar(str);
    	case 3 :
    		return duplicateString(str);
    	case 4 :
    		return rightShift(str);
    	case 5 :
    		return  viceversa(str);
    	default:
    		return "";
    	}
    	
    }
    public static String whiteFunc(String str1 ,String str2, int arr_ij) {
    	switch(arr_ij) {
    	case 1 :
    		return combination(str1,str2);
    	case 2 :
    		return concatReversed(str1,str2);
    	case 3 :
    		return everyOtherString(str1,str2);
    	case 4 :
    		return evenOdd(str1,str2);
    	case 5 :
    		return  sumString(str1,str2);
    	default:
    		return "tfthjj";
    		}
    	}
    
    public static String reverse(String originalStr) {
    	String reversedStr = "";
    	for (int i = 0; i < originalStr.length(); i++) {
    	  reversedStr = originalStr.charAt(i) + reversedStr;
    	}
    	return reversedStr;
    }
    
    public static String duplicateChar(String originalStr) {
    	String str ="";
    	for(int i =0 ; i<originalStr.length() ; i++) {
    		for(int j =0 ; j<2 ; j++) {
    			str += originalStr.charAt(i);
    		}
    	}
    	return str;
    }
    
    public static String duplicateString (String originalStr) {
    	return originalStr+originalStr;
    }
    
    public static String rightShift(String originalStr) {
    	String str = originalStr.substring(0,originalStr.length()-1);
    	return originalStr.charAt(originalStr.length()-1)+str;
    }
    public static String viceversa(String originalStr) {
    	String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	StringBuilder output = new StringBuilder();
    	for(int i=0 ; i<originalStr.length();i++) {
    		char currentChar  = originalStr.charAt(i);
    		if(Character.isLetter(currentChar)) {
    			char shiftedChar = alphabet.charAt(25-alphabet.indexOf(Character.toLowerCase(currentChar)));
    			if(Character.isUpperCase(currentChar)) {
    				shiftedChar = Character.toUpperCase(shiftedChar);
    				}
    			output.append(shiftedChar);
    		}else {
    			output.append(currentChar);
    		}
    	}
    	return output.toString();
    }
    public static String combination(String str1 , String str2) {
    	int a = str1.length() , b = str2.length();
    	String newStr =""; String fill ="";
    	if(b>=a) {
        	for (int i = 0 ;i<str1.length() ; i++) {
        		newStr+=str1.charAt(i);
        		newStr+=str2.charAt(i);
        	}
        	newStr+=str2.substring(a,b);
        	return newStr;
    	}
    	else {for (int i = 0 ;i<str2.length() ; i++) {
    		newStr+=str2.charAt(i);
    		newStr+=str1.charAt(i);
    	}
    	newStr+=str1.substring(b,a);
    	return newStr;}
    	
    }
    
    public static String concatReversed(String str1 , String str2) {
    	String newStr="";
    	for(int i =0 ; i<str2.length() ; i++) {
    		newStr+=str2.charAt((str2.length()-1)-i);
    	}
    	return str1.concat(newStr);
    }
    
    public static String everyOtherString(String str1 , String str2) {
    	String newStr = "";
    	int a = str1.length(); int b = str2.length();
    	if (a==b) {
    		for(int i =0 ; i<str1.length();i++) {
        		newStr+=str1.charAt(i);
        		newStr+=str2.charAt((str2.length()-1)-i);
        	}
    		return newStr;
    	}
    	else if(a<b) {
    		for(int i =0 ; i<str1.length();i++) {
        		newStr+=str1.charAt(i);
        		newStr+=str2.charAt((str2.length()-1)-i);
        	}
    		String lastString=str2.substring(0,a);
    		for(int i = 0 ; i<lastString.length();i++) {
    			newStr+=lastString.charAt((lastString.length()-1)-i);
    		}
    		return newStr;
    	}
    	else if(b<a) {
    		String lastString = str1.substring(0,b);
    		String lastString2 = str1.substring(b,a);
    		for(int i =0 ; i<lastString.length();i++) {
        		newStr+=lastString.charAt(i);
        		newStr+=str2.charAt((str2.length()-1)-i);
        	}
    		
    		return newStr+lastString2;
    	}
    	else {
    		return "";
    	}
    }
    
    public static String evenOdd(String str1 , String str2) {
    	if(str1.length()%2==0) {
    		return str1;
    	}
    	else {
    		return str2;
    	}
    }
    public static String sumString(String str1 , String str2) {
    	StringBuilder result = new StringBuilder();
        int length1 = str1.length();
        int length2 = str2.length();
        
        int maxLength = Math.max(length1, length2);
        
        for (int i = 0; i < maxLength; i++) {
            char char1 = (i < length1) ? str1.charAt(i) : 'a';
            char char2 = (i < length2) ? str2.charAt(i) : 'a';
            
            int char1Value = char1 - 'a';
            int char2Value = char2 - 'a';
            
            int sum = (char1Value + char2Value) % 26;
            char resultChar = (char) (sum + 'a');
            
            result.append(resultChar);
        }
        
        return result.toString();
    }
}