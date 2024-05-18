package mid.Functions;

public class WhiteFunction {
    private String input1;
    private String input2;
    private int selectFunc;
    private String answer;

    public WhiteFunction(String input1,String input2,int selectFunc){
        this.input1 = input1;
        this.input2 = input2;
        this.selectFunc = selectFunc;
        this.answer = "";
        SelectFunc();
    }

    public void SelectFunc(){
        switch(this.selectFunc){
            case 1 :
                func1();
                break;
            case 2:
                func2();
                break;
            case 3:
                func3();
                break;
            case 4:
                func4();
                break;
            case 5:
                func5();
                break;
            default:
                break;
        }
    }

    private void func1(){
        int minLength = Math.min(this.input1.length(),this.input2.length());
        int i;
        for(i = 0; i < minLength; i++){
            this.answer += input1.charAt(i);
            this.answer += input2.charAt(i);
        }
        String strMax = findMaxLength(this.input1,this.input2);
        for(; i<strMax.length(); i++){
            this.answer += strMax.charAt(i);
        }
    }

    private void func2(){
        this.answer += this.input1;
        for(int i = input2.length() - 1; i >= 0; i--){
            this.answer += this.input2.charAt(i);
        }
    }

    private void func3(){
        int i = 0;
        int j = input2.length() - 1;
        while (i < input1.length() && j >= 0){
            this.answer += input1.charAt(i);
            this.answer += input2.charAt(j);
            i++;
            j--;
        }
        while(i < input1.length()){
            this.answer += input1.charAt(i);
            i++;
        }
        while (j >= 0){
            this.answer += input2.charAt(j);
            j--;
        }
    }

    private void func4(){
        if(input1.length() % 2 == 0){
            answer = input1;
        }
        else {
            answer = input2;
        }
    }

    private void func5(){
        if(input1.length() <= input2.length()){
            for(int i = 0; i < input1.length(); i++){
                int index = ((input1.charAt(i) - 97) + (input2.charAt(i) - 97)) % 26;
                answer += (char)(index + 97);
            }
            for(int i = input1.length(); i < input2.length(); i++){
                answer += input2.charAt(i);
            }
        }
        else {
            for(int i = 0; i < input2.length(); i++){
                int index = ((input1.charAt(i) - 97) + (input2.charAt(i) - 97)) % 26;
                answer += (char)(index + 97);
            }
            for(int i = input2.length(); i < input1.length(); i++){
                answer += input1.charAt(i);
            }
        }
    }

    public String getAnswer(){
        return this.answer;
    }

    private String findMaxLength(String input1,String input2){
        if(input1.length() <= input2.length()){
            return input2;
        }
        else {
            return input1;
        }
    }
}
