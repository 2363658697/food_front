package model;

import java.util.List;
import java.util.Map;

import tools.DbUtils;

public class MyDesk {
    /**
     * 获取表中所有数据
     * @return  List<Map<String, String>>
     */
    public  List<Map<String, String>> getTableAll() {
        String sql="select * from desk order by deskid";
         List<Map<String, String>> list = null;
        try {
            list = DbUtils.query(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
