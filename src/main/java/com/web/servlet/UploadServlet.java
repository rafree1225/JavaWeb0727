package com.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns = {"/servlet/upload"})

@MultipartConfig(
        fileSizeThreshold = 1024*1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*20,
        location = "C:/upload"
)

public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = resp.getWriter();
        
        // 分析 part (name=myfile1)
        req.getParts().stream()
                .filter(part -> part.getName().equals("myfile1"))
                .forEach(part -> {
                    try {
                        String fname = part.getSubmittedFileName();//part抓取上傳檔案的名稱並存到String fname中
                        part.write(fname);//並寫入fname(存入server端)
                        out.print(fname + " Upload OK !");//在網頁上顯示
                        // 圖片
                        String imagePath = "/JavaWeb0727/servlet/image?fname=" + fname;
                        out.println(String.format("<p><img width='150' src='%s'>", imagePath));

                    } catch (Exception e) {
                    }
                });
        
        // 分析 part (name=desc1)
        req.getParts().stream()
                .filter(part -> part.getName().equals("desc1"))
                .forEach(part -> {
                    try {   /*因為文字內容(String)屬於物件，需要用IOUtil，並用getInputStream()去接;
                              另外設定其編碼為UTF-8並存在String desc中；
                              不可用getParameter()，因為其只能用於application/x-www-form-urlencoded表單*/
                        String desc = IOUtils.toString(part.getInputStream(), StandardCharsets.UTF_8.name());
                        out.print(desc);
                    } catch (Exception e) {
                    }
                });
        
    }
    
}
    
