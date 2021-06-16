package bank.app.service;

import bank.app.model.Account;
import bank.app.model.Payment;
import java.util.List;

public interface AccountService {
    Account get(Long id);

    Account save(Account account);

    Account update(Account account);

    List<Payment> getPaymentsInfo(Long sourceAccId);
}
