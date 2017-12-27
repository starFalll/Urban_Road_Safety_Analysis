package team.web_first.javabean;

public class PersResult {
    double abiOneScore, abiTwoScore;
    boolean oneDanger, twoDanger;

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

    public boolean isOneDanger() {
        return oneDanger;
    }

    public PersResult setOneDanger(boolean oneDanger) {
        this.oneDanger = oneDanger;
        return this;
    }

    public boolean isTwoDanger() {
        return twoDanger;
    }

    public PersResult setTwoDanger(boolean twoDanger) {
        this.twoDanger = twoDanger;
        return this;
    }
}
