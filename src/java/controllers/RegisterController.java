/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.AccountDAO;
import dtos.AccountDTO;

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
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            request.setAttribute("message", "Password and Confirm Password are not matched");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        int type = 2; //regular user at beginning
        AccountDTO accountDTO = AccountDTO.builder()
                .accountId(accountId)
                .username(username)
                .password(password)
                .fullName(fullName)
                .type(type)
                .build();

        AccountDAO accountDAO = new AccountDAO();
        boolean result = accountDAO.createAccount(accountDTO);
        if (result) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("message", "Error occurred, please try again!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }


}
