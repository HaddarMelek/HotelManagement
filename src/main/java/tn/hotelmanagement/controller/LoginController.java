package tn.hotelmanagement.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;
import tn.hotelmanagement.service.AccountService;
import tn.hotelmanagement.service.LoginService;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")

public class LoginController extends HttpServlet {
    private final LoginService loginService;
    private final AccountService accountService;

    public LoginController() {
        this.loginService = new LoginService(new AccountDAO());
        this.accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isValid = loginService.validateCredentials(username, password);

        if (isValid) {
            Account account = accountService.getAccountByUsername(username);

            if (account != null) {
                String role = accountService.getUserRole(account);
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);

                switch (role) {
                    case "Admin":

                        response.sendRedirect(request.getContextPath() + "/admin/dashboard?username=" + username);
                        break;
                    case "Agent":
                        response.sendRedirect(request.getContextPath() + "/agent/dashboard?username=" + username);
                        break;
                    case "Visitor":
                        response.sendRedirect(request.getContextPath() + "/visitor/dashboard?username=" + username);
                        break;
                    default:
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid role");
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
        }
    }
}
