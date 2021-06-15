package bank.app.service;

import bank.app.model.Account;

public interface AccountService {
    Account get(Long id);

    Account save(Account account);

    Account update(Account account);
}
