package bank.app.service.mapper.response;

import bank.app.model.Payment;
import bank.app.model.dto.response.PaymentIdResponseDto;
import bank.app.service.mapper.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentIdResponseMapper implements DtoResponseMapper<PaymentIdResponseDto, Payment> {
    @Override
    public PaymentIdResponseDto toDto(Payment object) {
        PaymentIdResponseDto dto = new PaymentIdResponseDto();
        dto.setId(object.getId());
        return dto;
    }
}
