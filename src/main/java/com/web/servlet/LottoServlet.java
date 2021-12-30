package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//電腦選號Servlet
@WebServlet(name="lotto",
            urlPatterns ={"/servlet/lotto"},
            initParams = {@WebInitParam(name="n",value="6"),
                          @WebInitParam(name="range",value="49")})

/*直接簡單定義該servlet的路徑，OK，name的設定會等於urlPatterns的設定  
@WebServlet("/servlet/lotto")*/

public class LottoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //UTF-8編碼設定
       
       /*Servlet告知自己下方中英文編碼皆用UTF-8*/
       req.setCharacterEncoding("utf-8");
       resp.setCharacterEncoding("utf-8");
       
       /*告知瀏覽器內容為純文字/html，編碼為UTF-8*/
       resp.setContentType("text/html;charset=utf-8");
       
       
       
       //取得n參數
       String n=req.getParameter("n");
       int range=Integer.parseInt(getInitParameter("range"));
       if(n==null){
         n=getInitParameter("n");
       }
        
       //1~49取n組，不可重複(需用亂數，放在元素不可重複的Set中，LinkedHashSet並有按順序將元素加入並能快速查找的性質(存取順序一致))
       Set<Integer> set=new LinkedHashSet<>();
       Random r =new Random();
       while(set.size() < Integer.parseInt(n)){
         set.add(r.nextInt(range)+1);//+1是因為亂數從0開始取，+1數字才會對
       }
       
       //顯示在網頁上
       PrintWriter out=resp.getWriter();
       out.print("<html>");
       out.print("<header>");
       out.print("<title>本期樂透電腦選號</title>"); 
       out.print("</header>"); 
       out.print("<body>");
       out.print("<h1>");
       out.print("本期樂透開獎號碼:");
       out.print(set.toString());
       out.print("</h1>");
       out.print("</body>");
       out.print("</html>");
    }
    
}