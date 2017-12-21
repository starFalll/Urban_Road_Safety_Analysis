package team.web_first.javabean;

import java.io.Serializable;

/**
 * Bean FactorD
 * 对应
 * DATABASE web_first
 * TABLE personality
 */
public class FactorD implements Serializable{
    private int personalityId;
    private int PersonalityScore;

    private boolean D1, D2, D3, D4, D5, D6;

    public int getPersonalityId() {
        return personalityId;
    }

    public FactorD setPersonalityId(int personalityId) {
        this.personalityId = personalityId;
        return this;
    }

    public int getPersonalityScore() {
        return PersonalityScore;
    }

    public FactorD setPersonalityScore(int personalityScore) {
        PersonalityScore = personalityScore;
        return this;
    }


    public boolean isD1() {
        return D1;
    }

    public FactorD setD1(boolean d1) {
        D1 = d1;
        return this;
    }

    public boolean isD2() {
        return D2;
    }

    public FactorD setD2(boolean d2) {
        D2 = d2;
        return this;
    }

    public boolean isD3() {
        return D3;
    }

    public FactorD setD3(boolean d3) {
        D3 = d3;
        return this;
    }

    public boolean isD4() {
        return D4;
    }

    public FactorD setD4(boolean d4) {
        D4 = d4;
        return this;
    }

    public boolean isD5() {
        return D5;
    }

    public FactorD setD5(boolean d5) {
        D5 = d5;
        return this;
    }

    public boolean isD6() {
        return D6;
    }

    public FactorD setD6(boolean d6) {
        D6 = d6;
        return this;
    }
}
