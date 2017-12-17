package team.web_first.algorithms;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;


public class Log4jUtil {
    private Logger logger=null;
    static {
        String resource = "log4j.properties";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        }catch (IOException e){
            e.printStackTrace();
        }
        PropertyConfigurator.configure(inputStream);
    }

    public Log4jUtil(Class eleClass){
        logger=Logger.getLogger(eleClass);
    }

    public Logger getLogger(){
        return logger;
    }

}
