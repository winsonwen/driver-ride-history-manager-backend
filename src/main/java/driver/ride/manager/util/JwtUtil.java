package driver.ride.manager.util;

import driver.ride.manager.domain.model.BasicDataModel;
import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.auth0.jwt.exceptions.IncorrectClaimException;
import com.auth0.jwt.algorithms.Algorithm;


public class JwtUtil {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("Test");
    private static final String ISSUER = "token123";


    public static String getToken(BasicDataModel data) {
        return JWT.create().withIssuer(ISSUER).withClaim("jwtData", JSONUtil.toJsonStr(data)).sign(ALGORITHM);

    }

    public static BasicDataModel getData(String token) {
        Gson gson = new Gson();
        DecodedJWT checkJwt = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build().verify(token);

        if (checkJwt == null) {
            return null;
        }
        return gson.fromJson(checkJwt.getClaim("jwtData").asString(), BasicDataModel.class);
    }

    public static DecodedJWT checkJwt(String token) {
        try {
            return JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build().verify(token);
        } catch (IncorrectClaimException exception) {
            System.out.println("IncorrectClaimException");
            return null;
        }
    }
}
