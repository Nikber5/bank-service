package bank.app.dao.impl;

import bank.app.dao.PaymentDao;
import bank.app.exception.DataProcessingException;
import bank.app.model.Payment;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDaoImpl implements PaymentDao {
    protected final SessionFactory sessionFactory;

    public PaymentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Payment> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Payment.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Can't get payment by id: " + id);
        }
    }

    @Override
    public Payment save(Payment payment) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(payment);
            transaction.commit();
            return payment;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save payment: " + payment, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
