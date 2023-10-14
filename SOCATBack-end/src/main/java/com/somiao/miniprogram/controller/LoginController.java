package com.somiao.miniprogram.controller;

import com.somiao.miniprogram.entity.User;
import com.somiao.miniprogram.entity.UserInfo;
import com.somiao.miniprogram.tool.impl.EncryptionImpl;
import com.somiao.miniprogram.tool.impl.SearchUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {

    private EncryptionImpl encryption;

    private SearchUserImpl searchUser;

    /**
     * 验证登录
     * */
    @PostMapping("/checkLogin")
//    public ResponseEntity<List<Object>> testConnectionPost(@RequestParam("account") String account, @RequestParam("password") String password){
    public ResponseEntity<List<Object>> checkLogin(@RequestBody UserInfo user) throws Exception {

        List<Object> list = new ArrayList<>();

        String password2 = encryption.encodePasswd(user.password);
        System.out.println(password2);
        System.out.println(searchUser.searchByUserAccount(user.account));
        if(encryption.contrast(user.account, user.password)){
           list.add(true);
           list.add(searchUser.searchByUserAccount(user.account).get(0).getUsername());
           list.add(searchUser.searchByUserAccount(user.account).get(0).getPermission_level());
           return new ResponseEntity<> (list,HttpStatus.OK);
        }
          list.add(false);
          return ResponseEntity.ok(list);
    }

    /**
     * 用户注册
     * */
    @PostMapping("/sign")
    public void Sign(@RequestBody User user){
          encryption.addUser(user);
    }


    /**
     * Setter注入
     *
     * @param searchUser Setter注入
     */
    @Autowired
    private void setSearchUser(SearchUserImpl searchUser) {

        this.searchUser = searchUser;
    }

    /**
     * Setter注入
     *
     * @param encryption Setter注入
     */
    @Autowired
    private void setEncryptionImpl(EncryptionImpl encryption) {

        this.encryption = encryption;
    }
}
