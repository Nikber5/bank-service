package bank.app.service.mapper.response;

import bank.app.model.Payment;
import bank.app.model.dto.response.PaymentIdStatusResponseDto;
import bank.app.service.mapper.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentIdStatusResponseMapper
        implements DtoResponseMapper<PaymentIdStatusResponseDto, Payment> {
    @Override
    public PaymentIdStatusResponseDto toDto(Payment object) {
        PaymentIdStatusResponseDto dto = new PaymentIdStatusResponseDto();
        dto.setId(object.getId());
        dto.setStatus(object.getStatus());
        return dto;
    }
}
