/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.*;
import dtos.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang Hieu
 */
public class OrderController extends HttpServlet {

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
            out.println("<title>Servlet OrderController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
        String productId = request.getParameter("productId");
        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = productDAO.getProductById(Long.parseLong(productId));
        request.setAttribute("product", product);
        request.getRequestDispatcher("orderpage.jsp").forward(request, response);
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
        String productId = request.getParameter("productId");
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        ProductDTO product = productDAO.getProductById(Long.parseLong(productId));
        String quantity = request.getParameter("quantity");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String contactName = request.getParameter("contactName");

        AccountDTO loggedInAccount = (AccountDTO) request.getSession().getAttribute("account");
        CustomerDTO customer = CustomerDTO.builder()
                                .contactName(contactName)
                                .address(address)
                                .phone(phone)
                                .password(loggedInAccount.getPassword())
                                .build();
        customerDAO.addCustomer(customer);
        customer = customerDAO.findOneByPhone(phone);
        loggedInAccount.setCustomerId(customer.getCustomerId());
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.updateAccount(loggedInAccount);
        OrderDTO order = OrderDTO.builder()
                .customer(customer)
                .freight(300.00)
                .shipAddress(address)
                .build();
        orderDAO.addOrder(order);
        order = orderDAO.getTheLastOrder();
        OrderDetailsDTO orderDetails = OrderDetailsDTO.builder()
                .order(order)
                .product(product)
                .unitPrice(product.getUnitPrice())
                .quantity(Integer.parseInt(quantity))
                .build();

        orderDetailsDAO.addOrderDetails(orderDetails);
        request.setAttribute("messageOrder", "Order successfully!");
        response.sendRedirect("./home");

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
