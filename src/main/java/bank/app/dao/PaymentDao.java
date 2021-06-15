package bank.app.dao;

import bank.app.model.Payment;
import java.util.Optional;

public interface PaymentDao {
    Optional<Payment> get(Long id);

    Payment save(Payment payment);
}
