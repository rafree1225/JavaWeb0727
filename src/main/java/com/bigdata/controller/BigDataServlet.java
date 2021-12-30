package com.bigdata.controller;

import com.bigdata.model.Sales;
import com.bigdata.model.SalesModel;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/bigdata")
public class BigDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        
        // 設定 request attribute(若無country，將country丟入，並貼入 req.setAttribute的"saleses")
        if(country == null  ||  country.length() == 0)
            req.setAttribute("saleses", SalesModel.getSaleses());
        else
            req.setAttribute("saleses", SalesModel.getSaleses(country));
        //並將結果丟給bigdataform.jsp
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/form/bigdataform.jsp");
        rd.forward(req, resp);
    }
    
}
    
    
    

