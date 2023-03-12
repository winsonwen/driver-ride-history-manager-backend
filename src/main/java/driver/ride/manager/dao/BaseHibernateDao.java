package driver.ride.manager.dao;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


@Repository
public class BaseHibernateDao<T> {
    public String save(T entity, Session currentSession) {
        return currentSession.save(entity).toString();
    }

    public void persist(T entity, Session currentSession) {
         currentSession.persist(entity);
    }
    public int delete(T entity, Session currentSession) {
        currentSession.delete(entity);
        return 0;
    }

    public void update(Object entity, Session currentSession) {
        currentSession.merge(entity);
    }

    public T findById(Serializable id, Session currentSession) {
        return currentSession.get(getTClass(), id);
    }

    public List<T> findAll(Session currentSession) {
        return currentSession.createQuery("from " + getTClass().getSimpleName(),getTClass()).list();
    }

    private Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}



