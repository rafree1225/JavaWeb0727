package com.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/servlet/stream"})
public class StreamServlet extends HttpServlet {
    
    protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        
        PrintWriter out=resp.getWriter();
        
        
        //檢視HTTP part2( The headers section：根據 HTTP 的方法所包含的標頭資訊)
        Enumeration<String> e =req.getHeaderNames();
        while(e.hasMoreElements()){
            String name= e.nextElement();
            String value= req.getHeader(name);
            out.println(name+":"+value);
        }
        
        
        out.println("<hr>");//顯示分隔線
        
        
        
        //檢視HTTP part4(An message body：存放 Request 或 Response 的內容)
        InputStream is=req.getInputStream();
        InputStreamReader isr=new InputStreamReader(is);
        char[] buffer=new char[1];
        while(isr.read(buffer)!=-1){
            String s= new String(buffer);
            out.print(s);
            //out.flush();上傳資料若寫這個會不斷傳(Servlet有資料時就會存在flush Buffer(可設定大小)，當其容量滿就會送至Client端並清空該資料，不斷重複*在JSP較常用)
        }
    }
    
    
    
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         doHandler(req, resp);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandler(req, resp);
    }
}