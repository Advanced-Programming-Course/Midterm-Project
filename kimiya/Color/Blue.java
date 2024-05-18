package mid.Color;

import mid.Functions.BlackFunction;

public class Blue{
    private String input1;
    private String input2;
    private String output1;
    private String output2;
    private BlackFunction blackFunction1;
    private BlackFunction blackFunction2;

    public Blue(String input1,String input2,int selectFunc){
        blackFunction1 = new BlackFunction(input1,selectFunc);
        blackFunction2 = new BlackFunction(input2,selectFunc);
        output1 = blackFunction1.getAnswer();
        output2 = blackFunction2.getAnswer();
    }
    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    public String getOutput1() {
        return output1;
    }

    public String getOutput2() {
        return output2;
    }
}
