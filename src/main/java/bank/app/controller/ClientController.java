package bank.app.controller;

import java.util.List;
import bank.app.model.Account;
import bank.app.model.Client;
import bank.app.model.dto.request.ClientRequestDto;
import bank.app.model.dto.response.ClientIdResponseDto;
import bank.app.service.AccountService;
import bank.app.service.ClientService;
import bank.app.service.mapper.request.ClientRequestMapper;
import bank.app.service.mapper.response.ClientIdMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientRequestMapper clientRequestMapper;
    private final ClientIdMapper clientIdMapper;
    private final ClientService clientService;
    private final AccountService accountService;

    public ClientController(ClientRequestMapper clientRequestMapper,
                            ClientIdMapper clientIdMapper,
                            ClientService clientService,
                            AccountService accountService) {
        this.clientRequestMapper = clientRequestMapper;
        this.clientIdMapper = clientIdMapper;
        this.clientService = clientService;
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientIdResponseDto addClient(@RequestBody ClientRequestDto requestDto) {
        Client client = clientRequestMapper.fromDto(requestDto);
        List<Account> accounts = client.getAccounts();
        for (Account account : accounts) {
            accountService.save(account);
        }
        clientService.save(client);
        return clientIdMapper.toDto(client);
    }

}
