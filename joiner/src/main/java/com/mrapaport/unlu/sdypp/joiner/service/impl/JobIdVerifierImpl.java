package com.mrapaport.unlu.sdypp.joiner.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrapaport.unlu.sdypp.joiner.exceptions.AppSecretException;
import com.mrapaport.unlu.sdypp.joiner.exceptions.JobIdException;
import com.mrapaport.unlu.sdypp.joiner.service.JobIdVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource("application.properties")
@Service
public class JobIdVerifierImpl implements JobIdVerifier {

    @Value("${jwt.app.secret}")
    private String APP_SECRET;

    private Logger logger = LoggerFactory.getLogger(JobIdVerifierImpl.class);

    @Override
    public String verify(String rawJobId) throws JobIdException {

        try {
            Algorithm algorithm = Algorithm.HMAC256(APP_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT jwt = verifier.verify(rawJobId);

            return jwt.getPayload();

        } catch (IllegalArgumentException e) {
            logger.error("There's an error with the APP_SECRET.");
            throw new AppSecretException();
        } catch (JWTVerificationException e) {
            logger.debug(e.getMessage());
            throw new JobIdException("There's an error with the given job id.");
        }
    }
}
