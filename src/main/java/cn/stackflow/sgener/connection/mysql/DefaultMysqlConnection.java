package cn.stackflow.sgener.connection.mysql;

import cn.stackflow.sgener.base.ColumnDetail;
import cn.stackflow.sgener.base.DatabaseType;
import cn.stackflow.sgener.base.TableDetail;
import cn.stackflow.sgener.connection.AbstractConnection;
import cn.stackflow.sgener.strategy.NameStrategy;
import netscape.javascript.JSUtil;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-24 14:27
 */
public class DefaultMysqlConnection extends AbstractConnection {


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
//            printResultSetMetaData(rs);
            while (rs.next()) {
                String name = rs.getString("TABLE_NAME");
                String component = rs.getString("REMARKS");

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

//            printResultSetMetaData(rs);
            while (rs.next()) {
                ColumnDetail columnDetail = new ColumnDetail();
                columnDetail.setPrimaryKey(false);
                columnDetail.setColumnSize(rs.getInt("COLUMN_SIZE"));
                columnDetail.setBufferLength(rs.getInt("BUFFER_LENGTH"));
                columnDetail.setNullable(rs.getInt("NULLABLE"));
                columnDetail.setColumnDef(rs.getString("COLUMN_DEF"));
                columnDetail.setAutoincrement("YES".equals(rs.getString("IS_AUTOINCREMENT")));
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


    public void printResultSetMetaData(ResultSet rs) {
        try {
            ResultSetMetaData m = rs.getMetaData();
            int columns=m.getColumnCount();
            //显示列,表格的表头
            for(int i=1;i<=columns;i++)
            {
                System.out.print(m.getColumnName(i));
                System.out.print("\t");
            }

            System.out.println();
            System.out.println();
            System.out.println();
            //显示表格内容
            while(rs.next())
            {
                for(int i=1;i<=columns;i++)
                {
                    System.out.print(rs.getString(i));
                    System.out.print("\t");
                }
                System.out.println();
            }
            rs.beforeFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private static String changeDbType(String dbType) {
        dbType = dbType.toUpperCase();
        switch (dbType) {
            case "VARCHAR":
            case "CHAR":
            case "TEXT":
                return "String";
            case "INT":
            case "TINYINT":
            case "SMALLINT":
            case "MEDIUMINT":
                return "Integer";
            case "ID":
            case "INTEGER":
                return "Long";
            case "BLOB":
                return "byte[]";
            case "BOOLEAN":
            case "BIT":
                return "Boolean";
            case "BIGINT":
                return "BigInteger";
            case "FLOAT":
                return "Float";
            case "DOUBLE":
                return "Double";
            case "DECIMAL":
                return "BigDecimal";
            case "DATE":
            case "YEAR":
                return "Date";
            case "TIME":
                return "Time";
            case "DATETIME":
            case "TIMESTAMP":
                return "Timestamp";
            default:
                return "Object";
        }
    }

}
