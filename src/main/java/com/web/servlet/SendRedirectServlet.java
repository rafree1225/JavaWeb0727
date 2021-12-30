
package com.web.servlet;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/sendredirect")
public class SendRedirectServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      
         int n=new Random().nextInt(100);//Random()返回隨機數串流，nextInt()為其函式，返回在指定數值範圍內下一個int的隨機數
            
         if(n%2==0){
            resp.sendRedirect("https://tw.yahoo.com/");//因使用sendRedirect()，由SendRedirectServlet執行
         }else{
            getServletContext().getRequestDispatcher("/index.html").forward(req, resp);
         }//如果隨機數是偶數,導到Yahoo(sendredirect會放在yahoo的Header裡)，如果不是,導到內部由forward(),透過add.jsp執行(forward不會改路徑,因為可能導好幾次,使用者不需要知道路徑,sendredirect則會;內部重導可以帶參數，控制性較高;例如介接可以用sendredirect)
    }
    
    
    
}
