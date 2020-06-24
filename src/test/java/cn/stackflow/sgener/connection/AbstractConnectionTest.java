package cn.stackflow.sgener.connection;


import cn.stackflow.sgener.base.TableDetail;
import cn.stackflow.sgener.connection.mysql.DefaultMysqlConnection;

import java.util.List;

public class AbstractConnectionTest {

    public static void main(String[] args) {
        AbstractConnection connection = new DefaultMysqlConnection();
        List<TableDetail> tableList = connection.getTableList("");
        for (TableDetail tableDetail : tableList) {
            System.out.println(tableDetail);
        }
    }
}