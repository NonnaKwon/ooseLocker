package oose.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/locker")
public class MyServlet extends HttpServlet {
    public void doGet( HttpServletRequest req,
                       HttpServletResponse res )
            throws ServletException, IOException {
        res.setContentType("text/html;charset=EUC-KR");
        PrintWriter out = res.getWriter( );
        out.println("<html><head><title>날짜</title></head>");
        out.println("<body>");
        out.println("지금은 "+new java.util.Date( )+"입니다." );
        out.println("</body></html>");
    }
}