package com.mrapaport.unlu.sdypp.splitter.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.mrapaport.unlu.sdypp.splitter.service.JWTProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@PropertySource("application.properties")
public class JWTProviderImpl implements JWTProvider {

    @Value("${jwt.app.secret}")
    private String APP_SECRET;

    @Override
    public String createToken(Map<String, String> payload) {
        Algorithm algorithm = Algorithm.HMAC256(APP_SECRET);
        JWTCreator.Builder jwtBuilder = JWT.create();
        payload.forEach(jwtBuilder::withClaim);
        return jwtBuilder.sign(algorithm);
    }
}