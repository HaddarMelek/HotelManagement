package tn.hotelmanagement.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;
import tn.hotelmanagement.service.AccountService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminDashboardController", value = "/admin/dashboard")


public class AdminDashboardController extends HttpServlet {
    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        this.accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Account> agents = accountService.getAccountsByRole("Agent");
        request.setAttribute("agents", agents);

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }
}
