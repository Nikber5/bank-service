package bank.app.service.mapper.response;

import bank.app.model.Payment;
import bank.app.model.dto.response.PaymentResponseDto;
import bank.app.service.mapper.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentResponseMapper implements DtoResponseMapper<PaymentResponseDto, Payment> {

    @Override
    public PaymentResponseDto toDto(Payment object) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(object.getId());
        dto.setSourceAccountId(object.getSourceAccount().getId());
        dto.setDestinationAccountId(object.getDestinationAccount().getId());
        dto.setAmount(object.getAmount());
        dto.setStatus(object.getStatus());
        dto.setReason(object.getReason());
        return dto;
    }
}
