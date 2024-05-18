package mid;

public class App {

    /**
     * use this function for magical machine question.
     *
     * @param n     size of machine
     * @param array   an array in size n * n
     * @param input the input string
     * @return the output string of machine
     */
    public String main(int n, int[][] array, String input) {
        SetUpTable setUpTable = new SetUpTable(n,array,input);
        return setUpTable.getOutput();
    }
}
