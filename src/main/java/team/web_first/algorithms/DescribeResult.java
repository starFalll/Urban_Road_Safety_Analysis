package team.web_first.algorithms;

import org.apache.ibatis.session.SqlSession;
import team.web_first.javabean.NewData;
import team.web_first.mapper.FactorMapper;

import java.util.ArrayList;
import java.util.List;

public class DescribeResult {
    protected static List<List<String>> DoGet() {
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
                    lineList.add(AllCol[j]);
                }

            }
            record.add(lineList);
        }
        return record;
    }
    public static void main(String[] args){
        List<List<String>> record = new ArrayList<List<String>>();
        record=DoGet();
        /**
         * 此处添加数据库接口，将置信度结果(data_result表中数据)放到List<List<String>>型数据结构中,
         * 以便我稍后根据置信度结果来处理record中的数据
         * 
         *
         *
         *
         *
         *
         *
         *
         *
         */
    }

}
