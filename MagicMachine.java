import java.util.Scanner;
import java.util.Random;
public class MagicMachine {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	String input1 = scanner.nextLine();
    	String input2 = scanner.nextLine();
    	int n = scanner.nextInt();
    	Random r = new Random(0);
    	int arr[][] = new int[n][n];
    	for (int i = 0;i<n;i++) {
    		for(int j =0;j<n;j++) {
    			arr[i][j]=r.nextInt(1,6);
    		}
    	}
    	System.out.print(sumString(input1 , input2));

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
    		return concatReversed(str1,str2);
    	case 4 :
    		return evenOdd(str1,str2);
    	case 5 :
    		return  sumString(str1,str2);
    	default:
    		return "";
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
    
    
    public static String magicMachineFunction(int n, int[][] array, String input) {
        return null;
    }
}