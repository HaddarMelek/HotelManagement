

package tn.hotelmanagement.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;
import tn.hotelmanagement.service.AccountService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "AdminController", value = "/admin/create")
public class AdminController extends HttpServlet {
    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        this.accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");  // New field for password
        String role = "Agent";

        if (accountService.isUsernameTaken(username)) {
            request.setAttribute("error", "Username is already taken.");
            request.getRequestDispatcher("/WEB-INF/views/admin/create.jsp").forward(request, response);
            return;
        }

        if (accountService.isEmailTaken(email)) {
            request.setAttribute("error", "Email is already taken.");
            request.getRequestDispatcher("/WEB-INF/views/admin/create.jsp").forward(request, response);
            return;
        }

        Account agent = new Account();
        UUID uuid = UUID.randomUUID();
        long uniqueId = uuid.getMostSignificantBits();
        agent.setId(uniqueId);
        agent.setUsername(username);
        agent.setEmail(email);
        agent.setPassword(password);
        agent.setRole(role);

        accountService.createAccount(agent);

        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }
}