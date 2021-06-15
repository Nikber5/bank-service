package bank.app.service.impl;

import bank.app.dao.PaymentDao;
import bank.app.model.Account;
import bank.app.model.Payment;
import bank.app.service.AccountService;
import bank.app.service.PaymentService;
import java.math.BigInteger;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentDao paymentDao;
    private final AccountService accountService;

    public PaymentServiceImpl(PaymentDao paymentDao, AccountService accountService) {
        this.paymentDao = paymentDao;
        this.accountService = accountService;
    }

    @Override
    public Payment get(Long id) {
        return paymentDao.get(id).get();
    }

    @Override
    public Payment createPayment(Payment payment) {
        Account sourceAccount = accountService.get(payment.getSourceAccount().getId());
        Account destinationAccount = accountService.get(payment.getDestinationAccount().getId());
        BigInteger sourceBalance = sourceAccount.getBalance();
        BigInteger destinationBalance = destinationAccount.getBalance();

        sourceBalance = sourceBalance.subtract(payment.getAmount());
        destinationBalance = destinationBalance.add(payment.getAmount());
        if (sourceBalance.compareTo(BigInteger.ZERO) < 0
                || destinationBalance.compareTo(BigInteger.ZERO) < 0) {
            payment.setStatus("error");
            paymentDao.save(payment);
            throw new RuntimeException("Balance can't be less than zero");
        }
        sourceAccount.setBalance(sourceBalance);
        destinationAccount.setBalance(destinationBalance);
        accountService.update(sourceAccount);
        accountService.update(destinationAccount);
        payment.setStatus("ok");
        return paymentDao.save(payment);
    }
}
