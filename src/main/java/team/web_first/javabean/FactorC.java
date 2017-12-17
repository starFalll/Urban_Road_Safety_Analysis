package team.web_first.javabean;

import java.io.Serializable;

/**
 * Bean FactorC
 * 对应
 * DATABASE web_first
 * TABLE confidence
 */
public class FactorC implements Serializable {
    private int confidenceId;
    private int confidenceScore;
    private int dangerousDrivingCoefficient;
    private boolean C1, C2, C3, C4, C5, C6;

    public int getConfidenceId() {
        return confidenceId;
    }

    public FactorC setConfidenceId(int confidenceId) {
        this.confidenceId = confidenceId;
        return this;
    }

    public int getConfidenceScore() {
        return confidenceScore;
    }

    public FactorC setConfidenceScore(int confidenceScore) {
        this.confidenceScore = confidenceScore;
        return this;
    }

    public int getDangerousDrivingCoefficient() {
        return dangerousDrivingCoefficient;
    }

    public FactorC setDangerousDrivingCoefficient(int dangerousDrivingCoefficient) {
        this.dangerousDrivingCoefficient = dangerousDrivingCoefficient;
        return this;
    }

    public boolean isC1() {
        return C1;
    }

    public FactorC setC1(boolean c1) {
        C1 = c1;
        return this;
    }

    public boolean isC2() {
        return C2;
    }

    public FactorC setC2(boolean c2) {
        C2 = c2;
        return this;
    }

    public boolean isC3() {
        return C3;
    }

    public FactorC setC3(boolean c3) {
        C3 = c3;
        return this;
    }

    public boolean isC4() {
        return C4;
    }

    public FactorC setC4(boolean c4) {
        C4 = c4;
        return this;
    }

    public boolean isC5() {
        return C5;
    }

    public FactorC setC5(boolean c5) {
        C5 = c5;
        return this;
    }

    public boolean isC6() {
        return C6;
    }

    public FactorC setC6(boolean c6) {
        C6 = c6;
        return this;
    }
}
