package driver.ride.manager.service.impl;

import driver.ride.manager.dao.DriverRideHistoryDao;
import driver.ride.manager.domain.entity.DriverRideHistoryEntity;
import driver.ride.manager.domain.entity.DriverRideHistoryId;
import driver.ride.manager.domain.model.DriverRideHistoryModel;
import driver.ride.manager.service.DriverRideHistoryService;
import driver.ride.manager.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverRideHistoryServiceImpl implements DriverRideHistoryService {

    DriverRideHistoryDao driverRideHistoryDao;
    SessionFactory sessionFactory;

    HibernateSession hibernateSession;


    DriverRideHistoryServiceImpl(DriverRideHistoryDao driverRideHistoryDaoImpl,
                                 SessionFactory sessionFactory, HibernateSession hibernateSession) {

        this.driverRideHistoryDao = driverRideHistoryDaoImpl;
        this.sessionFactory = sessionFactory;
        this.hibernateSession = hibernateSession;
    }

    @Override
    public Optional<String> saveDriverRideHistoryRecord(DriverRideHistoryEntity driverRideHistoryEntity) {
        Session currentSession = sessionFactory.getCurrentSession();
        return hibernateSession.synchronizeSession(() -> {
            DriverRideHistoryEntity findById = driverRideHistoryDao.findById(driverRideHistoryEntity.getDriverRideHistoryId(), currentSession);
            if (findById != null) {
                return Optional.empty();
            }
            return Optional.ofNullable(driverRideHistoryDao.save(driverRideHistoryEntity, currentSession));
        }, currentSession);
    }

    @Override
    public List<DriverRideHistoryModel> findAllDriverRideHistories(Integer userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return hibernateSession.synchronizeSession(() -> driverRideHistoryDao.findAllByUserId(userId, currentSession), currentSession);
    }

    @Override
    public void deleteDriverRideHistoryRecord(DriverRideHistoryId driverRideHistoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        hibernateSession.synchronizeSession(() -> driverRideHistoryDao.delete(DriverRideHistoryEntity.builder().driverRideHistoryId(driverRideHistoryId).build(), currentSession),currentSession);
    }

}
