package tn.hotelmanagement.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.hotelmanagement.dao.HotelDAO;
import tn.hotelmanagement.model.Hotel;
import tn.hotelmanagement.service.HotelService;

import java.io.IOException;

@WebServlet("/hotelDetails")
public class DetailsHotelController extends HttpServlet {

    private HotelService hotelService;

    @Override
    public void init() {
        hotelService = new HotelService(new HotelDAO());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hotelId = request.getParameter("id");
        if (hotelId != null) {
            try {
                Hotel hotel = hotelService.getHotelById(Long.parseLong(hotelId));
                if (hotel != null) {
                    request.setAttribute("hotel", hotel);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/hotelDetails.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Hotel not found.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid hotel ID format.");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching hotel details.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Hotel ID is required.");
        }
    }
}
