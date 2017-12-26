package team.web_first.javabean;

public class Result {
    private int resultId;
    private String fChar, sChar;
    private double confidence;

    public Result(String fChar, String sChar, double confidence) {
        this.fChar = fChar;
        this.sChar = sChar;
        this.confidence = confidence;
    }

    public Result(long resultId, String fChar, String sChar, double confidence) {
        this.resultId = (int) resultId;
        this.fChar = fChar;
        this.sChar = sChar;
        this.confidence = confidence;
    }

    public int getResultId() {
        return resultId;
    }

    public Result setResultId(int resultId) {
        this.resultId = resultId;
        return this;
    }

    public String getfChar() {
        return fChar;
    }

    public Result setfChar(String fChar) {
        this.fChar = fChar;
        return this;
    }

    public String getsChar() {
        return sChar;
    }

    public Result setsChar(String sChar) {
        this.sChar = sChar;
        return this;
    }

    public double getConfidence() {
        return confidence;
    }

    public Result setConfidence(double confidence) {
        this.confidence = confidence;
        return this;
    }
}
