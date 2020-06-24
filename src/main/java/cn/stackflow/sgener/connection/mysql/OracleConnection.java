package cn.stackflow.sgener.connection.mysql;

import cn.stackflow.sgener.base.ColumnDetail;
import cn.stackflow.sgener.base.DatabaseType;
import cn.stackflow.sgener.base.TableDetail;
import cn.stackflow.sgener.connection.AbstractConnection;
import cn.stackflow.sgener.strategy.NameStrategy;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-24 14:27
 */
public class OracleConnection extends AbstractConnection {


    public String getDriver() {
        return "com.mysql.jdbc.Driver";
    }

    public String getUrl() {
        return "jdbc:mysql://localhost:3306/ess?useInformationSchema=true";
    }

    public String getUser() {
        return "root";
    }

    public String getPassword() {
        return "root";
    }

    public DatabaseMetaData getDatabaseMetaData() {
        try {
            return getConnection().getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public DatabaseType getDatabaseType() {
        return DatabaseType.MYSQL;
    }

    public List<String> getDatabaseList() {
        //  String[] strings = {"TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL  TEMPORARY", "ALIAS", "SYSNONYM"};

        return null;
    }

    public List<TableDetail> getTableList(String dbName) {
        List<TableDetail> tableList = new ArrayList<TableDetail>();
        try {
            ResultSet rs = getDatabaseMetaData().getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                String name = rs.getString(3);
                String component = rs.getString(5);
                List<ColumnDetail> columnList = getColumnList(name);

                TableDetail tableDetail = new TableDetail();
                tableDetail.setTableName(name);
                tableDetail.setClassName(NameStrategy.DEFAULT_STRATEGY.getClassName(name));
                tableDetail.setComment(component);
                tableDetail.setColumnList(columnList);
                tableList.add(tableDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tableList;
    }

    public List<ColumnDetail> getColumnList(String tableName) {
        List<ColumnDetail> columnList = new ArrayList<ColumnDetail>();
        try {
            ResultSet rs = getDatabaseMetaData().getColumns(null, null, tableName, null);
//            printResultSet(rs);
            while (rs.next()) {
                ColumnDetail columnDetail = new ColumnDetail();
                columnDetail.setColumnName(rs.getString("COLUMN_NAME"));
                columnDetail.setAttributeName(NameStrategy.DEFAULT_STRATEGY.getPropertyName(columnDetail.getColumnName()));
                columnDetail.setComment(rs.getString("REMARKS"));
                columnDetail.setJdbcType(rs.getString("TYPE_NAME"));
                columnDetail.setAttributeType(changeDbType(columnDetail.getJdbcType()));
                columnList.add(columnDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnList;
    }


    public void printResultSet(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println("字段名：" + rs.getString("COLUMN_NAME") + "--字段注释：" + rs.getString("REMARKS") + "--字段数据类型：" + rs.getString("TYPE_NAME"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private static String changeDbType(String dbType) {
        dbType = dbType.toUpperCase();
        switch (dbType) {
            case "VARCHAR":
            case "VARCHAR2":
            case "CHAR":
                return "1";
            case "NUMBER":
            case "DECIMAL":
                return "4";
            case "INT":
            case "SMALLINT":
            case "INTEGER":
                return "2";
            case "BIGINT":
                return "6";
            case "DATETIME":
            case "TIMESTAMP":
            case "DATE":
                return "7";
            default:
                return "1";
        }
    }

}
