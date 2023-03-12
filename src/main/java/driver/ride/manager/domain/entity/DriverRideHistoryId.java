package driver.ride.manager.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverRideHistoryId implements Serializable {
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "ride_date")
    private LocalDate rideDate;

}
