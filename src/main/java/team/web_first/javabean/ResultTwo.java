package team.web_first.javabean;

public class ResultTwo {
    private int result2ID;
    private String MainChar, FirstChar,SecondChar;
    private double confidenceTwo;

    public String getMainChar() {
        return MainChar;
    }

    public void setMainChar(String mainChar) {
        MainChar = mainChar;
    }

    public double getConfidenceTwo() {
        return confidenceTwo;
    }

    public void setConfidenceTwo(double confidenceTwo) {
        this.confidenceTwo = confidenceTwo;
    }

    public String getSecondChar() {
        return SecondChar;
    }

    public void setSecondChar(String secondChar) {
        SecondChar = secondChar;
    }

    public String getFirstChar() {
        return FirstChar;
    }

    public void setFirstChar(String firstChar) {
        FirstChar = firstChar;
    }

    public int getResult2ID() {
        return result2ID;
    }

    public void setResult2ID(int result2ID) {
        this.result2ID = result2ID;
    }
}
