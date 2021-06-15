package bank.app.service.impl;

import bank.app.dao.ClientDao;
import bank.app.model.Client;
import bank.app.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client get(Long id) {
        return clientDao.get(id).get();
    }

    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }
}
