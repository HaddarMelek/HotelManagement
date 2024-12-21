package tn.hotelmanagement.controller;


import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;
import tn.hotelmanagement.service.AccountService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ManageAgentController extends HttpServlet {

    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");

        if ("edit".equals(action)) {
            Account agent = accountService.getAccountByUsername(username);
            request.setAttribute("agent", agent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/editAgent.jsp");
            dispatcher.forward(request, response);

        } else if ("delete".equals(action)) {
            if (username != null) {
                accountService.deleteAccount(username);
            }
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else {
            List<Account> agents = accountService.getAllAgents();
            request.setAttribute("agents", agents);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            String username = request.getParameter("username");
            String newUsername = request.getParameter("newUsername");
            String email = request.getParameter("email");
            String role = request.getParameter("role");

            Account agent = accountService.getAccountByUsername(username);
            if (agent != null) {
                agent.setUsername(newUsername);
                agent.setEmail(email);
                agent.setRole(role);
                accountService.updateAccount(agent);
            }

            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }
}
