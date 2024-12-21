package tn.hotelmanagement.service;

import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;

import java.util.List;

public class AccountService {

    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void createAccount(Account account) {
        accountDAO.createAccount(account);
    }
    public List<Account> getAccountsByRole(String role) {
        return accountDAO.findAccountsByRole(role);
    }

    public Account getAccountByUsername(String username) {
        return accountDAO.findByUsername(username).orElse(null);
    }

    public boolean isUsernameTaken(String username) {
        return accountDAO.findByUsername(username).isPresent();
    }

    public boolean isEmailTaken(String email) {
        return accountDAO.findByEmail(email).isPresent();
    }
    public String getUserRole(Account account) {
        return (account != null) ? account.getRole() : null;
    }
    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }


    public void deleteAccount(String username) {
        accountDAO.deleteAccount(username);
    }

    public List<Account> getAllAgents() {
        return accountDAO.findAllAgents();
    }
}
