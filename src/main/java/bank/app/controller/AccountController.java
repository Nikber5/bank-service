package bank.app.controller;

import bank.app.model.Payment;
import bank.app.model.dto.response.PaymentResponseDto;
import bank.app.service.AccountService;
import bank.app.service.mapper.response.PaymentResponseMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final PaymentResponseMapper responseMapper;

    public AccountController(AccountService accountService,
                             PaymentResponseMapper responseMapper) {
        this.accountService = accountService;
        this.responseMapper = responseMapper;
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentResponseDto> getHistory(@RequestParam Long accountId) {
        List<Payment> paymentsInfo = accountService.getPaymentsInfo(accountId);
        return paymentsInfo.stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
