package com.somiao.miniprogram.controller;

import com.somiao.miniprogram.tool.RSA_Encryption_Decryption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import com.somiao.miniprogram.entity.UserInfo;

/**
 * @author h1c
 */
@RestController
public class TestConnection {

    @GetMapping("testConnectionGet")
    public String testConnectionGet(@RequestParam String requestParam){
        System.out.println(requestParam);
        return "Success";
    }

    @PostMapping ("testConnectionPost")
//    public ResponseEntity<List<Object>> testConnectionPost(@RequestParam("account") String account, @RequestParam("password") String password){
    public ResponseEntity<List<Object>> testConnectionPost(@RequestBody UserInfo user) throws Exception {

        System.out.println("Received a post request");

        System.out.println(user.account);
        System.out.println(user.password);

        System.out.println(RSA_Encryption_Decryption.decrypt(user.account));
        System.out.println(RSA_Encryption_Decryption.decrypt(user.password));

        List<Object> list = new ArrayList<>();
        list.add(true);
        list.add("Admin");
        list.add(4);

        return ResponseEntity.ok(list);
    }
}
