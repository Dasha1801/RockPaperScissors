package com.test;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HMAC {
    public static String[] Hmac(String move)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(move.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        String hex = String.format("%064x", new BigInteger(1, digest));
        SecureRandom random = new SecureRandom();
        byte[] seed = random.generateSeed(16);
        StringBuilder builder = new StringBuilder();
        for(byte b1 : seed) {
            builder.append(String.format("%02X", b1));
        }
        String key = (builder.toString());
        return new String[] {hex, key};
    }

}