package bank.app.service.mapper.request;

import java.util.List;
import java.util.stream.Collectors;
import bank.app.model.Account;
import bank.app.model.Client;
import bank.app.model.dto.request.AccountRequestDto;
import bank.app.model.dto.request.ClientRequestDto;
import bank.app.service.mapper.DtoRequestMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper implements DtoRequestMapper<ClientRequestDto, Client> {
    private final AccountRequestMapper accountRequestMapper;

    public ClientRequestMapper(AccountRequestMapper accountRequestMapper) {
        this.accountRequestMapper = accountRequestMapper;
    }

    @Override
    public Client fromDto(ClientRequestDto dto) {
        Client client = new Client();
        client.setFirstName(dto.getFirst_name());
        client.setLastName(dto.getLast_name());
        List<AccountRequestDto> accountsDto = dto.getAccounts();
        List<Account> accounts = accountsDto.stream()
                .map(accountRequestMapper::fromDto)
                .collect(Collectors.toList());
        client.setAccounts(accounts);
        return client;
    }
}
