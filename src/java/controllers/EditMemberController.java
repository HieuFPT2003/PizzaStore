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
public class EditMemberController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountId = request.getParameter("accountId");
        AccountDAO accountDAO = new AccountDAO();
        AccountDTO account = accountDAO.getAccountById(accountId);

        request.setAttribute("account", account);

        request.getRequestDispatcher("edit-member.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountId = request.getParameter("accountId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        int type = Integer.parseInt(request.getParameter("type"));

        AccountDTO account = AccountDTO.builder()
                .accountId(accountId)
                .username(username)
                .password(password)
                .fullName(fullName)
                .type(type)
                .build();

        AccountDAO accountDAO = new AccountDAO();
        accountDAO.updateAccount(account);

        AccountDTO loggedInAccount = (AccountDTO) request.getSession().getAttribute("account");
        if (loggedInAccount.getAccountId().equals(accountId)) {
            request.getSession().setAttribute("account", account);
        }

        response.sendRedirect("./member-management");
    }

}
