package bank.app.service;

import bank.app.model.Payment;

public interface PaymentService {
    Payment get(Long id);

    Payment createPayment(Payment payment);
}
