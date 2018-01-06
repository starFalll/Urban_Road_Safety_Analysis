package team.web_first.algorithms;

import org.apache.ibatis.session.SqlSession;
import team.web_first.javabean.NewData;
import team.web_first.javabean.PersResult;
import team.web_first.javabean.Result;
import team.web_first.javabean.ResultTwo;
import team.web_first.mapper.FactorMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class DescribeResult {
    /**
     * 计数方法，计算A/B/C/D("道路风险感知能力", "危险驾驶行为", "驾驶能力自信", "人格特性")
     * 在最新插入的一条数据中的数量
     *
     * @param total
     * @return
     */

    private int recordId = 1;
    private PersResult persResult = new PersResult();

    public DescribeResult() {
    }

    ;//测试用

    public DescribeResult(int recordId) {
        this.recordId = recordId;
    }

    /**
     * 记录最新一条数据中四种因素的数量
     *
     * @param total
     * @return
     */
    protected List<List<String>> CalNewData(int[] total) {
        SqlSession sqlSession = null;
        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        NewData[] newdatas = factorMapper.showNewData(recordId);//NewData[]为一维数组
        List<List<String>> record = new ArrayList<List<String>>();
        String AllCol[] = {"A1", "A2", "A3", "A4", "A5", "A6", "B1", "B2", "B3", "B4", "B5", "B6", "C1", "C2", "C3",
                "C4", "C5", "C6", "D1", "D2", "D3", "D4", "D5", "D6"};
        for (int i = 0; i < newdatas.length; i++) {
            List<String> lineList = new ArrayList<String>();
            boolean[] values = newdatas[i].getAllBooleanValue();
            for (int j = 0; j < values.length; j++) {
                if (values[j] == true) {
                    if (AllCol[j].charAt(0) == 'A')
                        total[0]++;
                    else if (AllCol[j].charAt(0) == 'B')
                        total[1]++;
                    else if (AllCol[j].charAt(0) == 'C')
                        total[2]++;
                    else if (AllCol[j].charAt(0) == 'D')
                        total[3]++;
                    lineList.add(AllCol[j]);
                }

            }
            record.add(lineList);
        }
        sqlSession.close();
        return record;
    }

    /**
     * 获取关联挖掘结果集中的数据，如下：
     * +-------------+--------------------+--------------------------+------------+
     * | result_id   | f_char             | s_char                   | confidence |
     * +-------------+--------------------+--------------------------+------------+
     * | 00000000001 | 危险驾驶行为       | 道路风险感知能力         |      0.376 |
     * | 00000000002 | 驾驶能力自信       | 道路风险感知能力         |      0.689 |
     * | 00000000003 | 人格特性           | 道路风险感知能力         |      0.608 |
     * | 00000000004 | 驾驶能力自信       | 危险驾驶行为             |      0.696 |
     * | 00000000005 | 人格特性           | 危险驾驶行为             |      0.551 |
     * | 00000000006 | 人格特性           | 驾驶能力自信             |      0.583 |
     * +-------------+--------------------+--------------------------+------------+
     *
     * @return
     */
    protected List<List<String>> DoGet_data_result() {
        SqlSession sqlSession = null;
        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        Result[] results = factorMapper.showResult();
        List<List<String>> record = new ArrayList<List<String>>();

        for (int i = 0; i < results.length; i++) {
            List<String> lineList = new ArrayList<String>();
            lineList.add(results[i].getfChar());
            lineList.add(results[i].getsChar());
            Double confidence = new Double(results[i].getConfidence());

            lineList.add(confidence.toString());
            record.add(lineList);
        }
        sqlSession.close();
        return record;
    }


    /**
     * 获取两层之间的数据
     * X1,X2->Y
     * <p>
     * 0    1    2    3         4
     * X1  X2    Y  相对支持度 置信度
     * B1  A2   C3  0.52453   1.0
     * C2  D4   B4  0.53434   1.0
     *
     * @return
     */
    protected List<List<String>> DoGet_data_result_Two() {
        SqlSession sqlSession = null;
        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        ResultTwo[] resultTwos = factorMapper.showResultTwo();
        List<List<String>> record = new ArrayList<List<String>>();
        for (int i = 0; i < resultTwos.length; i++) {
            //X1,X2->Y
            //lineList[0]为Y的内容,lineList[1]为X1的内容,lineList[2]为X2的内容,
            // lineList[3]为confidenceTwo的内容转化成字符串
            List<String> lineList = new ArrayList<String>();
            lineList.add(resultTwos[i].getMainChar());
            lineList.add(resultTwos[i].getFirstChar());
            lineList.add(resultTwos[i].getSecondChar());
            Double confidence = new Double(resultTwos[i].getConfidenceTwo());
            lineList.add(confidence.toString());
            record.add(lineList);
        }
        sqlSession.close();
        return record;
    }

    public PersResult getPersResultOne() {
        List<List<String>> dataresultrecord;//= new ArrayList<List<String>>();

        int[] total = {0, 0, 0, 0};//计数器，计算A/B/C/D在最新插入的一条数据中的数量


        this.CalNewData(total);
        dataresultrecord = this.DoGet_data_result();

        double[] calresults = {total[0] / 6.0, total[1] / 6.0, total[2] / 6.0, total[3] / 6.0};
        System.out.println("calresults:" + calresults[0] + " " + calresults[1] + " " + calresults[2] + " " + calresults[3]);
        String[] FourTables = {"道路风险感知能力", "危险驾驶行为", "驾驶能力自信", "人格特性"};

        double dangerousDrivingScore = 0, riskPerceptionScore = 0, confidenceScore = 0;
        int dangerousDrivingScoreall = 0, riskPerceptionScoreall = 0, confidenceScoreall = 0;

        //dangerousDrivingScore += calresults[1];
        //riskPerceptionScore += calresults[0];
        //confidenceScore += calresults[2];

        for (int i = 0; i < dataresultrecord.size(); i++) {

            if (dataresultrecord.get(i).get(0).equals("危险驾驶行为")) {
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(1).equals(FourTables[k])) {
                        Double confidence = new Double(dataresultrecord.get(i).get(2));
                        dangerousDrivingScore += calresults[k] * confidence;
                        dangerousDrivingScoreall++;
                    }
                }

            } else if (dataresultrecord.get(i).get(1).equals("危险驾驶行为")) {
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(0).equals(FourTables[k])) {
                        Double confidence = new Double(dataresultrecord.get(i).get(2));
                        dangerousDrivingScore += calresults[k] * confidence;
                        dangerousDrivingScoreall++;
                    }
                }
            }
            if (dataresultrecord.get(i).get(0).equals("道路风险感知能力")) {
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(1).equals(FourTables[k])) {
                        Double confidence = new Double(dataresultrecord.get(i).get(2));
                        riskPerceptionScore += calresults[k] * confidence;
                        riskPerceptionScoreall++;
                    }
                }

            } else if (dataresultrecord.get(i).get(1).equals("道路风险感知能力")) {
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(0).equals(FourTables[k])) {
                        Double confidence = new Double(dataresultrecord.get(i).get(2));
                        riskPerceptionScore += calresults[k] * confidence;
                        riskPerceptionScoreall++;
                    }
                }
            }
            if (dataresultrecord.get(i).get(0).equals("驾驶能力自信")) {
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(1).equals(FourTables[k])) {
                        Double confidence = new Double(dataresultrecord.get(i).get(2));
                        confidenceScore += calresults[k] * confidence;
                        confidenceScoreall++;
                    }
                }

            } else if (dataresultrecord.get(i).get(1).equals("驾驶能力自信")) {
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(0).equals(FourTables[k])) {
                        Double confidence = new Double(dataresultrecord.get(i).get(2));
                        confidenceScore += calresults[k] * confidence;
                        confidenceScoreall++;
                    }
                }
            }

        }
        /**
         * UI展示接口
         */
        double riskPerceptiongrade = riskPerceptionScore / riskPerceptionScoreall * 100;
        double dangerousDrivinggrade = 100 - (dangerousDrivingScore / dangerousDrivingScoreall * 100);
        double confidencegrade = confidenceScore / confidenceScoreall * 100;
        System.out.println("您的道路风险感知能力得分为:" + riskPerceptiongrade + "\n您的危险驾驶行为得分为:" + dangerousDrivinggrade

                + "\n您的驾驶能力自信的分为:" + confidencegrade);
        persResult.setAbiOneScore(new BigDecimal(riskPerceptiongrade).setScale(3, RoundingMode.HALF_EVEN).doubleValue());
        persResult.setAbiTwoScore(new BigDecimal(dangerousDrivinggrade).setScale(3, RoundingMode.HALF_EVEN).doubleValue());
        persResult.setAbiThrScore(new BigDecimal(confidencegrade).setScale(3, RoundingMode.HALF_EVEN).doubleValue());
        if (riskPerceptiongrade > 90) {
            System.out.println("您的道路风险感知能力很好!");
            persResult.setOneDegree(0);
        } else if (riskPerceptiongrade > 60) {
            System.out.println("您的道路风险感知能力较好!");
            persResult.setOneDegree(1);
        } else if (riskPerceptiongrade > 30) {
            System.out.println("您的道路风险感知能力有待加强!");
            persResult.setOneDegree(2);
        } else {
            System.out.println("您的道路风险感知能力十分有待加强!");
            persResult.setOneDegree(3);
        }


        if (dangerousDrivinggrade > 90) {
            System.out.println("您的驾驶行为比较安全!");
            persResult.setTwoDegree(0);
        } else if (dangerousDrivinggrade > 60) {
            System.out.println("您出现危险驾驶行为的概率较小，请再接再厉!");
            persResult.setTwoDegree(1);
        } else if (dangerousDrivinggrade > 30) {
            System.out.println("您出现危险驾驶行为的概率较大，请谨慎驾驶!");
            persResult.setTwoDegree(2);
        } else {
            System.out.println("您出现危险驾驶行为的概率很大，请谨慎驾驶!");
            persResult.setTwoDegree(3);

        }

        if (confidencegrade > 90) {
            System.out.println("您驾驶汽车很有自信!");
            persResult.setThrDegree(0);
        } else if (confidencegrade > 60) {
            System.out.println("您驾驶汽车较有自信!");
            persResult.setThrDegree(1);
        } else if (confidencegrade > 30) {
            System.out.println("您驾驶汽车不太自信!");
            persResult.setThrDegree(2);
        } else {
            System.out.println("您驾驶汽车很不自信!");
            persResult.setThrDegree(3);
        }
        return persResult;
    }
    /*public PersResult getPersResult() {*/

    /**
     * 登录用户的两种因素对另一种因素的影响
     * 对于每一种因素，计算出它影响最大的两种因素
     */
    protected void getPersResultTwo() {
        List<List<String>> dataresultrecord;

        int[] total = {0, 0, 0, 0};//计数器，计算A/B/C/D在最新插入的一条数据中的数量

        this.CalNewData(total);

        dataresultrecord = this.DoGet_data_result_Two();

        double[] calresults = {total[0] / 6.0, total[1] / 6.0, total[2] / 6.0, total[3] / 6.0};
        System.out.println("calresults:" + calresults[0] + " " + calresults[1] + " " + calresults[2] + " " + calresults[3]);
        String[] FourTables = {"道路风险感知能力", "危险驾驶行为", "驾驶能力自信", "人格特性"};
        double influenceScores[][][] = {{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}},
                {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}};
        //第一维为Y([0]为"道路风险感知能力"，[1]为"危险驾驶行为",[2]为"驾驶能力自信"),第二维为X1,第三维是X3,用来记录X1,X2对Y的影响强弱
        for (int i = 0; i < dataresultrecord.size(); i++) {

            if (dataresultrecord.get(i).get(0).equals("道路风险感知能力")) {//X1,X2->Y为危险驾驶行为
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(1).equals(FourTables[k])) {
                        for (int m = 0; m < 4; m++) {
                            if (dataresultrecord.get(i).get(2).equals(FourTables[m])) {
                                Double confidence = new Double(dataresultrecord.get(i).get(3));
                                influenceScores[0][k][m] = (calresults[k] + calresults[m]) / 2 * confidence;
                            }
                        }
                    }
                }

            } else if (dataresultrecord.get(i).get(0).equals("危险驾驶行为")) {//X1,X2->Y为道路风险感知能力
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(1).equals(FourTables[k])) {
                        for (int m = 0; m < 4; m++) {
                            if (dataresultrecord.get(i).get(2).equals(FourTables[m])) {
                                Double confidence = new Double(dataresultrecord.get(i).get(3));
                                influenceScores[1][k][m] = (calresults[k] + calresults[m]) / 2 * confidence;
                            }
                        }
                    }
                }
            } else if (dataresultrecord.get(i).get(0).equals("驾驶能力自信")) {//X1,X2->Y为驾驶能力自信
                for (int k = 0; k < 4; k++) {
                    if (dataresultrecord.get(i).get(1).equals(FourTables[k])) {
                        for (int m = 0; m < 4; m++) {
                            if (dataresultrecord.get(i).get(2).equals(FourTables[m])) {
                                Double confidence = new Double(dataresultrecord.get(i).get(3));
                                influenceScores[2][k][m] = (calresults[k] + calresults[m]) / 2 * confidence;
                            }
                        }
                    }
                }

            }
        }
        double max = 0;
        int re1 = 0, re2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 4; k++) {
                for (int m = 0; m < 4; m++) {
                    if (influenceScores[i][k][m] > max) {
                        max = influenceScores[i][k][m];
                        re1 = k;
                        re2 = m;
                    }
                }

            }
            System.out.println(FourTables[i] + " 受 " + FourTables[re1] + " 和 " + FourTables[re2] + " 影响最大");
        }
    }

    public static void main(String[] args) {
        DescribeResult test = new DescribeResult();
        test.getPersResultOne();
        test.getPersResultTwo();
    }

}
