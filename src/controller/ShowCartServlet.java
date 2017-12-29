package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyCart;

    
public class ShowCartServlet extends HttpServlet {
    MyCart myCart = new MyCart();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pWriter=response.getWriter();
        
        
        HttpSession session=request.getSession();
        String method=request.getParameter("method");
        if("remove".equals(method)){
            String fId=request.getParameter("fId");
            session.removeAttribute(fId);
            request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);
        }else if("order".equals(method)){
            String deskId=session.getAttribute("deskId").toString();
            int state=0;
            try {
               Integer orderId= myCart.saveOrder(deskId, state);
               Enumeration<String> kyes=session.getAttributeNames();
              while (kyes.hasMoreElements()) {
                String key = (String) kyes.nextElement();
                if (key.startsWith("cart_")) {
                    String foodId=key.split("_")[1];
                    Map<String, String> map=(Map<String, String>)session.getAttribute(key);
                    String count=map.get("count");
                    myCart.saveOrderDetail(orderId, foodId, count);
                }
            }
             session.invalidate();
             pWriter.write("<script>alert('下单成功')</script>");  
             response.setHeader("refresh", "1;url="+request.getContextPath()+"/listDesk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {          
            String foodId=request.getParameter("foodId");
            String foodName=request.getParameter("foodName");
            String price=request.getParameter("price");
/*            if (foodId != null) {
                // 通过ID作为键来获取session的值
                Object fId=session.getAttribute("cart_"+foodId);
                if(fId==null){
                    Map<String, String> map=new HashMap<>();
                    map.put("foodName", foodName);
                    map.put("price", price);
                    map.put("count", "1");
                    session.setAttribute("cart_"+foodId, map);
                }else {
                    Map<String, String> map=(Map<String, String>) fId;
                    Integer count=Integer.parseInt(map.get("count"));
                    map.put("count", String.valueOf(count+1));
                    session.setAttribute("cart_"+foodId, map);
                }
            } else {
                //重新获取ID的值
                foodId = request.getParameter("fId").toString();
                if (foodId != null) {
                    Object fId=session.getAttribute(foodId);
                    Map<String, String> map=(Map<String, String>) fId;
                    Integer count=Integer.parseInt(map.get("count"));
                    map.put("count", String.valueOf(count-1));
                    session.setAttribute("cart_"+foodId, map);
                    // 商品数量小于1，删除该商品
                    if (count < 1) {
                        session.removeAttribute(foodId);
                    }
                }
            }
            <input type="button" value="+" onclick="window.location='${pageContext.request.contextPath }/showCart?foodId=${pageScope.order.key}'"/>*/
            Object fId=session.getAttribute("cart_"+foodId);
            
            if(fId==null){
                Map<String, String> map=new HashMap<>();
                map.put("foodName", foodName);
                map.put("price", price);
                map.put("count", "1");
                session.setAttribute("cart_"+foodId, map);
            }else {
                Map<String, String> map=(Map<String, String>) fId;
                Integer count=Integer.parseInt(map.get("count"));
                map.put("count", String.valueOf(count+1));
                session.setAttribute("cart_"+foodId, map);
            }
            
            request.getRequestDispatcher("/detail/clientCart.jsp").forward(request, response);
        }
       
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

}
