package com.somiao.miniprogram.controller;


import com.somiao.miniprogram.entity.User;
import com.somiao.miniprogram.tool.impl.EncryptionImpl;
import com.somiao.miniprogram.tool.impl.SearchCatImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 测试
 *
 * @author yc
 */
@RestController
public class testController {

    private SearchCatImpl searchCat;

    private EncryptionImpl encryption;


    //这个用来测试数据库是否正常连接
    @GetMapping("test")
    public ResponseEntity<String> test1(){

        System.out.println("11111111111111111111111111111111111");

        return new ResponseEntity<>(this.searchCat.searchInfo("小老鼠"), HttpStatus.OK);
    }

    //这个测试添加用户并且对密码加密
    @GetMapping("test2")
    public int encryption(){

        String account = "1008611";
        String password = "qq110.cn";
        User user = new User(account,password);

        this.encryption.addUser(user);
        return 1;
    }

    //这个用于验证用户与密码是否正确
    @GetMapping("test3")
    public String verify(){
        String account = "1008611";
        String password = "qq110.cn";
        if(this.encryption.contrast(account,password))
        {
            return "登录成功";
        }
        else{
            return "密码或用户名错误";
        }

    }

    //这个用于验证用户与密码是否正确
    @PostMapping("test4")
    public String testAPI(
            @RequestParam("account") String account,
            @RequestParam("password") String password)
    {
        System.out.println(2222222);
        if(this.encryption.contrast(account,password))
        {
            return "登录成功";
        }
        else{
            return "密码或用户名错误";
        }
    }

    @PostMapping("test5")
    public String ttt(@RequestParam("account") String account) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        String plaintext = account;
        byte[] ciphertextBytes = encrypt(plaintext);
        String ciphertext = Base64.getEncoder().encodeToString(ciphertextBytes);
        String decryptedText = decrypt(ciphertext);

        // 输出结果
        System.out.println("明文: " + plaintext);
        System.out.println("密文: " + new String(ciphertext));
        System.out.println("解密后: " + decryptedText);

        return "123456";
    }
    /**
     * 查到并返回信息
     *
     * @param catName 猫的名字
     *
     * @return 找到则返回json类型的字符串，未找到则返回null
     */
    //@PostMapping("/searchCat")
    public ResponseEntity<String> getCatInfo(@RequestParam("name") String catName) {

        return new ResponseEntity<>(this.searchCat.searchInfo(catName), HttpStatus.OK);
        //return null;
    }

    public static PublicKey convertPemToPublicKey(String pemPublicKey) throws Exception {
        String publicKeyPEM = pemPublicKey.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey convertPemToPrivateKey(String pemPrivateKey) throws Exception {
        String privateKeyPEM = pemPrivateKey.replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(keySpec);
    }
    // 使用公钥加密数据
    public static byte[] encrypt(String data) throws Exception {


        // 明文数据
        byte[] plaintext = data.getBytes();

        // 创建Cipher对象，并指定填充方式
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // 使用公钥进行加密
        cipher.init(Cipher.ENCRYPT_MODE, convertPemToPublicKey("-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh4Hi8LsmbHhWdslRUKRg\n" +
                "Bv0VH9q6VAHStQ8CmYUZ4e4K1M/Qv7HUCOgjCfrrwmiZ9fsOaKEXcn7dY6w19AWe\n" +
                "zAGPiqJwHLbLTVUHkKK8Iiw/O4atftLEfM2Y2FXSCXQL9W9MN0BiMFn9eC3vzejp\n" +
                "/n3Utv2e9Ji16XW93GN1xIloVXSiJ551G2r0DhG8rWoPbP33GJlAE5K6MXpoKnSn\n" +
                "IIZ6nWqWxWkZ900D0WiR58GYPY1TZ3D4yFelRg/5OD++fdZdC9pzUvpgZAGdYZhI\n" +
                "dBjons8uMvFF1bi5NIdfUk8R39M0TC/cCRJ0d8IG0FKQK3P8vCu+x64G0irPD/kq\n" +
                "7QIDAQAB\n" +
                "-----END PUBLIC KEY-----"));
        byte[] ciphertext = cipher.doFinal(plaintext);

        return ciphertext;
    }

    //使用私钥解密
    private static String decrypt(String data) throws Exception {

        // 创建Cipher对象，并指定填充方式
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        // 使用私钥进行解密
        cipher.init(Cipher.DECRYPT_MODE, convertPemToPrivateKey("-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCHgeLwuyZseFZ2\n" +
                "yVFQpGAG/RUf2rpUAdK1DwKZhRnh7grUz9C/sdQI6CMJ+uvCaJn1+w5ooRdyft1j\n" +
                "rDX0BZ7MAY+KonActstNVQeQorwiLD87hq1+0sR8zZjYVdIJdAv1b0w3QGIwWf14\n" +
                "Le/N6On+fdS2/Z70mLXpdb3cY3XEiWhVdKInnnUbavQOEbytag9s/fcYmUATkrox\n" +
                "emgqdKcghnqdapbFaRn3TQPRaJHnwZg9jVNncPjIV6VGD/k4P7591l0L2nNS+mBk\n" +
                "AZ1hmEh0GOiezy4y8UXVuLk0h19STxHf0zRML9wJEnR3wgbQUpArc/y8K77HrgbS\n" +
                "Ks8P+SrtAgMBAAECggEADzr0DOacDPkBjtxVvWCcQmzToP7ujl6eFieXvTWOkSw2\n" +
                "H3E0HzeXg2DD/20qbnFfTJxpJC1m+0FzFK7YaZLwJDHQtl5Hh5KsCwcRkJoZ1yVt\n" +
                "/fzHJ0f9OaHKGhabQuC+L1nRESy1XIXgzU8sctJI6oeuDts2sHsxhwnqywiKxgoZ\n" +
                "tuRYNeNBdPsWkX6kgX1UTKYKiZcQs4VLzO0Cr7mmSmWgStpFfywF36Zly4/XGY1f\n" +
                "L3QT2Uit6L3vj3J1nFZP4y0sT85EQTW2LnSpD6zy9law6qT8dln0xvjmgWuwkF0h\n" +
                "ROzhVDpOpuXzlnWLwl5Lr7Ys6TIofnjp/lD69BfZaQKBgQDE+2mkmc1cgXUq9W6N\n" +
                "0am6us5EksS1PcDtw2K2K+FXr7YvnIZSYtGRLiaq4f1L+MpwWNs7JzaKYZv5lO4b\n" +
                "K5s9faugBCmNJkW8+TY6sCZarjpVAoV2SNChYPxvEOrXC6UXfOmU4TlHSTql8Gsa\n" +
                "O7d43H2sNtbEpzNAcCJBO03ctwKBgQCwG1gbS6nG667nCL9VgCUOWqnIXHyIzJMx\n" +
                "56KEcNX37YPPQvmGTG92B6DpByJV8QjJDhQyD43M0GQ36YDHgDztqQku/XQB7T7X\n" +
                "rok67C3tYXEOmB7upAxgTith5l4dOyLdvzb6FoXgipQWqg3uvP0G1ayu728xS3TZ\n" +
                "u6plM0/ZewKBgQCZoaRxdmtO3MEvciB5h66jLpM1tGqrGLDiP+M7RdoI88haNfAl\n" +
                "TWE5pA8QdBWELVRmb0b9ScYmA58M93UFx+rZ+Kvkr5pgthrqGkyLVVt0mjJwnvjM\n" +
                "mlnto6D+VulyM2jdetULAuYTjS+qwGooGa8tGnsB1/uFgQpeFZcB3WkK3QKBgEa/\n" +
                "NW4Bv3rCQ8lcokDe8gQKOBrdzMnIJXnu7EL4cXzgVgt1hHiHTF/GQMZcr1aHNlhO\n" +
                "554U0YpuTVSI4ctDoTZ4qc/O0kPT42MSIt8g8nJiquCKDCMilDY8MQFrYnOrxh3/\n" +
                "Kz7cvXuvMD7Koj4+fge4316HocvT3GV8FsABFRrxAoGAb1DrEilSM7z7KFGxKMla\n" +
                "Pzcd4ECjvTUwjmpjHtYja4u7Ws89msz//YSu0bEdvDH2psHRpjH/8VwuXYtt+7yp\n" +
                "4AATmejcN0JBfvP1zPsfF2lb1m0tAnpRnjs0RA5DzewLzg8WMAd2FIugjWgbxbwz\n" +
                "SWlHMKXA4WMQYNgMlKdMBIs=\n" +
                "-----END RSA PRIVATE KEY-----"));
        //byte[] decryptedText = cipher.doFinal(ciphertext);

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decryptedBytes);
    }

    /**
     * Setter注入
     *
     * @param searchCatImpl Setter注入
     */
    @Autowired
    @Qualifier("SearchCat")
    public void setSearchCat(SearchCatImpl searchCatImpl) {

        this.searchCat = searchCatImpl;
    }

    /**
     * Setter注入
     *
     * @param encryptionImpl Setter注入
     */
    @Autowired
    @Qualifier("encryption")
    public void setSearchCat(EncryptionImpl encryptionImpl) {

        this.encryption = encryptionImpl;
    }
}
