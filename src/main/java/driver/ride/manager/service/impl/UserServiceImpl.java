package driver.ride.manager.service.impl;

import driver.ride.manager.dao.UserDao;
import driver.ride.manager.domain.entity.User;
import driver.ride.manager.service.UserService;
import driver.ride.manager.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDAO;

    private SessionFactory sessionFactory;
    HibernateSession hibernateSession;

    @Autowired
    public void setUserDAO(UserDao userDAO, SessionFactory sessionFactory, HibernateSession hibernateSession) {
        this.userDAO = userDAO;
        this.sessionFactory = sessionFactory;
        this.hibernateSession = hibernateSession;
    }


    @Override
    public Optional<User> validateLogin(String username, String password) {
        Session currentSession = sessionFactory.getCurrentSession();
        return hibernateSession.synchronizeSession(() -> {
            User user = userDAO.getUserByUsername(username, currentSession);
            return username.equals(user.getUsername())&& password.equals( user.getPassword()) ? Optional.of(user): Optional.empty();

        }, currentSession);

    }

    @Override
    public Optional<User> signup(String username, String password) {
        Session currentSession = sessionFactory.getCurrentSession();
        return hibernateSession.synchronizeSession(() -> {
            User userByUsername = userDAO.getUserByUsername(username, currentSession);
            if (userByUsername != null) {
                return Optional.empty();
            }
            User user = User.builder().username(username).password(password).build();
            userDAO.save(user, currentSession);
            return Optional.ofNullable(user);

        }, currentSession);

    }
}
