package model;

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.batch.Main;

import tools.DbUtils;

public class MyFoodType {
        
    /**
     * ��ȡ���еĲ�ϵ��Ϣ
     * @return  List<Map<String, String>>
     * @throws Exception
     */
    public  List<Map<String, String>> getAllType() throws Exception{
        String sql = "select * from foodtype order by typeid";
         List<Map<String, String>> list = DbUtils.query(sql);
        return list;
    }
    
}
