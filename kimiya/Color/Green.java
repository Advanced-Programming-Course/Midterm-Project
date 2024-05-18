package mid.Color;

import mid.Functions.BlackFunction;

public class Green extends BlackFunction {
    private String input;
    private String output1;
    private String output2;

    public Green(String input,int selectFunc){
        super(input,selectFunc);
        this.input = input;
        output1 = super.getAnswer();
        output2 = super.getAnswer();
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput1() {
        return output1;
    }

    public String getOutput2() {
        return output2;
    }
}
