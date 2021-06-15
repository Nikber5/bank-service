package bank.app.dao;

import bank.app.model.Client;
import java.util.Optional;

public interface ClientDao {
    Optional<Client> get(Long id);

    Client save(Client client);

    Client update(Client client);
}
