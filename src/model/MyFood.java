package model;

import java.util.List;
import java.util.Map;

import tools.DbUtils;

public class MyFood {
   /**
    * 根据菜系编号或菜品名来获取数据
    * @param typeId 菜系编号
    * @param foodName 菜品名
    * @return Integer
    */
    public Integer getTableCount(String typeId,String foodName) {
        String  sql="select count(rowid) ct from food where typeid like '%"+typeId+"%'";
        if(foodName!=null && foodName!=""){
            sql="select count(rowid) ct from food where foodname like '%"+foodName+"%'";
        }
        
         List<Map<String, String>> list = null;
        try {
            list = DbUtils.query(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer count = Integer.parseInt(list.get(0).get("CT").toString());
        return count;
    }


    public PageTools getTableList( Integer curPage,String foodName,String typeId) {
        if (typeId==null) {
            typeId="";
        }
        Integer totalCount =getTableCount(typeId, foodName);
        PageTools pageTools = new PageTools(curPage, 6, totalCount);
        
        String  sql="select * from (select t.*,rownum rn from food t where typeid like '%"+typeId+"%' ) where rn>=" + pageTools.getStartIndex() + " and rn<=" + pageTools.getEndIndex() + " order by foodid";
        if(foodName!=null && foodName!=""){      
            sql="select * from (select t.*,rownum rn from food t where foodname like '%"+foodName+"%' ) where rn>=" + pageTools.getStartIndex() + " and rn<=" + pageTools.getEndIndex() + " order by foodid";
        }
        
         List<Map<String, String>> list = null;
        try {
            list = DbUtils.query(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pageTools.setData(list);
        return pageTools;
    }

}
