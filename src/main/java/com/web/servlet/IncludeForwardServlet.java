package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/includeAndForward")
public class IncludeForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x=10;
        int y=20;
        
        String path="/result/add.jsp";
        RequestDispatcher rd=getServletContext().getRequestDispatcher(path);
        req.setAttribute("x", x);
        req.setAttribute("y", y);
        //rd.forward(req, resp);
       rd.include(req, resp);//通常用於報表、驗證(及格、不及格給不同頁面)；可以拆分、彈性排列組合,及做客製化頁面。
        
        PrintWriter out=resp.getWriter();
        out.print("Servlet:"+req.getAttribute("sum"));
    }
    
}

