/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;
/**

/**
 *
 * @author Lenovo
 */
public class AdminLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session1=request.getSession();
        
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try(PrintWriter out = response.getWriter())
        {
            String Username=request.getParameter("username");
            String Password=request.getParameter("password");
System.out.println("this line my cheking======="+Username);
System.out.println("this line my cheking======="+Password);
            
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/crimenew","root","Vishnu$20");
            st=con.createStatement();
            rs=st.executeQuery("select * from admin where username='"+Username+"' and password='"+Password+"'");
            if(rs.next())
            {
                response.sendRedirect("adminpage.jsp");
                 
            }
            else
            {
                
              //  out.print("Sorry UserName or Password Error!");  
                
              
                RequestDispatcher rd=request.getRequestDispatcher("Admin1.jsp");  
        rd.include(request, response); 
         out.print("<br><br><br><h1><center>Sorry UserName or Password Error!"+"</h1>");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
          
        
       
       
           
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
