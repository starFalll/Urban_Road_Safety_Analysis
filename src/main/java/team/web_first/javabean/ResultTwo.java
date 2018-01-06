package team.web_first.javabean;

public class ResultTwo {
    private int result2ID;
    private String mainChar, firstChar, secondChar;
    private double confidenceTwo;

    public ResultTwo(String mainChar, String firstChar, String secondChar, double confidenceTwo) {
        this.mainChar = mainChar;
        this.firstChar = firstChar;
        this.secondChar = secondChar;
        this.confidenceTwo = confidenceTwo;
    }

    public ResultTwo(int result2ID, String mainChar, String firstChar, String secondChar, double confidenceTwo) {
        this.result2ID = result2ID;
        this.mainChar = mainChar;
        this.firstChar = firstChar;
        this.secondChar = secondChar;
        this.confidenceTwo = confidenceTwo;
    }

    public String getMainChar() {
        return mainChar;
    }

    public void setMainChar(String mainChar) {
        this.mainChar = mainChar;
    }

    public double getConfidenceTwo() {
        return confidenceTwo;
    }

    public void setConfidenceTwo(double confidenceTwo) {
        this.confidenceTwo = confidenceTwo;
    }

    public String getSecondChar() {
        return secondChar;
    }

    public void setSecondChar(String secondChar) {
        this.secondChar = secondChar;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }

    public int getResult2ID() {
        return result2ID;
    }

    public void setResult2ID(int result2ID) {
        this.result2ID = result2ID;
    }
}
