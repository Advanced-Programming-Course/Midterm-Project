package sbu.cs;

import sbu.cs.Color.Blue;
import sbu.cs.Color.Green;
import sbu.cs.Color.Pink;
import sbu.cs.Color.Yellow;
import sbu.cs.Holder;

public class SetUpTable {
    private Holder[][] holders;
    private int [][] numArray;
    private String input;
    private String output;

    public SetUpTable(int n,int [][] numArray,String input){
        holders = new Holder[numArray.length][numArray.length];
        initHolder(holders);
        this.numArray = numArray;
        this.input = input;
        this.output = "";
        Table();
    }

    private void Table(){
        //set Green section
        Green green = new Green(input,numArray[0][0]);
        holders[0][0].setOutput1(green.getOutput1());
        holders[0][0].setOutput2(green.getOutput2());

        for(int i = 1; i < holders.length - 1 ;i++){
            Green green1 = new Green(holders[0][i-1].getOutput1(), numArray[0][i]);
            holders[0][i].setOutput1(green1.getOutput1());
            holders[0][i].setOutput2(green1.getOutput2());
        }
        for(int i = 1; i < holders.length - 1; i++){
            Green green2 = new Green(holders[i-1][0].getOutput2(),numArray[i][0]);
            holders[i][0].setOutput1(green2.getOutput1());
            holders[i][0].setOutput2(green2.getOutput2());
        }

        //set Yellow Section
        Yellow yellow1 = new Yellow(holders[0][holders.length-2].getOutput1(),numArray[0][holders.length-1]);
        holders[0][holders.length-1].setOutput2(yellow1.getOutput());
        holders[0][holders.length-1].setOutput2(yellow1.getOutput());

        Yellow yellow2 = new Yellow(holders[holders.length-2][0].getOutput2(),numArray[holders.length-1][0]);
        holders[holders.length-1][0].setOutput1(yellow2.getOutput());
        holders[holders.length-1][0].setOutput2(yellow2.getOutput());

        //set Blue Section
        for(int i = 1; i < holders.length-1; i++){
            Blue blue1 = new Blue(holders[i][i-1].getOutput1(),holders[i-1][i].getOutput2(),numArray[i][i]);
            holders[i][i].setOutput1(blue1.getOutput1());
            holders[i][i].setOutput2(blue1.getOutput2());

            for(int j = i + 1; j < holders.length - 1; j++){
                Blue blue2 = new Blue(holders[i][j-1].getOutput1(),holders[i-1][j].getOutput2(),numArray[i][j]);
                holders[i][j].setOutput1(blue2.getOutput1());
                holders[i][j].setOutput2(blue2.getOutput2());
            }

            for(int j = i + 1; j < holders.length - 1; j++){
                Blue blue3 = new Blue(holders[j][i-1].getOutput1(),holders[j-1][i].getOutput2(),numArray[j][i]);
                holders[j][i].setOutput1(blue3.getOutput1());
                holders[j][i].setOutput2(blue3.getOutput2());
            }
        }

        //Set pink Section
        for(int i = 1; i < holders.length - 1;i++){
            Pink pink1 = new Pink(holders[i][holders.length-2].getOutput1(),holders[i - 1][holders.length -1].getOutput2(),numArray[i][holders.length -1]);
            holders[i][holders.length-1].setOutput1(pink1.getOutput());
            holders[i][holders.length-1].setOutput2(pink1.getOutput());
        }

        for(int i = 1; i < holders.length -1; i++){
            Pink pink2 = new Pink(holders[holders.length-1][i-1].getOutput1(),holders[holders.length -2][i].getOutput2(),numArray[holders.length -1][i]);
            holders[holders.length-1][i].setOutput1(pink2.getOutput());
            holders[holders.length-1][i].setOutput2(pink2.getOutput());
        }
        Pink pink3 = new Pink(holders[holders.length-1][holders.length-2].getOutput1() , holders[holders.length-2][holders.length-1].getOutput1(),numArray[holders.length-1][holders.length-1]);
        output = pink3.getOutput();
    }

    /**
     * Initialize the Holder Array
     * @param holders array of holder object
     */
    private void initHolder(Holder[][] holders){
        for(int i = 0; i < holders.length; i++){
            for(int j = 0; j < holders.length; j++){
                holders[i][j] = new Holder();
            }
        }
    }

    public String getOutput(){
        return output;
    }
}
