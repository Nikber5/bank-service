package bank.app.controller;

import bank.app.model.Payment;
import bank.app.model.dto.request.PaymentRequestDto;
import bank.app.model.dto.response.PaymentIdResponseDto;
import bank.app.model.dto.response.PaymentIdStatusResponseDto;
import bank.app.service.PaymentService;
import bank.app.service.mapper.request.PaymentRequestMapper;
import bank.app.service.mapper.response.PaymentIdResponseMapper;
import bank.app.service.mapper.response.PaymentIdStatusResponseMapper;
import java.util.ArrayList;
import java.util.List;
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
    private final PaymentIdStatusResponseMapper idStatusMapper;

    public PaymentController(PaymentRequestMapper paymentRequestMapper,
                             PaymentService paymentService,
                             PaymentIdResponseMapper responseMapper,
                             PaymentIdStatusResponseMapper idStatusMapper) {
        this.paymentRequestMapper = paymentRequestMapper;
        this.paymentService = paymentService;
        this.responseMapper = responseMapper;
        this.idStatusMapper = idStatusMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentIdResponseDto createPayment(@RequestBody PaymentRequestDto dto) {
        Payment payment = paymentRequestMapper.fromDto(dto);
        paymentService.createPayment(payment);
        return responseMapper.toDto(payment);
    }

    @PostMapping("/all")
    public List<PaymentIdStatusResponseDto>
            createListOfPayments(@RequestBody List<PaymentRequestDto> dtos) {
        List<PaymentIdStatusResponseDto> responseDtos = new ArrayList<>();
        for (PaymentRequestDto dto : dtos) {
            Payment payment = paymentRequestMapper.fromDto(dto);
            PaymentIdStatusResponseDto idStatusDto = idStatusMapper
                    .toDto(paymentService.createPayment(payment));
            responseDtos.add(idStatusDto);
        }

        return responseDtos;
    }
}
