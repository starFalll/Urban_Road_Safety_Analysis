package team.web_first.mapper;

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

    int addFactorA(FactorA factorA);

    int addFactorB(FactorB factorB);

    int addFactorC(FactorC factorC);

    int addFactorD(FactorD factorD);
}
