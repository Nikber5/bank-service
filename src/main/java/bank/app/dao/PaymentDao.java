package bank.app.dao;

import bank.app.model.Payment;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentDao {
    Optional<Payment> get(Long id);

    Payment save(Payment payment);

    List<Payment> findAll(Map<String, Long> params);
}
