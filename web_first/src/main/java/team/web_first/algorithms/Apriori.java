package team.web_first.algorithms;

import org.apache.ibatis.session.SqlSession;
import team.web_first.algorithms.SqlSessionFactoryUtil;
import team.web_first.javabean.FactorAll;
import team.web_first.mapper.FactorMapper;

public class Apriori {
    protected void DoGet() {
        SqlSession sqlSession = null;

        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        FactorAll[] factorAlls=factorMapper.showFactorAll();//factorAlls为二维数组
        /**
         * Columns: personality_id, personality_score, dager_influence_coefficient, D1, D2, D3, D4, D5, D6
                 Row: 2, 0, 0, 1, 1, 0, 0, 1, 1 //factorAlls[0]
                 Row: 3, 0, 0, 1, 1, 0, 0, 1, 1 //factorAlls[1]
                 Row: 4, 0, 0, 1, 1, 1, 1, 1, 1
                 Row: 5, 0, 0, 0, 0, 0, 0, 0, 0
                 Row: 6, 0, 0, 0, 0, 0, 0, 0, 0
                 Row: 7, 0, 0, 1, 0, 1, 1, 0, 1
                 Row: 8, 0, 0, 0, 0, 0, 0, 0, 0 //factorAlls[6]

         */
    }
}
