package team.web_first.javabean;

/**
 * Bean FactorB
 * 对应
 * DATABASE web_first
 * TABLE dangerous_driving
 */
public class FactorB {
    private int dangerousDrivingId;
    private int dangerousDrivingScore;

    private boolean B1, B2, B3, B4, B5, B6;

    public int getDangerousDrivingId() {
        return dangerousDrivingId;
    }

    public FactorB setDangerousDrivingId(int dangerousDrivingId) {
        this.dangerousDrivingId = dangerousDrivingId;
        return this;
    }

    public int getDangerousDrivingScore() {
        return dangerousDrivingScore;
    }

    public FactorB setDangerousDrivingScore(int dangerousDrivingScore) {
        this.dangerousDrivingScore = dangerousDrivingScore;
        return this;
    }


    public boolean isB1() {
        return B1;
    }

    public FactorB setB1(boolean b1) {
        B1 = b1;
        return this;
    }

    public boolean isB2() {
        return B2;
    }

    public FactorB setB2(boolean b2) {
        B2 = b2;
        return this;
    }

    public boolean isB3() {
        return B3;
    }

    public FactorB setB3(boolean b3) {
        B3 = b3;
        return this;
    }

    public boolean isB4() {
        return B4;
    }

    public FactorB setB4(boolean b4) {
        B4 = b4;
        return this;
    }

    public boolean isB5() {
        return B5;
    }

    public FactorB setB5(boolean b5) {
        B5 = b5;
        return this;
    }

    public boolean isB6() {
        return B6;
    }

    public FactorB setB6(boolean b6) {
        B6 = b6;
        return this;
    }
}
