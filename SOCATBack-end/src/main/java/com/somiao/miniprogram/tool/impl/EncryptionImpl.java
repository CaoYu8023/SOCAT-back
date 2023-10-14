package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.SearchUserDao;
import com.somiao.miniprogram.entity.User;
import com.somiao.miniprogram.tool.interf.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("encryption")
public class EncryptionImpl implements Encryption {

    private SearchUserDao searchUserDao;

    //密码加密
    public String encodePasswd(String password)
    {
        // 创建BCryptPasswordEncoder实例
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 对密码进行加密
        String encodedPassword = encoder.encode(password);

        return encodedPassword;
    }



    //将加密密码存入数据库
    /*
    public void encrypted(int id,String password)//需要判断是否存在此id
    {
        this.searchUserDao.encrypted(id,password);
    }

     */

    @Override
    public void addUser(User user) {

        String encodePassword = this.encodePasswd(user.getPassword());
        user.setEncrypted_password(encodePassword);
        this.searchUserDao.addUser(user);
    }

    //判断用户名密码是否符合
    @Override
    public boolean contrast(String account, String password) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(password, this.searchUserDao.getEncrypted_password(account));
        return result;
    }

    /**
     * Setter注入
     *
     * @param searchUserDao Setter注入
     */
    @Autowired
    private void setSearchCatDao(SearchUserDao searchUserDao) {

        this.searchUserDao = searchUserDao;
    }
}
