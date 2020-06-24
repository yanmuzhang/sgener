package cn.stackflow.sgener.connection;

import cn.stackflow.sgener.base.ColumnDetail;
import cn.stackflow.sgener.base.DatabaseType;
import cn.stackflow.sgener.base.TableDetail;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-24 14:19
 */
public abstract class AbstractConnection implements DataBaseProduct {

    protected Connection connection;

    {
        try {
            //1.加载驱动程序
            Class.forName(getDriver());
            //2. 获得数据库连接
            connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract String getDriver();

    public abstract String getUrl();

    public abstract String getUser();

    public abstract String getPassword();

    public String getDatabaseProductName() {
        try {
            return getDatabaseMetaData().getDatabaseProductName();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public String getDatabaseProductVersion() {
        try {
            return getDatabaseMetaData().getDatabaseProductVersion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public String getDriverVersion() {
        try {
            return getDatabaseMetaData().getDriverVersion();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public abstract DatabaseMetaData getDatabaseMetaData();

    public abstract DatabaseType getDatabaseType();

    public abstract List<String> getDatabaseList();

    public abstract List<TableDetail> getTableList(String dbName);

    public abstract List<ColumnDetail> getColumnList(String tableName);

}
