package driver.ride.manager.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtModel {
    BasicDataModel basicDataModel;

    String jwt;
}
