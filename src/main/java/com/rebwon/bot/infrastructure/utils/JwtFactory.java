package com.rebwon.bot.infrastructure.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;

public final class JwtFactory {

    public String createJWT(String gitHubAppId, long timeMillis) throws Exception {
        SignatureAlgorithm algorithm = SignatureAlgorithm.RS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Key signKey = PrivateKeyReadUtils.readPrivateKey("src/main/resources/private-key.der");

        JwtBuilder builder = Jwts.builder()
            .setIssuedAt(now)
            .setIssuer(gitHubAppId)
            .signWith(signKey, algorithm);

        if (timeMillis > 0) {
            long expMillis = nowMillis + timeMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }
}
