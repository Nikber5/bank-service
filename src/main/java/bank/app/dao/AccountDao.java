package bank.app.dao;

import bank.app.model.Account;
import bank.app.model.Payment;
import java.util.List;
import java.util.Optional;

public interface AccountDao {
    Optional<Account> get(Long id);

    Account save(Account account);

    Account update(Account account);

    List<Payment> getPaymentsInfo(Long sourceAccId);
}
