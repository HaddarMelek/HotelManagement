package tn.hotelmanagement.hotelmanagementjee;

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
import java.util.List;


@WebServlet(name = "HotelController", value = {"/hotels", "/"})
public class HotelController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HotelDAO hotelDAO = new HotelDAO();

        try {
            List<Hotel> hotels = hotelDAO.getAllHotels();
            request.setAttribute("hotels", hotels);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching hotels.");
        } finally {
            hotelDAO.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        int stars;
        int minPrice;
        int maxPrice;
        if (request.getParameter("stars").isEmpty()){
            stars = 0;
        }
        else{
             stars = Integer.parseInt(request.getParameter("stars"));
        }
        String roomType = request.getParameter("roomType");
        if (request.getParameter("minPrice").isEmpty()){
            minPrice = 0;
        }
        else{
            minPrice = Integer.parseInt(request.getParameter("minPrice"));
        }
        if (request.getParameter("maxPrice").isEmpty()){
            maxPrice = 0;
        }
        else{
            maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
        }

        HotelService hotelService = new HotelService(new HotelDAO());

        try {
            List<Hotel> hotels = hotelService.getFilteredHotels(city, stars, roomType, minPrice, maxPrice);
            request.setAttribute("hotels", hotels);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while filtering hotels.");
        }
    }
}
