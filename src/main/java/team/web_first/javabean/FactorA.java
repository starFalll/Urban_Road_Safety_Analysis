package team.web_first.javabean;

import java.io.Serializable;

/**
 * Bean FactorA
 * 对应 DATABASE web_first
 * TABLE risk_perception
 */
public class FactorA implements Serializable {
    private int riskPerceptionId;
    private int getRiskPerceptionScore;

    private boolean A1;
    private boolean A2;
    private boolean A3;
    private boolean A4;
    private boolean A5;
    private boolean A6;

    public int getRiskPerceptionId() {
        return riskPerceptionId;
    }

    public FactorA setRiskPerceptionId(int riskPerceptionId) {
        this.riskPerceptionId = riskPerceptionId;
        return this;
    }

    public int getGetRiskPerceptionScore() {
        return getRiskPerceptionScore;
    }

    public FactorA setGetRiskPerceptionScore(int getRiskPerceptionScore) {
        this.getRiskPerceptionScore = getRiskPerceptionScore;
        return this;
    }


    public boolean isA1() {
        return A1;
    }

    public FactorA setA1(boolean a1) {
        A1 = a1;
        return this;
    }

    public boolean isA2() {
        return A2;
    }

    public FactorA setA2(boolean a2) {
        A2 = a2;
        return this;
    }

    public boolean isA3() {
        return A3;
    }

    public FactorA setA3(boolean a3) {
        A3 = a3;
        return this;
    }

    public boolean isA4() {
        return A4;
    }

    public FactorA setA4(boolean a4) {
        A4 = a4;
        return this;
    }

    public boolean isA5() {
        return A5;
    }

    public FactorA setA5(boolean a5) {
        A5 = a5;
        return this;
    }

    public boolean isA6() {
        return A6;
    }

    public FactorA setA6(boolean a6) {
        A6 = a6;
        return this;
    }

}
