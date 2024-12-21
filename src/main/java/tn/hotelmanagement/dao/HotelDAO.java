package tn.hotelmanagement.dao;

import jakarta.persistence.EntityManager;
import tn.hotelmanagement.JPAutile.JPAUtil;
import tn.hotelmanagement.model.Hotel;

import java.util.List;

public class HotelDAO {

    private EntityManager entityManager;

    public HotelDAO() {
        this.entityManager = JPAUtil.getEntityManager();
    }

    public List<Hotel> getAllHotels() {
        return entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }

    public Hotel getHotelById(long id) {
        return entityManager.find(Hotel.class, id);
    }

    public List<Hotel> getFilteredHotels(String city, Integer stars, String roomType, Integer minPrice, Integer maxPrice) {
        StringBuilder query = new StringBuilder(
                "SELECT DISTINCT h FROM Hotel h JOIN h.roomTypes rt WHERE 1=1"
        );

        if (city != null && !city.isEmpty()) {
            query.append(" AND h.city = :city");
        }
        if (stars != null && stars > 0) {
            query.append(" AND h.stars = :stars");
        }
        if (roomType != null && !roomType.isEmpty()) {
            query.append(" AND rt.label = :roomType");
        }
        if (minPrice != null && minPrice > 0) {
            query.append(" AND rt.price >= :minPrice");
        }
        if (maxPrice != null && maxPrice > 0) {
            query.append(" AND rt.price <= :maxPrice");
        }

        var typedQuery = entityManager.createQuery(query.toString(), Hotel.class);

        if (city != null && !city.isEmpty()) {
            typedQuery.setParameter("city", city);
        }
        if (stars != null && stars > 0) {
            typedQuery.setParameter("stars", stars);
        }
        if (roomType != null && !roomType.isEmpty()) {
            typedQuery.setParameter("roomType", roomType);
        }
        if (minPrice != null && minPrice > 0) {
            typedQuery.setParameter("minPrice", minPrice);
        }
        if (maxPrice != null && maxPrice > 0) {
            typedQuery.setParameter("maxPrice", maxPrice);
        }

        return typedQuery.getResultList();
    }

    public List<Hotel> findHotelsByAgentId(Long agentId) {
        return entityManager.createQuery(
                        "SELECT h FROM Hotel h WHERE h.agent.id = :agentId", Hotel.class)
                .setParameter("agentId", agentId)
                .getResultList();
    }


    public void save(Hotel hotel) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(hotel);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void update(Hotel hotel) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(hotel);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void delete(Long hotelId) {
        try {
            entityManager.getTransaction().begin();
            Hotel hotel = entityManager.find(Hotel.class, hotelId);
            if (hotel != null) {
                entityManager.remove(hotel);
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public Hotel findById(Long hotelId) {
        return entityManager.find(Hotel.class, hotelId);
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
