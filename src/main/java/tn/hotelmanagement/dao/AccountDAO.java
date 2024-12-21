package tn.hotelmanagement.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.hotelmanagement.JPAutile.JPAUtil;
import tn.hotelmanagement.model.Account;

import java.util.List;
import java.util.Optional;

public class AccountDAO {

    private static final Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    public void createAccount(Account account) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        try {
            if (findByUsername(account.getUsername()).isPresent()) {
                return;
            }
            if (findByEmail(account.getEmail()).isPresent()) {
                return;
            }

            em.persist(account);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.error("Error creating account", e);
        } finally {
            em.close();
            logger.info("EntityManager closed.");
        }
    }

    public List<Account> findAccountsByRole(String role) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT a FROM Account a WHERE a.role = :role", Account.class)
                    .setParameter("role", role)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Account> findByUsername(String username) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            Account account = entityManager.createQuery(
                            "SELECT a FROM Account a WHERE a.username = :username", Account.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return Optional.of(account);
        } catch (jakarta.persistence.NoResultException e) {
            logger.warn("No account found with username: {}", username);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error finding account with username: {}", username, e);
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }

    public Optional<Account> findByEmail(String email) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            Account account = entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email", Account.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(account);
        } catch (jakarta.persistence.NoResultException e) {
            logger.warn("No account found with email: {}", email);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Error finding account with email: {}", email, e);
            return Optional.empty();
        } finally {
            entityManager.close();
        }
    }

    public void deleteAccount(String username) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Account account = entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class)
                    .setParameter("username", username)
                    .getSingleResult();
            entityManager.remove(account);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error deleting account with username: {}", username, e);
        } finally {
            entityManager.close();
        }
    }

    public List<Account> findAllAgents() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            return entityManager.createQuery("SELECT a FROM Account a WHERE a.role = 'Agent'", Account.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public String updateAccount(Account account) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = JPAUtil.getEntityManager();
            transaction = entityManager.getTransaction();

            Optional<Account> existingAccountOpt = findByUsername(account.getUsername());
            if (existingAccountOpt.isEmpty()) {
                return "Account not found";
            }

            transaction.begin();
            entityManager.merge(account);
            transaction.commit();
            return "Account updated successfully";

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            logger.error("Error updating account", e);
            return "Error updating account";
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}
