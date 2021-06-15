package bank.app;

import bank.app.config.AppConfig;
import bank.app.model.Account;
import bank.app.model.Client;
import bank.app.model.Payment;
import bank.app.service.AccountService;
import bank.app.service.ClientService;
import bank.app.service.PaymentService;
import java.math.BigInteger;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        Account bobAccount = new Account();
        bobAccount.setAccountType("simple");
        bobAccount.setAccountNumber(123456789L);
        bobAccount.setBalance(BigInteger.valueOf(100L));

        Account aliceAccount = new Account();
        aliceAccount.setAccountType("simple");
        aliceAccount.setAccountNumber(987654321L);
        aliceAccount.setBalance(BigInteger.valueOf(150L));

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountService accountService = context.getBean(AccountService.class);
        accountService.save(bobAccount);
        accountService.save(aliceAccount);

        Client bob = new Client();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setAccounts(List.of(bobAccount));

        Client alice = new Client();
        alice.setFirstName("Alice");
        alice.setLastName("Johnson");
        alice.setAccounts(List.of(aliceAccount));

        ClientService clientService = context.getBean(ClientService.class);
        clientService.save(bob);
        clientService.save(alice);

        System.out.println(bob);
        System.out.println(bobAccount);

        System.out.println(clientService.get(1L));
        System.out.println(clientService.get(2L));

        Payment payment = new Payment();
        payment.setSourceAccount(bobAccount);
        payment.setDestinationAccount(aliceAccount);
        payment.setAmount(BigInteger.valueOf(50L));

        PaymentService paymentService = context.getBean(PaymentService.class);
        paymentService.createPayment(payment);

        System.out.println(clientService.get(1L));
        System.out.println(clientService.get(2L));
    }
}
