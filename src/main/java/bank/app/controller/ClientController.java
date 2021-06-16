package bank.app.controller;

import bank.app.model.Account;
import bank.app.model.Client;
import bank.app.model.dto.request.ClientRequestDto;
import bank.app.model.dto.response.AccountResponseDto;
import bank.app.model.dto.response.ClientIdResponseDto;
import bank.app.service.AccountService;
import bank.app.service.ClientService;
import bank.app.service.mapper.request.ClientRequestMapper;
import bank.app.service.mapper.response.AccountResponseMapper;
import bank.app.service.mapper.response.ClientIdMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRequestMapper clientRequestMapper;
    private final ClientIdMapper clientIdMapper;
    private final ClientService clientService;
    private final AccountService accountService;
    private final AccountResponseMapper accountResponseMapper;

    public ClientController(ClientRequestMapper clientRequestMapper,
                            ClientIdMapper clientIdMapper,
                            ClientService clientService,
                            AccountService accountService,
                            AccountResponseMapper accountResponseMapper) {
        this.clientRequestMapper = clientRequestMapper;
        this.clientIdMapper = clientIdMapper;
        this.clientService = clientService;
        this.accountService = accountService;
        this.accountResponseMapper = accountResponseMapper;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ClientIdResponseDto addClient(@RequestBody @Valid ClientRequestDto requestDto) {
        Client client = clientRequestMapper.fromDto(requestDto);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            accountService.save(account);
        }
        clientService.save(client);
        return clientIdMapper.toDto(client);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountResponseDto> getAccountByClientId(@RequestParam Long client_id) {
        List<Account> accounts = clientService.get(client_id).getAccounts();
        List<AccountResponseDto> collect = accounts.stream()
                .map(accountResponseMapper::toDto)
                .collect(Collectors.toList());
        return collect;
    }

}
