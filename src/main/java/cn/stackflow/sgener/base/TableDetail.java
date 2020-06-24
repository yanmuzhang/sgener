package cn.stackflow.sgener.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: todo
 * @create: 2020-06-19 14:22
 */
public class TableDetail implements Serializable {
    private String tableName;
    private String className;
    private String comment;
    private List<ColumnDetail> columnList;

    public TableDetail() {
    }

    public TableDetail(String tableName, String className, String comment, List<ColumnDetail> columnList) {
        this.tableName = tableName;
        this.className = className;
        this.comment = comment;
        this.columnList = columnList;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ColumnDetail> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnDetail> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("tableName="+tableName+"\n");
        stringBuffer.append("className="+className+"\n");
        stringBuffer.append("comment="+comment+"\n");
        stringBuffer.append("columnList="+columnList+"\n");
        return stringBuffer.toString();
    }
}
