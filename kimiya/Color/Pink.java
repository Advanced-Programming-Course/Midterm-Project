package mid.Color;

import mid.Functions.WhiteFunction;

public class Pink extends WhiteFunction {
    private String input1;
    private String input2;
    private String output;
    public Pink (String input1,String input2,int selectFunc){
        super(input1,input2,selectFunc);
        this.input1 = input1;
        this.input2 = input2;
        this.output = super.getAnswer();
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getOutput() {
        return output;
    }
}
