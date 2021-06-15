package bank.app.service.impl;

import bank.app.dao.AccountDao;
import bank.app.model.Account;
import bank.app.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account get(Long id) {
        return accountDao.get(id).get();
    }

    @Override
    public Account save(Account account) {
        return accountDao.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountDao.update(account);
    }
}
