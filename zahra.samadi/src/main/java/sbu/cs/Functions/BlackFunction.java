package sbu.cs.Functions;

public class BlackFunction {
    private String input;
    private int selectFunc;
    private String answer;

    public BlackFunction(String input,int selectFunc){
        this.input = input;
        this.selectFunc = selectFunc;
        this.answer = "";
        SelectFunc();
    }
    public void SelectFunc(){
        switch (this.selectFunc){
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

    public void func1(){
        for(int i = this.input.length() -1 ; i >= 0; i--){
            this.answer += this.input.charAt(i);
        }
    }

    public void func2(){
        for(int i = 0; i<this.input.length(); i++){
            this.answer += this.input.charAt(i);
            this.answer += this.input.charAt(i);
        }
    }

    public void func3(){
        this.answer += this.input;
        this.answer += this.input;
    }

    public void func4(){
        this.answer += this.input.charAt(input.length()-1);
        for(int i = 0; i < this.input.length()-1; i++){
            this.answer += this.input.charAt(i);
        }
    }

    public void func5(){
        String alpha = "zyxwvutsrqponmlkjihgfedcba";
        for(int i = 0; i < this.input.length(); i++){
            int ascii = this.input.charAt(i);
            ascii = ascii - 97;
            this.answer += alpha.charAt(ascii);
        }
    }

    public String getAnswer(){
        return this.answer;
    }
}
