package bank.app.dao;

import bank.app.model.Account;
import java.util.Optional;

public interface AccountDao {
    Optional<Account> get(Long id);

    Account save(Account account);

    Account update(Account account);
}
