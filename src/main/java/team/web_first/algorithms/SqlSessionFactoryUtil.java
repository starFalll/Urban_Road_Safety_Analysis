package team.web_first.algorithms;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * SqlSessionFactoryUtil
 * 全局唯一SqlSessionFactory
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

    private SqlSessionFactoryUtil() {
    }

    public static SqlSessionFactory initSqlSessionFactory() {
        String resource = "MyBatisConf.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        }catch (IOException e){
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK){
            if (sqlSessionFactory==null){
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            }
            return sqlSessionFactory;
        }
    }
    public static SqlSession openSqlsession(){
        if(sqlSessionFactory==null){
            initSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
