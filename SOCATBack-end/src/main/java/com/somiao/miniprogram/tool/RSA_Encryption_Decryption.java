package com.somiao.miniprogram.tool;

/*
  @author 2h1c
 */
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

public class RSA_Encryption_Decryption {

    static private String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3T4/OUyvUuUlBrIieQ03DOBp/4+vvCocgtPBq9jlWNSlwlAofzdB96OCs7Xpjnm3DB3ZeoTsO1FOKkyqzqFwBHFH1GD3Bg/DvWWxHl6kk+sgGGTafXHBTIroF7GDkG6+nrZaiwZz6toeWLrBZ2615N69L3hgyKXSKtvQNE5Nmw9zN9phVO6qAPR+R3lFiAGiCxjVRr/JGqKHVyCcBxlVdpflZ9A8mYYxjT3fA9xERlDAmVz4rxos6miSwYUtwXiuhYxyz1/B7S3CK0lLkbdga79aKgkEQ4ZPyZRYgZOl4rIBLrFvFvN92Ux+lgkLq79qG+2FT/fQRdG49MbzjakVowIDAQAB";
    static private String privateKeyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC/IkHLEl0kU2WAD7BkQ2EnVZntbWSgazbUfVRp+3poQv7Fr5z4xCw46RkpSsCqOKEtYUMA+jcC4VWZpG2jSCjOS49bstfK7GAz2TefRvGNXIWaVkXZOtlYDM9C8TM3UnzgTS+O3YQSG2yCAzUySnRtFIssJ332KmKdTIiRULOwZkLV39W3QoOXFXUvkAFkKPqjR8BeoXQwAjT3vkTpMDlfM78rQ4NgwYFau/BQ/REHPfHb12vkG83wmRrekhuQXGIFQ4078jbylBnJ2DafR1+9cCfANPM2VkbI5An0Zj0sp8uUP7Z70OhkmPABKGOqNCLaRhvdEVKbY3xdPWvRUzMHAgMBAAECggEBAKZ2uYKZjdFzaLUt9Nx96oGNhjBI1Bj7B5ftR7W+Ng/UOXwGxNnMUQ+HxEg4OLCH/ag+S1LwmTTC+ACGOo647AEvwb2Fd3fegrLlfhIUgZAygpG2jcLyBBUxotmUpJyMFQJtdvwgZUQ36jsZfB0+x0OxXryn8IaSx6xj2ZVmCfmYtOdMPW/8nfRkjZCCmRUSLAotSdanlU2R82VH48VYMZyJXpuZKpiqPsKBeChWu5DyWdl2udcrrFySB2OsbRTjn4hXxThQQllb40XRGEBJeTz/P7/NrYTIcvxUIuXFEDLliOKP8G4wsLcMacwfYwNFJ1/ey3wHZuFF5NmBcBNaYjECgYEA577ANhf5sak2FqguuIn5gC8lFGNePeeJEHzvWcAJMj7/QtKqazHmjJ6mHjNlBwbu0rUxFW9sVodrOG6n4txnYKk3w7/JGxoq1TXhTSfwl6BZV+HSyq8KieTymg0fOsZvoxTenLOEIFXNfxVoCKZuFCaLqFPV5hvQCpzmdpedlGsCgYEA0yNjvWJeIim12LKrXLa4gHiYvLzwuPJRZnfC+X1UK6PfuKqM9PnPk05jSxIEtErlgNGzFmQn0kuu+8nJumWbu2/ovhVh8NypeT5FTVV+6E41J56G++QMM/4z6N3iwj6nMSvar+OL2xEYT88/cKFDK64cu0RUBBdkjoDiIdShotUCgYEAkXUSEiHkUNXjpD717FB18TYmMPDZ40MjrdytIwk8/HO8JoYx/0x36AVgQQ6DN/DrEAg4gbrJJlqjOu2BpQwwOuSbotsK8F7xSZgjDRmnkpRL37RXaW6Kz9iLSZQ46NxKj/L2cybeJKmWbVDTVrKX3z/+Yq4UUPVeFHs80G0DtHcCgYBAVIgPK+mCfzLo04diuEl+2z+uQeMWdr5Weibt3UHICiYgHdbUzUhxmXCnbtYAGNHUbhAiwmFfsa+fB9xu7oaElZRUVi+BM7pvNonnBiWkoi+P4r0+Aj21mb43NWPhyzUlxjbbYHk8RfkiWAL8TiYpKOOYKzluBbKduKctp5uYAQKBgQDgAifXsKptksZALU1PXw2RqnwJftnWNCK5EqKaLzOzjU8FwmCwSIB4lAaTjeP6XKYZcHqpKUfjy2ZCv9Zg0iA82IPYf7mW9sZ8iwWxMbczIkaDP5G6R5xEKtcLwnheMbIuNmcQrkBMRkIehXMVPp171eP4AQ+ME0XfafpQXjiYDg==";

    static private PublicKey publicKey;

    static {
        try {
            publicKey = getPublicKey(publicKeyStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static private PrivateKey privateKey;

    static {
        try {
            privateKey = getPrivateKey(privateKeyStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static PublicKey getPublicKey(String publicKeyStr) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey getPrivateKey(String privateKeyStr) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
