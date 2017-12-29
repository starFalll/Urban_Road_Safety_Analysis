package team.web_first.javabean;

public class PersResult {
    double abiOneScore, abiTwoScore;
    int oneDegree, twoDegree;

    public int getOneDegree() {
        return oneDegree;
    }

    public PersResult setOneDegree(int oneDegree) {
        this.oneDegree = oneDegree;
        return this;
    }

    public int getTwoDegree() {
        return twoDegree;
    }

    public PersResult setTwoDegree(int twoDegree) {
        this.twoDegree = twoDegree;
        return this;
    }

    public double getAbiOneScore() {
        return abiOneScore;
    }

    public PersResult setAbiOneScore(double abiOneScore) {
        this.abiOneScore = abiOneScore;
        return this;
    }

    public double getAbiTwoScore() {
        return abiTwoScore;
    }

    public PersResult setAbiTwoScore(double abiTwoScore) {
        this.abiTwoScore = abiTwoScore;
        return this;
    }

}
