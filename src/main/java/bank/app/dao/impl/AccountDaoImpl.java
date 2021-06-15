package bank.app.dao.impl;

import bank.app.dao.AccountDao;
import bank.app.exception.DataProcessingException;
import bank.app.model.Account;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {
    protected final SessionFactory sessionFactory;

    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Account> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Account.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Can't get account by id: " + id);
        }
    }

    @Override
    public Account save(Account account) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            return account;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save account: " + account, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Account update(Account account) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(account);
            transaction.commit();
            return account;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not update account with id "
                    + account.getId() + ". ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
