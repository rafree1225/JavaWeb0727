package com.web.servlet.session;

import com.github.javafaker.Faker;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/session/getauth")
public class GetAuthSessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //建立Session物件(預設Session時間為30分鐘)
      HttpSession session = req.getSession(true);
      //session.setMaxInactiveInterval(5);5秒後session逾期
      resp.getWriter().println(session.getId()); //授權的ID
      
       //隨機產生名字變數
      Faker faker=new Faker();
      String username=faker.name().firstName();
      
      //將username存到session變數內再取得
      session.setAttribute("username", username);
      resp.getWriter().println("Hello " + session.getAttribute("username"));
    
    }
}