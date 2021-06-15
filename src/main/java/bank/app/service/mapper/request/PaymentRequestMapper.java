package bank.app.service.mapper.request;

import bank.app.model.Payment;
import bank.app.model.dto.request.PaymentRequestDto;
import bank.app.service.AccountService;
import bank.app.service.mapper.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestMapper implements DtoRequestMapper<PaymentRequestDto, Payment> {
    private final AccountService accountService;

    public PaymentRequestMapper(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Payment fromDto(PaymentRequestDto dto) {
        Payment payment = new Payment();
        payment.setSourceAccount(accountService.get(dto.getSource_acc_id()));
        payment.setDestinationAccount(accountService.get(dto.getDest_acc_id()));
        payment.setAmount(dto.getAmount());
        payment.setReason(dto.getReason());
        return payment;
    }
}
