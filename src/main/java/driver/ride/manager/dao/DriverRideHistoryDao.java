package driver.ride.manager.dao;


import driver.ride.manager.domain.entity.DriverRideHistoryEntity;
import driver.ride.manager.domain.model.DriverRideHistoryModel;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DriverRideHistoryDao extends BaseHibernateDao<DriverRideHistoryEntity> {
    public List<DriverRideHistoryModel> findAllByUserId(Integer userId, Session currentSession) {
        TypedQuery<DriverRideHistoryEntity> query = currentSession.createQuery(" from DriverRideHistoryEntity where driverRideHistoryId.userId= :userId ", DriverRideHistoryEntity.class);
        query.setParameter("userId", userId);
        List<DriverRideHistoryEntity> driverRideHistories = query.getResultList();
        return driverRideHistories.stream().map(driverRideHistoryEntity -> DriverRideHistoryModel.builder()
                .driverName(driverRideHistoryEntity.getDriverRideHistoryId().getDriverName())
                .departingDate(driverRideHistoryEntity.getDriverRideHistoryId().getDepartingDate())
                .returningDate(driverRideHistoryEntity.getDriverRideHistoryId().getReturningDate())
                .rideReason(driverRideHistoryEntity.getRideReason())
                .departingMiles(driverRideHistoryEntity.getDepartingMiles())
                .returningMiles(driverRideHistoryEntity.getReturningMiles())
                .build()).collect(Collectors.toList());
    }
}
