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

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    private final AccountService accountService;

    public SignUpController() {
        this.accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (accountService.isUsernameTaken(username)) {
            request.setAttribute("error", "Username already taken");
            request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
            return;
        }

        if (accountService.isEmailTaken(email)) {
            request.setAttribute("error", "Email already taken");
            request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
            return;
        }

        Account newAccount = new Account();
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setEmail(email);
        newAccount.setRole("Visitor");

        try {
            accountService.createAccount(newAccount);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while creating the account");
            request.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(request, response);
        }
    }
}
