package tn.hotelmanagement.service;

import tn.hotelmanagement.dao.HotelDAO;
import tn.hotelmanagement.model.Hotel;

import java.util.List;

public class HotelService {
    private HotelDAO hotelDAO;

    public HotelService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }


    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }


    public Hotel getHotelById(long id) {
        return hotelDAO.getHotelById(id);
    }


    public List<Hotel> getFilteredHotels(String city, int stars, String roomType, int minPrice, int maxPrice) {
        try {
            return hotelDAO.getFilteredHotels(city, stars, roomType, minPrice, maxPrice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des hôtels filtrés.", e);
        }
    }

    public List<Hotel> getHotelsByAgentId(Long agentId) {
        return hotelDAO.findHotelsByAgentId(agentId);
    }

    public void createHotel(Hotel hotel) {
        hotelDAO.save(hotel);
    }

    public void updateHotel(Hotel hotel) {
        hotelDAO.update(hotel);
    }

    public void deleteHotel(Long hotelId) {
        hotelDAO.delete(hotelId);
    }

    public Hotel getHotelById(Long hotelId) {
        return hotelDAO.findById(hotelId);
    }

}
