package bank.app.service;

import bank.app.model.Client;

public interface ClientService {
    Client get(Long id);

    Client save(Client client);
}
