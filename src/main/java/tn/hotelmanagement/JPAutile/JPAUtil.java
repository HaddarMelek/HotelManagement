package tn.hotelmanagement.JPAutile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tn.hotelmanagement.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPAUtil {
    private static final Logger logger = LoggerFactory.getLogger(JPAUtil.class);
    private static EntityManagerFactory emf;

    static {
        try {
            logger.info("Initializing EntityManagerFactory...");
            emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
            logger.info("EntityManagerFactory initialized successfully.");
        } catch (Exception e) {
            logger.error("Failed to initialize EntityManagerFactory.", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        try {
            Account account = new Account();
            account.setUsername("admin");
            account.setPassword("admin");
            account.setEmail("admin@example.com");
            account.setRole("Admin");

            em.persist(account);

            em.getTransaction().commit();
            logger.info("Account entity persisted successfully.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Transaction failed. Rolling back.", e);
        } finally {
            em.close();
            logger.info("EntityManager closed.");
        }

        JPAUtil.closeEntityManagerFactory();
    }

    public static EntityManager getEntityManager() {
        logger.info("Creating a new EntityManager...");
        return emf.createEntityManager();
    }

    public static void closeEntityManagerFactory() {
        if (emf != null) {
            logger.info("Closing EntityManagerFactory...");
            emf.close();
            logger.info("EntityManagerFactory closed successfully.");
        }
    }
}
