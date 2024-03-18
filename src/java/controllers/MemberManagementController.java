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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;                                                                          
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Quang Hieu
 */
public class MemberManagementController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        List<AccountDTO> accounts = dao.getAllAccounts();
        if(accounts.isEmpty() || accounts == null){
            request.setAttribute("message", "No account found");
        } else {
            request.setAttribute("accounts", accounts);
        }
        request.getRequestDispatcher("member-management.jsp").forward(request, response);
    }

}
