package team.web_first.mapper;

import org.apache.ibatis.annotations.Param;
import team.web_first.javabean.*;

/**
 * Mapping interface
 * FactorMapper.xml
 */
public interface FactorMapper {
    FactorA[] showFactorA();

    FactorB[] showFactorB();

    FactorC[] showFactorC();

    FactorD[] showFactorD();

    FactorAll[] showFactorAll();

    NewData[] showNewData(@Param("urRecordId") int urRecordId);

    Result[] showResult();

    int addFactorA(FactorA factorA);

    int addFactorB(FactorB factorB);

    int addFactorC(FactorC factorC);

    int addFactorD(FactorD factorD);

    int addResult(Result result);
}
