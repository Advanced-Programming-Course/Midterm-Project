package sbu.cs.Color;
import sbu.cs.Functions.BlackFunction;

public class Yellow extends BlackFunction {
    private String input;
    private String output;

    public Yellow(String input,int selectFunc){
        super(input,selectFunc);
        this.input = input;
        output = super.getAnswer();
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

}
