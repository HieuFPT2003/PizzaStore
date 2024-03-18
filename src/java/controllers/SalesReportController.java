/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.OrderDAO;
import dtos.SalesReportDTO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang Hieu
 */
public class SalesReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("sales-report.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String message = " ";

        if(fromDate.isEmpty() || toDate.isEmpty()){
            message = "Please fill in all fields";
            request.setAttribute("message", message);
            request.getRequestDispatcher("sales-report.jsp").forward(request, response);
            return;
        }

        OrderDAO orderDAO = new OrderDAO();
        List<SalesReportDTO> salesReport = orderDAO.getSalesReportByPeriod(fromDate, toDate);
        if(salesReport.isEmpty() || salesReport == null){
            message = "No sales report found";
            request.setAttribute("message", message);
        } else {
            request.setAttribute("salesReport", salesReport);
        }
        request.getRequestDispatcher("sales-report.jsp").forward(request, response);
    }

}
