package driver.ride.manager.service;

import driver.ride.manager.domain.entity.DriverRideHistoryEntity;
import driver.ride.manager.domain.entity.DriverRideHistoryId;
import driver.ride.manager.domain.model.DriverRideHistoryModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DriverRideHistoryService {

    Optional<String> saveDriverRideHistoryRecord(DriverRideHistoryEntity driverRideHistoryEntity);

    List<DriverRideHistoryModel> findAllDriverRideHistories(Integer userId);

    void deleteDriverRideHistoryRecord(DriverRideHistoryId build);
}
