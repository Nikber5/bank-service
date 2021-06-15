package bank.app.service.mapper.request;

import java.util.HashMap;
import java.util.Map;
import bank.app.model.dto.request.PaymentParamsRequest;
import bank.app.service.mapper.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentParamsRequestMapper
        implements DtoRequestMapper<PaymentParamsRequest, Map<String, Long>> {
    @Override
    public Map<String, Long> fromDto(PaymentParamsRequest dto) {
        Map<String, Long> params = new HashMap<>();
        if (dto.getPayer_id() != null) {
            params.put("payerId", dto.getPayer_id());
        }
        if (dto.getRecipient_id() != null) {
            params.put("recipientId", dto.getRecipient_id());
        }
        if (dto.getDest_acc_id() != null) {
            params.put("destinationAccount.id", dto.getDest_acc_id());
        }
        if (dto.getSource_acc_id() != null) {
            params.put("sourceAccount.id", dto.getSource_acc_id());
        }
        return params;
    }
}
