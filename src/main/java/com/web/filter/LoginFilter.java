package com.web.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/servlet/result")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        if (remember != null) {
            Cookie cookie = new Cookie("email", email); //建立Cookie物件
            cookie.setMaxAge(60 * 60); //Cookie存續期間
            res.addCookie(cookie); //把Cookie加到response物件
        } else {
            Cookie cookie = new Cookie("email", ""); //建立Cookie物件
            cookie.setMaxAge(60 * 60); //Cookie存續期間
            res.addCookie(cookie);
        }

        if (email.contains("admin")) {
            chain.doFilter(req, res);
        } else {
            //重導至loginform.jsp
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/form/loginform.jsp");
            if (remember != null) {
                req.setAttribute("email",email);
            }else{
                 req.setAttribute("email","");
            }
            rd.forward(req, res);
        }

    }

}
