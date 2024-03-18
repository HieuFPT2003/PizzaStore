/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.ProductDAO;
import dtos.ProductDTO;

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
public class HomepageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        String search = request.getParameter("search");
        if(search != null && !search.isEmpty()){
            List<ProductDTO> pizzas = productDAO.searchProductByIdOrNameOrUnitPrice(search);
            if(pizzas == null || pizzas.isEmpty()){
                request.setAttribute("message", "No product found");
            } else {
                request.setAttribute("pizzas", pizzas);
            }
            request.getRequestDispatcher("homepage.jsp").forward(request, response);
            return;
        }
        List<ProductDTO> pizzas = productDAO.getAllProducts();
        if(pizzas == null || pizzas.isEmpty()){
            request.setAttribute("message", "No product found");
        } else {
            request.setAttribute("pizzas", pizzas);
        }

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }

}
