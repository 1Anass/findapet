/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.findapet;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zouin
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/ResultsPage"})
public class SearchServlet extends HttpServlet implements SingleThreadModel{

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
        ServletContext context = getServletContext();
        
        PrintWriter pw = response.getWriter();
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    String driver = "org.postgresql.Driver";
    
    String userid = context.getInitParameter("userid");
    String password = context.getInitParameter("password");
    String connectionUrl = context.getInitParameter("connectionUrl");
    pw.println("<script type=\"text/javascript\">");
    pw.println("alert('"+userid+password+connectionUrl+"');");
    pw.println("</script>");
    try {
        Class.forName(driver);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try {
                    String city = request.getParameter("city");
                    String country = request.getParameter("country");
                    
                    connection = DriverManager.getConnection(connectionUrl,userid,password);
                    statement = connection.createStatement();
                    String sql = "select p.pet_name, p.age, p.gender, p.pet_type, p.size,p.coat_length, p.color, o.email, o.phone_number from offer as o inner join pet as p on o.pet_id=p.pet_id;";
                    resultSet = statement.executeQuery(sql);
                    
                    out.println("<table class=\"styled-table\" >");
                    out.println("<thead><tr><th>Index</th><th  >Pet Name</th><th  >Pet Type</th><th>Age</th><th>Gender</th></tr></thead>");

                    out.println("<tbody>");
                    while (resultSet.next()) {
                        
                        out.print("<tr>");
                   
                        out.print("<td name=\"date\">" + resultSet.getString("pet_name") + "   </td>");
                        out.print("<td name=\"start\">" + resultSet.getString("pet_type") + "  </td>");
                        out.print("<td name=''>" + String.valueOf(resultSet.getInt("age")) + "</td>");
                        out.print("<td name=\"end\">" + resultSet.getString("gender") + "   </td>");
                        
                        
                        
                        
                        out.print("</tr>");
                        
                    }
                     
                    

                    out.println("</tbody>");
                    out.println("</table>");
                    
                       
                    connection.close();
                } catch (Exception e) {
                    out.println(e);
                }
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
