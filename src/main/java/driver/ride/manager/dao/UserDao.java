package driver.ride.manager.dao;

import driver.ride.manager.domain.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao extends BaseHibernateDao<User> {

    public Optional<User> getUserByUsernamePassword(String username, String password, Session currentSession) {
        TypedQuery<User> query = currentSession.createQuery("from User where username= :username and password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> users = query.getResultList();
        return users.size() == 1 ? Optional.of(users.get(0)) : Optional.empty();
    }
}
