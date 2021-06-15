package bank.app.service;

import java.util.Map;
import bank.app.model.Payment;

public interface PaymentService {
    Payment get(Long id);

    Payment createPayment(Payment payment);

    Payment findAll(Map<String, Long> params);
}
