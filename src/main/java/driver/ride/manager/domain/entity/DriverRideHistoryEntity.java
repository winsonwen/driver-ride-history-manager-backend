package driver.ride.manager.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "driver_ride_history")
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRideHistoryEntity {


    @EmbeddedId
    private DriverRideHistoryId driverRideHistoryId;

    @Column(name = "departing_miles")
    private Integer departingMiles;

    @Column(name = "returning_miles")
    private Integer returningMiles;

    @Column(name = "ride_reason")
    private String rideReason;

}
