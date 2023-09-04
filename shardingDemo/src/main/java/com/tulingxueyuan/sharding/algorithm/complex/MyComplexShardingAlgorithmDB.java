package com.tulingxueyuan.sharding.algorithm.complex;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.*;

/**
* @author: LCG
* @date: 2022-11-12 18:52:43
* @description: 自定义 多字段分片算法 按照数据库划分
**/
@Slf4j
public class MyComplexShardingAlgorithmDB implements ComplexKeysShardingAlgorithm<String> {

    private static final String column1="cid";

    private static final String column2="user_id";

    /**
     * @author: LCG
     * @date: 2022-11-12 23:19:16
     * @description:  collection  数据库的逻辑名称集合
     *                complexKeysShardingValue  复合分片字段的字段名和字段值
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<String> complexKeysShardingValue) {

        String logicTableName = complexKeysShardingValue.getLogicTableName();
        //等于号   in 的值
        Map<String, Collection<String>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        //实现  >，>=, <=，<  和 BETWEEN AND 等操作
        Map columnNameAndRangeValuesMap = complexKeysShardingValue.getColumnNameAndRangeValuesMap();
 
        Collection<String> c1 = columnNameAndShardingValuesMap.get(column1);
        Collection<String> c2 = columnNameAndShardingValuesMap.get(column2);
        if(Optional.ofNullable(c1).isPresent()&&Optional.ofNullable(c2).isPresent()){
            StringBuffer sb=new StringBuffer(c1.stream().findFirst().get());
            sb.append(c2.stream().findFirst().get());
            int i = sb.toString().hashCode() % collection.size();
            String s = i == 0 ? "m1" : "m2";
            return Collections.singletonList(s);
        }
        return collection;
    }
}
