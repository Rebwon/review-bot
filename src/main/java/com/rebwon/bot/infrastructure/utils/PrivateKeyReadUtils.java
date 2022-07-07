package com.rebwon.bot.infrastructure.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

public final class PrivateKeyReadUtils {

    private PrivateKeyReadUtils() {}

    public static PrivateKey readPrivateKey(String fileName) throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get(fileName));

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }
}
