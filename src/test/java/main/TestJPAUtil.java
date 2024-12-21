package main;

import tn.hotelmanagement.JPAutile.JPAUtil;
import jakarta.persistence.EntityManager;

public class TestJPAUtil {
    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();
            System.out.println("EntityManager created successfully: " + em);
        } catch (Exception e) {
            System.err.println("Error while creating EntityManager: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
                System.out.println("EntityManager closed.");
            }
            JPAUtil.closeEntityManagerFactory();
        }
    }
}
