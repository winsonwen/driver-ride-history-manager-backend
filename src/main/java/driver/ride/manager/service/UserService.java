package driver.ride.manager.service;

import driver.ride.manager.domain.entity.User;
import driver.ride.manager.domain.model.JwtModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {


    Optional<User> validateLogin(String username, String password);

    Optional<User> signup(String username, String password);
}
