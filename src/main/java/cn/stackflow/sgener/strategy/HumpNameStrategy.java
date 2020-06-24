package cn.stackflow.sgener.strategy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: zhangc/jaguar_zc@sina.com
 * @description: 驼峰命名法【默认的】
 * @create: 2020-06-24 15:38
 */
public class HumpNameStrategy implements NameStrategy {

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");


    /**
     * 下划线转驼峰
     */
    public  String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public  String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 驼峰转下划线,效率比上面高
     */
    public  String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Override
    public String getTableName(String className) {
        return getColumnName(className);
    }

    @Override
    public String getClassName(String tableName) {
        String propertyName = getPropertyName(tableName);
        char[] chars = propertyName.toCharArray();
        chars[0] -=32;
        return new String(chars);
    }

    @Override
    public String getPropertyName(String columnName) {
        return lineToHump(columnName);
    }

    @Override
    public String getColumnName(String propertyName) {
        return humpToLine(propertyName);
    }


}
