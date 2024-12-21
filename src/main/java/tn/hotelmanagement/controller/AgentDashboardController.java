package tn.hotelmanagement.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.dao.HotelDAO;
import tn.hotelmanagement.model.Account;
import tn.hotelmanagement.model.Hotel;
import tn.hotelmanagement.service.AccountService;
import tn.hotelmanagement.service.HotelService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "AgentDashboardController", value = "/agent/dashboard")
public class AgentDashboardController extends HttpServlet {
    private AccountService accountService;
    private HotelService hotelService;

    @Override
    public void init() throws ServletException {
        this.accountService = new AccountService(new AccountDAO());
        this.hotelService = new HotelService(new HotelDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        if (username != null) {
            Account account = accountService.getAccountByUsername(username);

            if (account != null) {
                request.setAttribute("account", account);

                List<Hotel> hotels = hotelService.getHotelsByAgentId(account.getId());
                request.setAttribute("hotels", hotels);

                request.getRequestDispatcher("/WEB-INF/views/agent/agentDashboard.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Agent not found");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
        }
    }


}
