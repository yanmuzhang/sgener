package cn.stackflow.sgener.strategy;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-24 15:38
 */
public interface NameStrategy {

    public NameStrategy DEFAULT_STRATEGY = new HumpNameStrategy();


    String getTableName(String className);

    String getClassName(String tableName);

    String getPropertyName(String columnName);

    String getColumnName(String propertyName);


}
