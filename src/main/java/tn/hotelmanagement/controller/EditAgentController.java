package tn.hotelmanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;
import tn.hotelmanagement.service.AccountService;

import java.io.IOException;

@WebServlet("/admin/edit")
public class EditAgentController extends HttpServlet {
    private final AccountService accountService;
    public EditAgentController() {
        this.accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        Account agent = accountService.getAccountByUsername(username);
        if (agent != null) {
            request.setAttribute("agent", agent);
            try {
                request.getRequestDispatcher("/WEB-INF/views/editAgent.jsp").forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("/admin/dashboard");
        }}


}

