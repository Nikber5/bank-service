package bank.app.service;

import bank.app.model.Payment;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    Payment get(Long id);

    Payment createPayment(Payment payment);

    List<Payment> findAll(Map<String, Long> params);
}
