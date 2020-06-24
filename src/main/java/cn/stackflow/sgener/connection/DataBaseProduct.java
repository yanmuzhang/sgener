package cn.stackflow.sgener.connection;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-24 14:37
 */
public interface DataBaseProduct {

    String getDatabaseProductName();//用以获得当前数据库是什么数据库。比如oracle，access等。返回的是字符串。

    String getDatabaseProductVersion();//获得数据库的版本。返回的字符串。

    String getDriverVersion();//获得驱动程序的版本。返回字符串。

//    String getTypeInfo();//获得当前数据库的类型信息

}
