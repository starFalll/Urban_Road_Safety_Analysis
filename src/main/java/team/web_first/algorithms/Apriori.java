package team.web_first.algorithms;

import com.mysql.cj.api.xdevapi.AddResult;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import team.web_first.javabean.FactorAll;
import team.web_first.javabean.Result;
import team.web_first.mapper.FactorMapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Apriori {
    static SqlSession sqlSession = null;
    static boolean isDo = false;
    static boolean endTag = false;
    static Map<Integer, Integer> dCountMap = new HashMap<Integer, Integer>(); // k-1频繁集的记数表
    static Map<Integer, Integer> dkCountMap = new HashMap<Integer, Integer>();// k频繁集的记数表
    static List<List<String>> record = new ArrayList<List<String>>();// 数据记录表
    final static double MIN_SUPPORT = 0.2;// 最小支持度
    final static double MIN_CONF = 0.8;// 最小置信度
    static int lable = 1;// 用于输出时的一个标记，记录当前在打印第几级关联集
    static List<Double> confCount = new ArrayList<Double>();// 置信度记录表
    static List<List<String>> confItemset = new ArrayList<List<String>>();// 满足置信度的集合

    static JSONArray results = new JSONArray();

    /**
     * 将数据库获取的数据转换成能够被算法用来处理的形式
     */
    protected static List<List<String>> DoGet() {
        List<List<String>> record = new ArrayList<List<String>>();

        //打开SQL session
        sqlSession = SqlSessionFactoryUtil.openSqlsession();
        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
        FactorAll[] factorAlls = factorMapper.showFactorAll();//factorAlls为二维数组
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
        String AllCol[] = {"A1", "A2", "A3", "A4", "A5", "A6", "B1", "B2", "B3", "B4", "B5", "B6", "C1", "C2", "C3",
                "C4", "C5", "C6", "D1", "D2", "D3", "D4", "D5", "D6"};
        for (int i = 0; i < factorAlls.length; i++) {
            List<String> lineList = new ArrayList<String>();
            boolean[] values = factorAlls[i].getAllBooleanValue();
            for (int j = 0; j < values.length; j++) {
                if (values[j] == true) {
                    lineList.add(AllCol[j]);
                }

            }
            record.add(lineList);
        }
        return record;

    }


/*    public static JSONArray getJson() {
        if (!isDo) {
            results = new JSONArray();
            main(null);
            System.out.println(results.toString());
            isDo = true;
            return results;
        } else return results;
    }*/

    /**
     * @param args
     */
    public static void main(String[] args) {
        results = new JSONArray();
        // TODO Auto-generated method stub
        record = DoGet();// 获取原始数据记录
        int i = 2;
        List<List<String>> cItemset = findFirstCandidate();// 获取第一次的备选集
        List<List<String>> lItemset = getSupportedItemset(cItemset);// 获取备选集cItemset满足支持的集合
        //System.out.print("第一次备选集满足支持的集合");
        //System.out.println(lItemset);
        while (endTag != true) {// 只要能继续挖掘
            List<List<String>> ckItemset = getNextCandidate(lItemset);// 获取第下一次的备选集
            //System.out.print("下一次备选集：");
            //System.out.println(ckItemset);
            List<List<String>> lkItemset = getSupportedItemset(ckItemset);// 获取备选集cItemset满足支持的集合
            //System.out.print("下一次备选集满足支持的集合：");
            //System.out.println(lkItemset);
            getConfidencedItemset(lkItemset, lItemset, dkCountMap, dCountMap);// 获取备选集cItemset满足置信度的集合
            //System.out.print("满足置信度的集合：");
            //System.out.println(confItemset);
            if (confItemset.size() != 0 && i < 4)// 满足置信度的集合不为空
            {
                System.out.println(+i + "中特性之间的影响:");
                i++;
                printConfItemset(confItemset, i);// 打印满足置信度的集合
            }
            if (i >= 4) {
                break;
            }
            confItemset.clear();// 清空置信度的集合
            cItemset = ckItemset;// 保存数据，为下次循环迭代准备
            lItemset = lkItemset;
            dCountMap.clear();
            dCountMap.putAll(dkCountMap);

        }

        System.out.println(results.toString());
        sqlSession.close();
    }

    /**
     * @param confItemset2 输出满足条件的频繁集
     */
    private static void printConfItemset(List<List<String>> confItemset2, int count) {
        char[] fourrelations = {'A', 'B', 'C', 'D'};
        String[] FourTables = {"道路风险感知能力", "危险驾驶行为", "驾驶能力自信", "人格特性"};
        List<String> Atotal = new ArrayList<String>();
        if (count == 3) {

            int[][] total = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};//对应A,B,C,D
            /**
             * XConfidence=支持度*置信度(即confItemset2.get(i).get(3))/total;
             */
            double[][] Confidences = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};//confidence[Y][X](X->Y)
            //A,B,C,D={0,B,C,D},{A,0,C,D},{A,B,0,D},{A,B,C,0}
            /**
             * confItemset2.get(i).get(j):相当于二维数组的内容如下
             *   j
             *   0   1   2         3
             * i X   Y  相对支持度 置信度
             * 0 B1 A2  0.52453   1.0
             * 1 C2 B4  0.53434   1.0
             * 2.....
             * 3.....
             */
            for (int i = 0; i < confItemset2.size(); i++) {
                for (int k = 0; k < 4; k++) {
                    if (confItemset2.get(i).get(1).charAt(0) == fourrelations[k]) {//X-->Y中Y
                        for (int m = 0; m < 4; m++) {
                            if (confItemset2.get(i).get(0).charAt(0) == fourrelations[m]
                                    && confItemset2.get(i).get(0).charAt(0) != confItemset2.get(i).get(1).charAt(0)) {//X-->Y中X
                                double confidence = new Double(confItemset2.get(i).get(3));
                                double support = new Double(confItemset2.get(i).get(2));
                                Confidences[k][m] += confidence * support;
                                total[k][m]++;
                            }
                        }

                    }

                }
            }
            for (int k = 0; k < 4; k++) {
                for (int m = 0; m < 4; m++) {
                    if (k != m && total[k][m] != 0) {
                        Confidences[k][m] /= total[k][m];

                    }
                }
            }

            for (int k = 0; k < 4; k++) {
                for (int m = k; m < 4; m++) {
                    if (k != m) {
                        double Confidence = (Confidences[k][m] + Confidences[m][k]) / 2;
                        System.out.println(FourTables[m] + "  与  " + FourTables[k] + " 的相关系数为:" + Confidences[k][m]);
/*                        JSONObject result = new JSONObject();
                        result.put("name1", FourTables[m]);
                        result.put("name2", FourTables[k]);
                        result.put("confidence", new BigDecimal(Confidences[k][m]).setScale(3, RoundingMode.HALF_EVEN));*/
                        Result result1=new Result(FourTables[m],FourTables[k],new BigDecimal(Confidences[k][m]).setScale(3, RoundingMode.HALF_EVEN).doubleValue());
                        sqlSession = SqlSessionFactoryUtil.openSqlsession();
                        FactorMapper factorMapper = sqlSession.getMapper(FactorMapper.class);
                        factorMapper.addResult(result1);
                        sqlSession.commit();
/*                        results.put(result);*/
                    }
                }
            }
        }
   /*
        else if(count==4){
            int[][][] total={{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                    {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                    {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                    {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}};
            double[][][] Confidences={{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                    {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                    {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}},
                    {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}};
            /**
             * confItemset2.get(i).get(j):相当于数组的内容如下
             *   j
             *   0    1    2    3         4
             * i X1  X2    Y  相对支持度 置信度
             * 0 B1  A2   C3  0.52453   1.0
             * 1 C2  D4   B4  0.53434   1.0
             * 2.....
             * 3.....
             *
            for (int i = 0; i < confItemset2.size(); i++){
                for(int k=0;k<4;k++) {
                    if (confItemset2.get(i).get(2).charAt(0) == fourrelations[k]) {//X1 X2-->Y中Y
                        for(int m=0;m<4;m++) {
                            for (int n = 0; n < 4; n++) {
                                if (confItemset2.get(i).get(0).charAt(0) == fourrelations[m]
                                        && confItemset2.get(i).get(0).charAt(0) != confItemset2.get(i).get(2).charAt(0)) {//X1 X2-->Y中X1
                                    double confidence = new Double(confItemset2.get(i).get(4));
                                    double support = new Double(confItemset2.get(i).get(3));
                                    Confidences[k][m] += confidence * support;
                                    total[k][m]++;
                                }
                            }
                        }
                    }

                }
            }


        }


        System.out.print("*********频繁模式挖掘结果***********\n");
        for (int i = 0; i < confItemset2.size(); i++) {
            int j = 0;
            for (j = 0; j < confItemset2.get(i).size() - 3; j++) {
                System.out.print(confItemset2.get(i).get(j) + " ");
            }
            System.out.print("-->");
            System.out.print(confItemset2.get(i).get(j++));
            System.out.print("相对支持度：" + confItemset2.get(i).get(j++));
            float confidence=new Float(confItemset2.get(i).get(j++));
            System.out.print("自信度：" + confidence + "\n");
        }*/

    }

    /**
     * @param lkItemset
     * @param lItemset
     * @param dkCountMap2
     * @param dCountMap2  根据lkItemset，lItemset，dkCountMap2，dCountMap2求出满足自信度的集合
     */
    private static List<List<String>> getConfidencedItemset(
            List<List<String>> lkItemset, List<List<String>> lItemset,
            Map<Integer, Integer> dkCountMap2, Map<Integer, Integer> dCountMap2) {
        for (int i = 0; i < lkItemset.size(); i++) {
            getConfItem(lkItemset.get(i), lItemset, dkCountMap2.get(i),
                    dCountMap2);

        }
        return null;
    }

    /**
     * @param list
     * @param lItemset
     * @param count
     * @param dCountMap2 检验集合list是否满足最低自信度要求
     *                   若满足则在全局变量confItemset添加list
     *                   如不满足则返回null
     */
    private static List<String> getConfItem(List<String> list,
                                            List<List<String>> lItemset, Integer count,
                                            Map<Integer, Integer> dCountMap2) {
        for (int i = 0; i < list.size(); i++) {
            List<String> testList = new ArrayList<String>();
            for (int j = 0; j < list.size(); j++)
                if (i != j)
                    testList.add(list.get(j));
            int index = findConf(testList, lItemset);//查找testList中的内容在lItemset的位置
            Double conf = count * 1.0 / dCountMap2.get(index);
            if (conf > MIN_CONF) {//满足自信度要求
                testList.add(list.get(i));
                Double relativeSupport = count * 1.0 / (record.size() - 1);
                testList.add(relativeSupport.toString());
                testList.add(conf.toString());
                confItemset.add(testList);//添加到满足自信度的集合中
            }
        }
        return null;
    }

    /**
     * @param testList
     * @param lItemset 查找testList中的内容在lItemset的位置
     */
    private static int findConf(List<String> testList,
                                List<List<String>> lItemset) {
        for (int i = 0; i < lItemset.size(); i++) {
            boolean notHaveTag = false;
            for (int j = 0; j < testList.size(); j++) {
                if (haveThisItem(testList.get(j), lItemset.get(i)) == false) {
                    notHaveTag = true;
                    break;
                }
            }
            if (notHaveTag == false)
                return i;
        }
        return -1;
    }

    /**
     * @param string
     * @param list   检验list中是否包含string
     * @return boolean
     */
    private static boolean haveThisItem(String string, List<String> list) {
        for (int i = 0; i < list.size(); i++)
            if (string.equals(list.get(i)))
                return true;
        return false;
    }

    /**
     * @param cItemset 求出cItemset中满足最低支持度集合
     */
    private static List<List<String>> getSupportedItemset(
            List<List<String>> cItemset) {
        // TODO Auto-generated method stub
        boolean end = true;
        List<List<String>> supportedItemset = new ArrayList<List<String>>();
        int k = 0;
        //System.out.println("------------------");
        for (int i = 0; i < cItemset.size(); i++) {
            int count = countFrequent(cItemset.get(i));//统计记录数
            //System.out.println("count:"+count);
            if (count >= MIN_SUPPORT * (record.size() - 1)) {// count值大于支持度与记录数的乘积，即满足支持度要求
                if (cItemset.get(0).size() == 1)
                    dCountMap.put(k++, count);
                else
                    dkCountMap.put(k++, count);
                supportedItemset.add(cItemset.get(i));
                end = false;
            }
        }
        //System.out.println(dCountMap);
        //System.out.println(dkCountMap);
        endTag = end;
        return supportedItemset;
    }

    /**
     * @param list 统计数据库记录record中出现list中的集合的个数
     */
    private static int countFrequent(List<String> list) {
        int count = 0;
        for (int i = 1; i < record.size(); i++) {
            boolean notHavaThisList = false;
            for (int k = 0; k < list.size(); k++) {
                boolean thisRecordHave = false;
                for (int j = 1; j < record.get(i).size(); j++) {
                    if (list.get(k).equals(record.get(i).get(j)))
                        thisRecordHave = true;
                }
                if (!thisRecordHave) {// 扫描一遍记录表的一行，发现list.get(i)不在记录表的第j行中，即list不可能在j行中
                    notHavaThisList = true;
                    break;
                }
            }
            if (notHavaThisList == false)
                count++;
        }
        return count;
    }

    /**
     * @param cItemset
     * @return nextItemset
     * 根据cItemset求出下一级的备选集合组，求出的备选集合组中的每个集合的元素的个数比cItemset中的集合的元素大1
     */
    private static List<List<String>> getNextCandidate(
            List<List<String>> cItemset) {
        List<List<String>> nextItemset = new ArrayList<List<String>>();
        for (int i = 0; i < cItemset.size(); i++) {
            List<String> tempList = new ArrayList<String>();
            for (int k = 0; k < cItemset.get(i).size(); k++)
                tempList.add(cItemset.get(i).get(k));
            for (int h = i + 1; h < cItemset.size(); h++) {
                for (int j = 0; j < cItemset.get(h).size(); j++) {
                    tempList.add(cItemset.get(h).get(j));
                    if (isSubsetInC(tempList, cItemset)) {// tempList的子集全部在cItemset中
                        List<String> copyValueHelpList = new ArrayList<String>();
                        for (int p = 0; p < tempList.size(); p++)
                            copyValueHelpList.add(tempList.get(p));
                        if (isHave(copyValueHelpList, nextItemset))//nextItemset还没有copyValueHelpList这个集合
                            nextItemset.add(copyValueHelpList);
                    }
                    tempList.remove(tempList.size() - 1);
                }
            }
        }

        return nextItemset;
    }

    /**
     * @param copyValueHelpList
     * @param nextItemset
     * @return boolean
     * 检验nextItemset中是否包含copyValueHelpList
     */
    private static boolean isHave(List<String> copyValueHelpList,
                                  List<List<String>> nextItemset) {
        for (int i = 0; i < nextItemset.size(); i++)
            if (copyValueHelpList.equals(nextItemset.get(i)))
                return false;
        return true;
    }

    /**
     * @param tempList
     * @param cItemset
     * @return 检验 tempList是不是cItemset的子集
     */
    private static boolean isSubsetInC(List<String> tempList,
                                       List<List<String>> cItemset) {
        boolean haveTag = false;
        for (int i = 0; i < tempList.size(); i++) {// k集合tempList的k-1子集是否都在k-1级频繁级中
            List<String> testList = new ArrayList<String>();
            for (int j = 0; j < tempList.size(); j++)
                if (i != j)
                    testList.add(tempList.get(j));
            for (int k = 0; k < cItemset.size(); k++) {
                if (testList.equals(cItemset.get(k))) {// 子集存在于k-1频繁集中
                    haveTag = true;
                    break;
                }
            }
            if (haveTag == false)// 其中一个子集不在k-1频繁集中
                return false;
        }

        return haveTag;
    }

    /**
     * 根据数据库记录求出第一级备选集
     */
    private static List<List<String>> findFirstCandidate() {
        List<List<String>> tableList = new ArrayList<List<String>>();
        List<String> lineList = new ArrayList<String>();

        int size = 0;
        for (int i = 1; i < record.size(); i++) {
            for (int j = 1; j < record.get(i).size(); j++) {
                if (lineList.isEmpty()) {
                    lineList.add(record.get(i).get(j));
                } else {
                    boolean haveThisItem = false;
                    size = lineList.size();
                    for (int k = 0; k < size; k++) {
                        if (lineList.get(k).equals(record.get(i).get(j))) {
                            haveThisItem = true;
                            break;
                        }
                    }
                    if (haveThisItem == false)
                        lineList.add(record.get(i).get(j));
                }
            }
        }
        System.out.println(lineList);
        for (int i = 0; i < lineList.size(); i++) {
            List<String> helpList = new ArrayList<String>();

            helpList.add(lineList.get(i));
            tableList.add(helpList);
        }
        //System.out.println(tableList);
        return tableList;
    }


}
