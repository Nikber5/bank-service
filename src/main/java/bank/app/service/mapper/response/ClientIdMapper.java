package bank.app.service.mapper.response;

import bank.app.model.Client;
import bank.app.model.dto.response.ClientIdResponseDto;
import bank.app.service.mapper.DtoResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientIdMapper implements DtoResponseMapper<ClientIdResponseDto, Client> {
    @Override
    public ClientIdResponseDto toDto(Client object) {
        ClientIdResponseDto responseDto = new ClientIdResponseDto();
        responseDto.setClient_id(object.getId());
        return responseDto;
    }
}
