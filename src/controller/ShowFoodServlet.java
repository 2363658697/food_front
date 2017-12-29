package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyFood;
import model.PageTools;

public class ShowFoodServlet extends HttpServlet {
    MyFood myFood = new MyFood();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String typeId=request.getParameter("typeId");
        String foodName=request.getParameter("foodName");
        String deskId=request.getParameter("deskId");
        HttpSession session=request.getSession();
        if (deskId!=null) {
            session.setAttribute("deskId", deskId);
        }
        Integer curPage = 1;
        if (request.getParameter("curPage") != null) {
            curPage = Integer.parseInt(request.getParameter("curPage"));
        }

        PageTools pageTools = myFood.getTableList(curPage, foodName, typeId);
        request.setAttribute("list", pageTools);
        request.getRequestDispatcher("/detail/caidan.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

}
