package com.king.sql;

import com.king.other.Parser;

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
    public String getInsect(P pojo) throws Exception {
        //将目标对象转化成map对象。
        java.util.Map map = Parser.getFields(pojo);
        String insert = "INSERT INTO table ( field ) VALUES( value )";
        insert = insert.replace("table", setString(map.get("table")));
        map.remove("table");
        java.util.Set set = map.keySet();
        StringBuilder temp=new StringBuilder();
        set.forEach(k -> temp.append(setString(k)+",") );
        temp.deleteCharAt(temp.length()-1);
        insert=insert.replace("field",temp.toString());
        return null;
    }

    private String setString(Object obj) {
        return "`" + obj.toString() + "`";
    }

    /**
     * 合成Update语句
     */
    public String getUpdate() {
        return null;
    }

    /**
     * 合成Delete语句
     */
    public String getDelete() {
        return null;
    }

    /**
     * 合成Select语句
     */
    public String getSelect() {
        return null;
    }
}
