package bank.app.dao.impl;

import bank.app.dao.ClientDao;
import bank.app.exception.DataProcessingException;
import bank.app.model.Client;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDao {
    protected final SessionFactory sessionFactory;

    public ClientDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Client> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Client> query = session.createQuery("from Client c "
                    + "left join fetch c.accounts "
                    + "where c.id = :id", Client.class);
            query.setParameter("id", id);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't get client by id: " + id);
        }
    }

    @Override
    public Client save(Client client) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            return client;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save client: " + client, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Client update(Client client) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            return client;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not update client with id "
                    + client.getId() + ". ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
