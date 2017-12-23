package team.web_first.javabean;

import java.io.Serializable;

/**
 * 对应数据库中四个TABLE
 * risk_perception
 * dangerous_driving
 * confidence
 * personality
 */
public class FactorAll implements Serializable {
    private int riskPerceptionId;
    //private int getRiskPerceptionScore;

    private boolean A1, A2, A3, A4, A5, A6;

    //private int dangerousDrivingId;
    //private int dangerousDrivingScore;

    private boolean B1, B2, B3, B4, B5, B6;

    //private int confidenceId;
    //private int confidenceScore;

    private boolean C1, C2, C3, C4, C5, C6;

    //private int personalityId;
   // private int personalityScore;
/*
    public int getPersonalityScore() {
        return personalityScore;
    }

    public FactorAll setPersonalityScore(int personalityScore) {
        this.personalityScore = personalityScore;
        return this;
    }
*/
    private boolean D1, D2, D3, D4, D5, D6;

    /**
     * 和算法的接口，勿删
     * @return
     */
    public boolean[] getAllBooleanValue() {
        boolean[] All = {A1, A2, A3, A4, A5, B1, B2, B3, B4, B5, B6, C1, C2, C3, C4, C5, C6, D1, D2, D3, D4, D5, D6};
        return All;
    }

    public int getRiskPerceptionId() {
        return riskPerceptionId;
    }

    public FactorAll setRiskPerceptionId(int riskPerceptionId) {
        this.riskPerceptionId = riskPerceptionId;
        return this;
    }
/*
    public int getGetRiskPerceptionScore() {
        return getRiskPerceptionScore;
    }

    public FactorAll setGetRiskPerceptionScore(int getRiskPerceptionScore) {
        this.getRiskPerceptionScore = getRiskPerceptionScore;
        return this;
    }
*/
    public boolean isA1() {
        return A1;
    }

    public FactorAll setA1(boolean a1) {
        A1 = a1;
        return this;
    }

    public boolean isA2() {
        return A2;
    }

    public FactorAll setA2(boolean a2) {
        A2 = a2;
        return this;
    }

    public boolean isA3() {
        return A3;
    }

    public FactorAll setA3(boolean a3) {
        A3 = a3;
        return this;
    }

    public boolean isA4() {
        return A4;
    }

    public FactorAll setA4(boolean a4) {
        A4 = a4;
        return this;
    }

    public boolean isA5() {
        return A5;
    }

    public FactorAll setA5(boolean a5) {
        A5 = a5;
        return this;
    }

    public boolean isA6() {
        return A6;
    }

    public FactorAll setA6(boolean a6) {
        A6 = a6;
        return this;
    }
/*
    public int getDangerousDrivingId() {
        return dangerousDrivingId;
    }

    public FactorAll setDangerousDrivingId(int dangerousDrivingId) {
        this.dangerousDrivingId = dangerousDrivingId;
        return this;
    }

    public int getDangerousDrivingScore() {
        return dangerousDrivingScore;
    }

    public FactorAll setDangerousDrivingScore(int dangerousDrivingScore) {
        this.dangerousDrivingScore = dangerousDrivingScore;
        return this;
    }
*/
    public boolean isB1() {
        return B1;
    }

    public FactorAll setB1(boolean b1) {
        B1 = b1;
        return this;
    }

    public boolean isB2() {
        return B2;
    }

    public FactorAll setB2(boolean b2) {
        B2 = b2;
        return this;
    }

    public boolean isB3() {
        return B3;
    }

    public FactorAll setB3(boolean b3) {
        B3 = b3;
        return this;
    }

    public boolean isB4() {
        return B4;
    }

    public FactorAll setB4(boolean b4) {
        B4 = b4;
        return this;
    }

    public boolean isB5() {
        return B5;
    }

    public FactorAll setB5(boolean b5) {
        B5 = b5;
        return this;
    }

    public boolean isB6() {
        return B6;
    }

    public FactorAll setB6(boolean b6) {
        B6 = b6;
        return this;
    }
/*
    public int getConfidenceId() {
        return confidenceId;
    }

    public FactorAll setConfidenceId(int confidenceId) {
        this.confidenceId = confidenceId;
        return this;
    }

    public int getConfidenceScore() {
        return confidenceScore;
    }

    public FactorAll setConfidenceScore(int confidenceScore) {
        this.confidenceScore = confidenceScore;
        return this;
    }
*/
    public boolean isC1() {
        return C1;
    }

    public FactorAll setC1(boolean c1) {
        C1 = c1;
        return this;
    }

    public boolean isC2() {
        return C2;
    }

    public FactorAll setC2(boolean c2) {
        C2 = c2;
        return this;
    }

    public boolean isC3() {
        return C3;
    }

    public FactorAll setC3(boolean c3) {
        C3 = c3;
        return this;
    }

    public boolean isC4() {
        return C4;
    }

    public FactorAll setC4(boolean c4) {
        C4 = c4;
        return this;
    }

    public boolean isC5() {
        return C5;
    }

    public FactorAll setC5(boolean c5) {
        C5 = c5;
        return this;
    }

    public boolean isC6() {
        return C6;
    }

    public FactorAll setC6(boolean c6) {
        C6 = c6;
        return this;
    }
/*
    public int getPersonalityId() {
        return personalityId;
    }

    public FactorAll setPersonalityId(int personalityId) {
        this.personalityId = personalityId;
        return this;
    }
*/

    public boolean isD1() {
        return D1;
    }

    public FactorAll setD1(boolean d1) {
        D1 = d1;
        return this;
    }

    public boolean isD2() {
        return D2;
    }

    public FactorAll setD2(boolean d2) {
        D2 = d2;
        return this;
    }

    public boolean isD3() {
        return D3;
    }

    public FactorAll setD3(boolean d3) {
        D3 = d3;
        return this;
    }

    public boolean isD4() {
        return D4;
    }

    public FactorAll setD4(boolean d4) {
        D4 = d4;
        return this;
    }

    public boolean isD5() {
        return D5;
    }

    public FactorAll setD5(boolean d5) {
        D5 = d5;
        return this;
    }

    public boolean isD6() {
        return D6;
    }

    public FactorAll setD6(boolean d6) {
        D6 = d6;
        return this;
    }
}
