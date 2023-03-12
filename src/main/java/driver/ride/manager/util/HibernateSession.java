package driver.ride.manager.util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class HibernateSession {

    public void synchronizeSession(Runnable runnable, Session currentSession) {
        Transaction transaction = null;
        try {
            transaction = currentSession.beginTransaction();
            runnable.run();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("SynchronizeSession Failed" + runnable.toString());
        }finally {
            currentSession.close();
        }
    }

    public <T> T synchronizeSession(Supplier<T> supplier, Session currentSession) {
        Transaction transaction = null;
        try {
            transaction = currentSession.beginTransaction();
            T result = supplier.get();
            transaction.commit();
            return result;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new IllegalStateException("SynchronizeSession Failed" + supplier.toString());
        }
    }
}