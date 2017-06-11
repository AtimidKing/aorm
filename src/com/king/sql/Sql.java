package com.king.sql;


import java.util.Set;

/**
 * Created by yking on 17-6-8.
 * SQL语句的合成
 * String insert = "INSERT INTO table ( field ) VALUES( value )";
 * String update = "UPDATE table SET value WHERE condition";
 * String delete = "DELETE FROM table WHERE condition";
 * String search = "SELECT field FROM table WHERE 1=1 condition";
 */
public class Sql<P> {

    /**
     * 合成insect语句
     */
    public String getInsect(String table , java.util.Set field) throws Exception {
        //insert语句的模板
        String insert = "INSERT INTO table ( field ) VALUES( value )";
        //替换table
        insert = replaceTable(table, insert);
        //替换field
        insert = replaceField(field, insert);
        //添加value，
        insert = replaceValue(field, insert);
        return insert;
    }

    /**
     * 替换sql中的value
     *
     * @param sql sql 语句，
     * @param set 字段的集合，
     * @return 返回执行后的SQL语句。
     */
    private String replaceValue(java.util.Set set, String sql) {
        StringBuilder temp = new StringBuilder();
        set.forEach(k -> temp.append("?" + ","));
        temp.deleteCharAt(temp.length() - 1);
        sql = sql.replace("value", temp.toString());
        return sql;
    }

    /**
     * 替换SQL中的field。
     *
     * @param sql sql语句，
     * @param set 字段集合，
     * @return 返回执行后的SQL语句。
     */
    private String replaceField(java.util.Set set, String sql) {
        StringBuilder temp = new StringBuilder();
        //遍历集合，添加到temp中
        set.forEach(k -> temp.append(setString(k) + ","));
        //替换字段。
        sql = sql.replace("field", temp.deleteCharAt(temp.length() - 1).toString());
        return sql;
    }

    /**
     * 替换SQL中的table。
     *
     * @param sql sql语句，
     * @param table  表名，
     * @return 返回执行后的SQL语句。
     */
    private String replaceTable(String table, String sql) {
        //替换table
        sql = sql.replace("table", setString(table));
        return sql;
    }

    /**
     * 为字段添加“`”包裹起来。
     * */
    private String setString(Object obj) {
        return "`" + obj.toString() + "`";
    }

    /**
     * 合成Update语句
     */
    public String getUpdate(String table,java.util.Set field,java.util.Set condition) throws Exception {
        String update = "UPDATE table SET value WHERE condition";
        //替换table
        update=replaceTable(table,update);
        //替换value
        update=replaceUpdateValue(field,update);
        //替换condition条件
        update=replaceCondition(condition,update);
        return update;
    }

    private String replaceUpdateValue(Set fieldNames, String update) {
        StringBuilder temp= new StringBuilder();
        fieldNames.forEach(k -> temp.append(setString(k)+"=?,"));
        temp.deleteCharAt(temp.length()-1);
        return update.replace("value",temp);
    }

    private String replaceCondition(java.util.Set condition, String sql) throws Exception {
        if (condition.isEmpty()) {
            throw new Exception("condition not null");
        }
        StringBuilder conditionTemp=new StringBuilder();
        condition.forEach(k-> conditionTemp.append(setString(k)+"= ? and"));
        conditionTemp.delete(conditionTemp.length()-3,conditionTemp.length());
        sql=sql.replace("condition",conditionTemp);
        return sql;
    }

    /**
     * 合成Delete语句
     */
    public String getDelete(String tabale,java.util.Set condition) throws Exception {
        String delete = "DELETE FROM table WHERE condition";
        //替换table
        delete=replaceTable(tabale,delete);
        //替换condition
        delete=replaceCondition(condition,delete);
        return delete;
    }

    /**
     * 合成Select语句
     */
    public String getSelect(String table ,java.util.Set field) {
        String search = "SELECT field FROM table ";
        //替换table
        search=replaceTable(table,search);
        //替换field
        search=replaceField(field,search);
        return search;
    }
}
