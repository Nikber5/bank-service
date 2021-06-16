package bank.app.dao.impl;

import bank.app.dao.PaymentDao;
import bank.app.exception.DataProcessingException;
import bank.app.model.Account;
import bank.app.model.Payment;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    @Override
    public List<Payment> findAll(Map<String, Long> params) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Payment> query = cb.createQuery(Payment.class);
            Root<Payment> root = query.from(Payment.class);
            root.fetch("sourceAccount", JoinType.LEFT);
            root.fetch("destinationAccount", JoinType.LEFT);
            Predicate resultPredicate = cb.and();

            for (Map.Entry<String, Long> entry : params.entrySet()) {
                Account account = session.get(Account.class, entry.getValue());
                Predicate equal = cb.equal(root.get(entry.getKey()), account);
                resultPredicate = cb.and(resultPredicate, equal);
            }

            query.where(resultPredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get payment from DB by params: "
                    + params, e);
        }
    }
}
