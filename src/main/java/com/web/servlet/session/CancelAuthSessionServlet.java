package com.web.servlet.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/session/cancel")
public class CancelAuthSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //建立Session物件
       HttpSession session = req.getSession(false);
       
       if(session != null){
           resp.getWriter().println("Hello " + session.getAttribute("username"));
           session.invalidate();//將Session物件直接過期
           resp.getWriter().println("Cancel OK");
       }else{
           resp.getWriter().println("Cancel Error");
       }
    }
}