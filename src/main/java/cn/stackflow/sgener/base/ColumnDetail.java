package cn.stackflow.sgener.base;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @create: 2020-06-24 14:17
 */
public class ColumnDetail {

    private boolean primaryKey;//主键
    private String attributeName;//属性名称
    private String columnName;// 列名称
    private Integer columnSize;// 列名称
    private Integer bufferLength;// 列名称
    private Integer nullable;// 可以为null
    private String columnDef;// 列默认值
    private Boolean autoincrement;//自增
    private String comment;//描述
    private String jdbcType;//数据库类型
    private String attributeType;// JAVA 属性类型


    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public Integer getBufferLength() {
        return bufferLength;
    }

    public void setBufferLength(Integer bufferLength) {
        this.bufferLength = bufferLength;
    }

    public Integer getNullable() {
        return nullable;
    }

    public void setNullable(Integer nullable) {
        this.nullable = nullable;
    }

    public String getColumnDef() {
        return columnDef;
    }

    public void setColumnDef(String columnDef) {
        this.columnDef = columnDef;
    }

    public Boolean getAutoincrement() {
        return autoincrement;
    }

    public void setAutoincrement(Boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    @Override
    public String toString() {
        return "ColumnDetail{" +
                "primaryKey=" + primaryKey +
                ", attributeName='" + attributeName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", columnSize=" + columnSize +
                ", bufferLength=" + bufferLength +
                ", nullable=" + nullable +
                ", columnDef='" + columnDef + '\'' +
                ", autoincrement=" + autoincrement +
                ", comment='" + comment + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", attributeType='" + attributeType + '\'' +
                '}\n"';
    }
}
