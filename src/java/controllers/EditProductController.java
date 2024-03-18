/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.CategoryDAO;
import daos.ProductDAO;
import daos.SupplierDAO;
import dtos.ProductDTO;

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
public class EditProductController extends HttpServlet {

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
            out.println("<title>Servlet EditProductController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProductController at " + request.getContextPath() + "</h1>");
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
        CategoryDAO categoryDAO = new CategoryDAO();
        request.setAttribute("categories", categoryDAO.getAllCategories());
        SupplierDAO supplierDAO = new SupplierDAO();
        request.setAttribute("suppliers", supplierDAO.getAllSuppliers());
        ProductDAO productDAO  = new ProductDAO();
        request.setAttribute("product", productDAO.getProductById(Long.parseLong(productId)));
        request.getRequestDispatcher("edit-product.jsp").forward(request, response);
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
        CategoryDAO categoryDAO = new CategoryDAO();
        SupplierDAO supplierDAO = new SupplierDAO();

        String productId = request.getParameter("productId");
        String categoryId = request.getParameter("categoryId");
        String supplierId = request.getParameter("supplierId");
        String productName = request.getParameter("productName");
        String quantityPerUnit = request.getParameter("quantityPerUnit");
        String unitPrice = request.getParameter("unitPrice");
        String productImage = request.getParameter("productImage");

        ProductDTO productDTO = ProductDTO.builder()
                .productId(Long.parseLong(productId))
                .category(categoryDAO.getCategoryById(Long.parseLong(categoryId)))
                .supplier(supplierDAO.getSupplierById(Long.parseLong(supplierId)) )
                .productName(productName)
                .quantityPerUnit(Integer.parseInt(quantityPerUnit))
                .unitPrice(Double.parseDouble(unitPrice))
                .productImage(productImage)
                .build();

        ProductDAO productDAO = new ProductDAO();
        productDAO.updateProduct(productDTO);
        response.sendRedirect("product-management");
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
