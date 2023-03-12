package driver.ride.manager.controller;

import driver.ride.manager.domain.entity.User;
import driver.ride.manager.domain.model.BasicDataModel;
import driver.ride.manager.domain.model.JwtModel;
import driver.ride.manager.domain.request.LoginRequest;
import driver.ride.manager.domain.request.SignupRequest;
import driver.ride.manager.service.UserService;
import driver.ride.manager.util.JwtUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    UserService userService;

    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login( @RequestBody LoginRequest loginRequest) {
        Optional<User> possibleUser = userService.validateLogin(loginRequest.getUsername(), loginRequest.getPassword());
        if (possibleUser.isPresent()) {
            User user = possibleUser.get();
            BasicDataModel basicDataModel = BasicDataModel.builder().username(user.getUsername()).userId(user.getId()).build();
            String token = JwtUtil.getToken(basicDataModel);
            JwtModel jwtModel = JwtModel.builder().jwt(token).basicDataModel(basicDataModel).build();
            return ResponseEntity.ok(jwtModel);
        }
        return ResponseEntity.badRequest().body("User name or password incorrect!");
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest signupRequest) {
        try {
            if (Strings.isNotEmpty(signupRequest.getUsername())&&Strings.isNotEmpty(signupRequest.getPassword())) {
                Optional<User> signup = userService.signup(signupRequest.getUsername(), signupRequest.getPassword());
                if (signup.isPresent()) {
                    return ResponseEntity.ok().body("success");
                } else {
                    return ResponseEntity.badRequest().body("Signup Already!");

                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e);
        }

        return ResponseEntity.badRequest().body("Signup Failed");
    }
}
