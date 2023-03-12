package driver.ride.manager.domain.model;

import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRideHistoryModel {
    private String driverName;

    private LocalDate rideDate;

    private Integer departingMiles;

    private Integer returningMiles;

    private String rideReason;

}
