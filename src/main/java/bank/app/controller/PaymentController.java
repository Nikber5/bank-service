package bank.app.controller;

import bank.app.model.Payment;
import bank.app.model.dto.request.PaymentRequestDto;
import bank.app.model.dto.response.PaymentIdResponseDto;
import bank.app.service.PaymentService;
import bank.app.service.mapper.request.PaymentRequestMapper;
import bank.app.service.mapper.response.PaymentIdResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentRequestMapper paymentRequestMapper;
    private final PaymentService paymentService;
    private final PaymentIdResponseMapper responseMapper;

    public PaymentController(PaymentRequestMapper paymentRequestMapper,
                             PaymentService paymentService,
                             PaymentIdResponseMapper responseMapper) {
        this.paymentRequestMapper = paymentRequestMapper;
        this.paymentService = paymentService;
        this.responseMapper = responseMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentIdResponseDto createPayment(@RequestBody PaymentRequestDto dto) {
        Payment payment = paymentRequestMapper.fromDto(dto);
        paymentService.createPayment(payment);
        return responseMapper.toDto(payment);
    }

}
