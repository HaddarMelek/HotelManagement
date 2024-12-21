package tn.hotelmanagement.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
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
import java.util.UUID;

@WebServlet(name = "HotelCreateController", value = "/agent/hotel/create")
@MultipartConfig
public class HotelCreateController extends HttpServlet {
    private HotelService hotelService;
    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        this.hotelService = new HotelService(new HotelDAO());
        this.accountService = new AccountService(new AccountDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/agent/agentDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        String description = request.getParameter("description");
        String starsParam = request.getParameter("stars");
        if (username == null || username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is required");
            return;
        }

        Account account = accountService.getAccountByUsername(username);
        if (name == null || name.isEmpty() || city == null || city.isEmpty() || description == null || description.isEmpty() || starsParam == null || starsParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
            return;
        }

        int stars;
        try {
            stars = Integer.parseInt(starsParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Stars must be a valid integer");
            return;
        }

        Part imagePart = request.getPart("image");
        String imagePath = null;

        if (imagePart != null && imagePart.getSize() > 0) {
            String fileName = UUID.randomUUID().toString() + "_" + getFileName(imagePart);
            String uploadDir="/Users/macbook/programmationParalelle/ExamenTP/HotelManagementJEE/src/main/webapp/uploads"+getFileName(imagePart);

            if (uploadDir == null) {
                uploadDir = "/Uploads";
                System.out.println("Using fallback upload directory: " + uploadDir);
            }
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdir();
            }

            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(imagePart.getInputStream(), filePath);
            imagePath = "/uploads/" + fileName;
        }


        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setCity(city);
        hotel.setStars(stars);
        hotel.setDescription(description);
        hotel.setImage(imagePath);
        hotel.setAgent(account);

        hotelService.createHotel(hotel);

        response.sendRedirect(request.getContextPath() + "/agent/dashboard?username=" + username);
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("Content-Disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf("=") + 2, cd.length() - 1);
            }
        }
        return null;
    }
}
