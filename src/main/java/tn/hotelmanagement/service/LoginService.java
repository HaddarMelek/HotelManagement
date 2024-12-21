package tn.hotelmanagement.service;

import tn.hotelmanagement.dao.AccountDAO;
import tn.hotelmanagement.model.Account;

import java.util.Optional;

public class LoginService {

    private final AccountDAO accountDAO;

    public LoginService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public boolean validateCredentials(String username, String rawPassword) {
        Optional<Account> optionalAccount = accountDAO.findByUsername(username);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            return account.getPassword().equals(rawPassword);
        }
        return false;
    }
}