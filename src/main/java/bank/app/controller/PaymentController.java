package bank.app.controller;

import bank.app.model.Payment;
import bank.app.model.ValidList;
import bank.app.model.dto.request.PaymentParamsRequest;
import bank.app.model.dto.request.PaymentRequestDto;
import bank.app.model.dto.response.PaymentIdResponseDto;
import bank.app.model.dto.response.PaymentIdStatusResponseDto;
import bank.app.model.dto.response.PaymentResponseDto;
import bank.app.service.PaymentService;
import bank.app.service.mapper.request.PaymentParamsRequestMapper;
import bank.app.service.mapper.request.PaymentRequestMapper;
import bank.app.service.mapper.response.PaymentIdResponseMapper;
import bank.app.service.mapper.response.PaymentIdStatusResponseMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import bank.app.service.mapper.response.PaymentResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final PaymentParamsRequestMapper paramsRequestMapper;
    private final PaymentResponseMapper paymentResponseMapper;

    public PaymentController(PaymentRequestMapper paymentRequestMapper,
                             PaymentService paymentService,
                             PaymentIdResponseMapper responseMapper,
                             PaymentIdStatusResponseMapper idStatusMapper,
                             PaymentParamsRequestMapper paramsRequestMapper,
                             PaymentResponseMapper paymentResponseMapper) {
        this.paymentRequestMapper = paymentRequestMapper;
        this.paymentService = paymentService;
        this.responseMapper = responseMapper;
        this.idStatusMapper = idStatusMapper;
        this.paramsRequestMapper = paramsRequestMapper;
        this.paymentResponseMapper = paymentResponseMapper;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentIdResponseDto createPayment(@RequestBody @Valid PaymentRequestDto dto) {
        Payment payment = paymentRequestMapper.fromDto(dto);
        paymentService.createPayment(payment);
        return responseMapper.toDto(payment);
    }

    @PostMapping(value = "/all", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentIdStatusResponseDto>
            createListOfPayments(@RequestBody @Valid ValidList<PaymentRequestDto> dtos) {
        List<PaymentIdStatusResponseDto> responseDtos = new ArrayList<>();
        for (PaymentRequestDto dto : dtos) {
            Payment payment = paymentRequestMapper.fromDto(dto);
            PaymentIdStatusResponseDto idStatusDto = idStatusMapper
                    .toDto(paymentService.createPayment(payment));
            responseDtos.add(idStatusDto);
        }

        return responseDtos;
    }

    @PostMapping(value = "/params", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PaymentResponseDto> getByParams(@RequestBody @Valid PaymentParamsRequest dto) {
        Map<String, Long> params = paramsRequestMapper.fromDto(dto);
        List<Payment> payment = paymentService.findAll(params);
        return payment.stream()
                .map(paymentResponseMapper::toDto)
                .collect(Collectors.toList());
    }
}
