package com.bfi.ecm.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bfi.ecm.DTO.UserDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}") // 1 hour
    private long validityInMilliseconds;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto dto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return JWT.create()
                .withIssuer("your-app-name")
                .withSubject(dto.getUsername())
                .withClaim("Firstname", dto.getFirstname())
                .withClaim("Lastname", dto.getLastname())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {

            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("your-app-name")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);

            UserDto user = UserDto.builder()
                    .username(decodedJWT.getSubject())
                    .firstname(decodedJWT.getClaim("Firstname").asString())
                    .lastname(decodedJWT.getClaim("Lastname").asString())
                    .build();

            return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());

    }

}

