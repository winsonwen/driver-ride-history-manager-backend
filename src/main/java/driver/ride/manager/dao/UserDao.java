package driver.ride.manager.dao;

import driver.ride.manager.dao.BaseHibernateDao;
import driver.ride.manager.domain.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

@Repository
public class UserDao extends BaseHibernateDao<User> {

    public User getUserByUsername(String username, Session currentSession) {
        TypedQuery<User> query = currentSession.createQuery("from User where username= :username ", User.class);
        query.setParameter("username",username);
        return query.getSingleResult();
    }
}
