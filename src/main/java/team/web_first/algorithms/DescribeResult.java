package team.web_first.algorithms;

import org.apache.ibatis.session.SqlSession;
import team.web_first.javabean.NewData;
import team.web_first.javabean.Result;
import team.web_first.mapper.FactorMapper;

import java.util.ArrayList;
import java.util.List;

public class DescribeResult {
    /**
     * 计数方法，计算A/B/C/D("道路风险感知能力", "危险驾驶行为", "驾驶能力自信", "人格特性")
     * 在最新插入的一条数据中的数量
     * @param total
     * @return
     */
    protected  List<List<String>> CalNewData(int[] total) {
        SqlSession sqlSession = null;
        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        NewData[] newdatas = factorMapper.showNewData();//NewData[]为一维数组
        List<List<String>> record = new ArrayList<List<String>>();

        String AllCol[] = {"A1", "A2", "A3", "A4", "A5", "A6", "B1", "B2", "B3", "B4", "B5", "B6", "C1", "C2", "C3",
                "C4", "C5", "C6", "D1", "D2", "D3", "D4", "D5", "D6"};
        for (int i = 0; i < newdatas.length; i++) {
            List<String> lineList = new ArrayList<String>();
            boolean[] values = newdatas[i].getAllBooleanValue();
            for (int j = 0; j < values.length; j++) {
                if (values[j] == true) {
                    if(AllCol[j].charAt(0)=='A')
                        total[0]++;
                    else if(AllCol[j].charAt(0)=='B')
                        total[1]++;
                    else if(AllCol[j].charAt(0)=='C')
                        total[2]++;
                    else if(AllCol[j].charAt(0)=='D')
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
     | result_id   | f_char             | s_char                   | confidence |
     +-------------+--------------------+--------------------------+------------+
     | 00000000001 | 危险驾驶行为       | 道路风险感知能力         |      0.376 |
     | 00000000002 | 驾驶能力自信       | 道路风险感知能力         |      0.689 |
     | 00000000003 | 人格特性           | 道路风险感知能力         |      0.608 |
     | 00000000004 | 驾驶能力自信       | 危险驾驶行为             |      0.696 |
     | 00000000005 | 人格特性           | 危险驾驶行为             |      0.551 |
     | 00000000006 | 人格特性           | 驾驶能力自信             |      0.583 |
     +-------------+--------------------+--------------------------+------------+
     * @return
     */
    protected  List<List<String>> DoGet_data_result() {
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
            Double confidence=new Double(results[i].getConfidence());

            lineList.add(confidence.toString());
            record.add(lineList);
        }
        sqlSession.close();
        return record;
    }
    public static void main(String[] args){
        List<List<String>> dataresultrecord = new ArrayList<List<String>>();
        int[] total={0,0,0,0};//计数器，计算A/B/C/D在最新插入的一条数据中的数量
        DescribeResult descriRes=new DescribeResult();
        descriRes.CalNewData(total);
        /**
         * 此处添加数据库接口，将置信度结果(data_result表中数据)放到List<List<String>>型数据结构中,
         * 以便我稍后根据置信度结果来处理record中的数据
         *
         */
        dataresultrecord=descriRes.DoGet_data_result();
        double[] calresults={total[0]/6.0,total[1]/6.0,total[2]/6.0,total[3]/6.0};
        System.out.println("calresults:"+calresults[0]+" "+calresults[1]+" "+calresults[2]+" "+calresults[3]);
        String[] FourTables = {"道路风险感知能力", "危险驾驶行为", "驾驶能力自信", "人格特性"};
        double dangerousDrivingScore=0,riskPerceptionScore=0,dangerousDrivingScoreall=1,riskPerceptionScoreall=1;
        dangerousDrivingScore+=calresults[1];
        riskPerceptionScore+=calresults[0];
        for(int i=0;i<dataresultrecord.size();i++){

            if(dataresultrecord.get(i).get(0).equals("危险驾驶行为")){
                for(int k=0;k<4;k++){
                    if(dataresultrecord.get(i).get(1).equals(FourTables[k])){
                        Double confidence=new Double(dataresultrecord.get(i).get(2));
                        dangerousDrivingScore+=calresults[k]*confidence;
                        dangerousDrivingScoreall+=confidence;
                    }
                }

            }
            else if(dataresultrecord.get(i).get(1).equals("危险驾驶行为")){
                for(int k=0;k<4;k++){
                    if(dataresultrecord.get(i).get(0).equals(FourTables[k])){
                        Double confidence=new Double(dataresultrecord.get(i).get(2));
                        dangerousDrivingScore+=calresults[k]*confidence;
                        dangerousDrivingScoreall+=confidence;
                    }
                }
            }
            if(dataresultrecord.get(i).get(0).equals("道路风险感知能力")){
                for(int k=0;k<4;k++){
                    if(dataresultrecord.get(i).get(1).equals(FourTables[k])){
                        Double confidence=new Double(dataresultrecord.get(i).get(2));
                        riskPerceptionScore+=calresults[k]*confidence;
                        riskPerceptionScoreall+=confidence;
                    }
                }

            }
            else if(dataresultrecord.get(i).get(1).equals("道路风险感知能力")){
                for(int k=0;k<4;k++){
                    if(dataresultrecord.get(i).get(0).equals(FourTables[k])){
                        Double confidence=new Double(dataresultrecord.get(i).get(2));
                        riskPerceptionScore+=calresults[k]*confidence;
                        riskPerceptionScoreall+=confidence;
                    }
                }
            }

        }
        /**
         * UI展示接口
         */
        double riskPerceptiongrade=riskPerceptionScore/riskPerceptionScoreall*100;
        double dangerousDrivinggrade=dangerousDrivingScore/dangerousDrivingScoreall*100;
        System.out.println("您的道路风险感知能力得分为:"+riskPerceptiongrade+"\n您的危险驾驶行为得分为:"+dangerousDrivingScore/dangerousDrivingScoreall*100);
        if(riskPerceptiongrade>60){
            System.out.println("您的道路风险感知能力良好!");
        }
        else{
            System.out.println("您的道路风险感知能力有待加强!");
        }

        if(dangerousDrivinggrade>60){
            System.out.println("您出现危险驾驶行为的概率较大，请谨慎驾驶!");
        }
        else{
            System.out.println("您的驾驶行为比较安全!");
        }


    }

}
