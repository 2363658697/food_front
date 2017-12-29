package model;

import java.util.List;
import java.util.Map;

import tools.DbUtils;

public class MyCart {
            /**
             * 获取订单的编号
             * @return Integer
             * @throws Exception
             */
           public Integer getOrderId() throws Exception{
               String sql="select nvl(max(orderid),0)+1 oid from foodorder";
               List<Map<String, String>> list=DbUtils.query(sql);
               return Integer.parseInt(list.get(0).get("OID"));
           }
           
           
           public Integer saveOrder(String deskId,int orderState) throws Exception{
               Integer orderId=getOrderId();
               String sql="insert into foodorder values("+orderId+", "+deskId+" , sysdate , "+orderState+")";
               DbUtils.excute(sql);
               return orderId;
           }
           
           public void saveOrderDetail(Integer orderId,String foodId,String count) throws Exception{ 
               String sql="insert into foodorderdetail values((select nvl(max(detailid),0)+1  from foodorderdetail) , "+orderId+" , "+foodId+" , "+count+")";   
               DbUtils.excute(sql);
           }
           
}
