package team.web_first.javabean;

import java.io.Serializable;

/**
 * 对应数据库中四个TABLE
 * risk_perception
 * dangerous_driving
 * confidence
 * personality
 *
 */
public class FactorAll implements Serializable {
    private int riskPerceptionId;
    private int getRiskPerceptionScore;

    private boolean A1;
    private boolean A2;
    private boolean A3;
    private boolean A4;
    private boolean A5;
    private boolean A6;

    private int dangerousDrivingId;
    private int dangerousDrivingScore;

    private boolean B1, B2, B3, B4, B5, B6;

    private int confidenceId;
    private int confidenceScore;

    private boolean C1, C2, C3, C4, C5, C6;

    private int personalityId;
    private int PersonalityScore;

    private boolean D1, D2, D3, D4, D5, D6;

    public boolean[] getAllBooleanValue(){
        boolean[] All={A1,A2,A3,A4,A5,B1, B2, B3, B4, B5, B6,C1, C2, C3, C4, C5, C6,D1, D2, D3, D4, D5, D6};
        return All;
    }

    public int getRiskPerceptionId() {
        return riskPerceptionId;
    }

    public void setRiskPerceptionId(int riskPerceptionId) {
        this.riskPerceptionId = riskPerceptionId;
    }

    public int getGetRiskPerceptionScore() {
        return getRiskPerceptionScore;
    }

    public void setGetRiskPerceptionScore(int getRiskPerceptionScore) {
        this.getRiskPerceptionScore = getRiskPerceptionScore;
    }

    public boolean isA1() {
        return A1;
    }

    public void setA1(boolean a1) {
        A1 = a1;
    }

    public boolean isA2() {
        return A2;
    }

    public void setA2(boolean a2) {
        A2 = a2;
    }

    public boolean isA3() {
        return A3;
    }

    public void setA3(boolean a3) {
        A3 = a3;
    }

    public boolean isA4() {
        return A4;
    }

    public void setA4(boolean a4) {
        A4 = a4;
    }

    public boolean isA5() {
        return A5;
    }

    public void setA5(boolean a5) {
        A5 = a5;
    }

    public boolean isA6() {
        return A6;
    }

    public void setA6(boolean a6) {
        A6 = a6;
    }

    public int getDangerousDrivingId() {
        return dangerousDrivingId;
    }

    public void setDangerousDrivingId(int dangerousDrivingId) {
        this.dangerousDrivingId = dangerousDrivingId;
    }

    public int getDangerousDrivingScore() {
        return dangerousDrivingScore;
    }

    public void setDangerousDrivingScore(int dangerousDrivingScore) {
        this.dangerousDrivingScore = dangerousDrivingScore;
    }

    public boolean isB1() {
        return B1;
    }

    public void setB1(boolean b1) {
        B1 = b1;
    }

    public boolean isB2() {
        return B2;
    }

    public void setB2(boolean b2) {
        B2 = b2;
    }

    public boolean isB3() {
        return B3;
    }

    public void setB3(boolean b3) {
        B3 = b3;
    }

    public boolean isB4() {
        return B4;
    }

    public void setB4(boolean b4) {
        B4 = b4;
    }

    public boolean isB5() {
        return B5;
    }

    public void setB5(boolean b5) {
        B5 = b5;
    }

    public boolean isB6() {
        return B6;
    }

    public void setB6(boolean b6) {
        B6 = b6;
    }

    public int getConfidenceId() {
        return confidenceId;
    }

    public void setConfidenceId(int confidenceId) {
        this.confidenceId = confidenceId;
    }

    public int getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(int confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public boolean isC1() {
        return C1;
    }

    public void setC1(boolean c1) {
        C1 = c1;
    }

    public boolean isC2() {
        return C2;
    }

    public void setC2(boolean c2) {
        C2 = c2;
    }

    public boolean isC3() {
        return C3;
    }

    public void setC3(boolean c3) {
        C3 = c3;
    }

    public boolean isC4() {
        return C4;
    }

    public void setC4(boolean c4) {
        C4 = c4;
    }

    public boolean isC5() {
        return C5;
    }

    public void setC5(boolean c5) {
        C5 = c5;
    }

    public boolean isC6() {
        return C6;
    }

    public void setC6(boolean c6) {
        C6 = c6;
    }

    public int getPersonalityId() {
        return personalityId;
    }

    public void setPersonalityId(int personalityId) {
        this.personalityId = personalityId;
    }

    public int getPersonalityScore() {
        return PersonalityScore;
    }

    public void setPersonalityScore(int personalityScore) {
        PersonalityScore = personalityScore;
    }

    public boolean isD1() {
        return D1;
    }

    public void setD1(boolean d1) {
        D1 = d1;
    }

    public boolean isD2() {
        return D2;
    }

    public void setD2(boolean d2) {
        D2 = d2;
    }

    public boolean isD3() {
        return D3;
    }

    public void setD3(boolean d3) {
        D3 = d3;
    }

    public boolean isD4() {
        return D4;
    }

    public void setD4(boolean d4) {
        D4 = d4;
    }

    public boolean isD5() {
        return D5;
    }

    public void setD5(boolean d5) {
        D5 = d5;
    }

    public boolean isD6() {
        return D6;
    }

    public void setD6(boolean d6) {
        D6 = d6;
    }

}
