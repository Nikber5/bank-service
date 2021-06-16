package bank.app.service.mapper.request;

import bank.app.model.dto.request.PaymentParamsRequest;
import bank.app.service.mapper.DtoRequestMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PaymentParamsRequestMapper
        implements DtoRequestMapper<PaymentParamsRequest, Map<String, Long>> {
    @Override
    public Map<String, Long> fromDto(PaymentParamsRequest dto) {
        Map<String, Long> params = new HashMap<>();
        if (dto.getDest_acc_id() != null) {
            params.put("destinationAccount", dto.getDest_acc_id());
        }
        if (dto.getSource_acc_id() != null) {
            params.put("sourceAccount", dto.getSource_acc_id());
        }
        return params;
    }
}
