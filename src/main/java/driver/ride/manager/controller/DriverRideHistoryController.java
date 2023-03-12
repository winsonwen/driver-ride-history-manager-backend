package driver.ride.manager.controller;


import driver.ride.manager.domain.entity.DriverRideHistoryEntity;
import driver.ride.manager.domain.entity.DriverRideHistoryId;
import driver.ride.manager.domain.model.DriverRideHistoryModel;
import driver.ride.manager.service.DriverRideHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driver_ride_history")
public class DriverRideHistoryController {

    DriverRideHistoryService driverRideHistoryService;

    @Autowired
    DriverRideHistoryController(DriverRideHistoryService driverRideHistoryService) {
        this.driverRideHistoryService = driverRideHistoryService;
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<DriverRideHistoryModel>> getAllDriverRideHistoryRecord(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(driverRideHistoryService.findAllDriverRideHistories(userId));
    }

    @PostMapping("/{userId}/create")
    public ResponseEntity<String> createDriverRideHistoryRecord(@PathVariable Integer userId, @RequestBody DriverRideHistoryModel driverRideHistoryModelRequest) {
        DriverRideHistoryEntity driverRideHistoryEntity = DriverRideHistoryEntity.builder().driverRideHistoryId(DriverRideHistoryId.builder()
                        .driverName(driverRideHistoryModelRequest.getDriverName())
                        .rideDate(driverRideHistoryModelRequest.getRideDate())
                        .userId(userId).build())
                .rideReason(driverRideHistoryModelRequest.getRideReason())
                .departingMiles(driverRideHistoryModelRequest.getDepartingMiles())
                .returningMiles(driverRideHistoryModelRequest.getReturningMiles())
                .build();
        Optional<String> save = driverRideHistoryService.saveDriverRideHistoryRecord(driverRideHistoryEntity);
        if (save.isPresent()) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.badRequest().body("Record Already Exist.");
        }
    }

    @PostMapping("/{userId}/delete")
    public ResponseEntity<String> deleteDriverRideHistoryRecord(@PathVariable Integer userId, @RequestBody DriverRideHistoryModel driverRideHistoryModelRequest) {
       driverRideHistoryService.deleteDriverRideHistoryRecord(DriverRideHistoryId.builder().userId(userId).rideDate(driverRideHistoryModelRequest.getRideDate()).driverName(driverRideHistoryModelRequest.getDriverName()).build());

        return ResponseEntity.ok().body("success");
        //        DriverRideHistoryEntity driverRideHistoryEntity = DriverRideHistoryEntity.builder().driverRideHistoryId(DriverRideHistoryId.builder()
//                        .driverName(driverRideHistoryModelRequest.getDriverName())
//                        .rideDate(driverRideHistoryModelRequest.getRideDate())
//                        .userId(userId).build())
//                .rideReason(driverRideHistoryModelRequest.getRideReason())
//                .departingMiles(driverRideHistoryModelRequest.getDepartingMiles())
//                .returningMiles(driverRideHistoryModelRequest.getReturningMiles())
//                .build();
//        Optional<String> save = driverRideHistoryService.saveDriverRideHistoryRecord(driverRideHistoryEntity);
//        if (save.isPresent()) {
//            return ResponseEntity.ok().body("success");
//        } else {
//            return ResponseEntity.badRequest().body("Record Already Exist.");
//        }
    }
}
