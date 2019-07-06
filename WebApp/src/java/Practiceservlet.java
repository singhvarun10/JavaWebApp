/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author VARUN SINGH
 */
@WebServlet(urlPatterns = {"/Practiceservlet"})
public class Practiceservlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String age = (request.getParameter("age"));
        String submit = request.getParameter("submit");
        String view  = request.getParameter("view");
        if(submit!=null){
            try{
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/practice","root","root");
                PreparedStatement stmt = conn.prepareStatement("insert into details values(?,?)");
                stmt.setString(1, name);
                stmt.setString(2,age);
                stmt.executeUpdate();
                stmt.close();
                conn.close();
                response.sendRedirect("index.html");
            }catch(Exception e){
                throw new ServletException();
            }
        }
        else if(view!=null){
            try{
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/practice","root","root");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select * from details");
                while(rs.next()){
                    out.print(rs.getString("name") + " " + Integer.parseInt(rs.getString("age"))*5 + "<br>");
                }
            }catch(Exception e){
                throw new ServletException();
            }
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
        processRequest(request, response);
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
