package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.SearchRescueInfoDao;
import com.somiao.miniprogram.dao.SearchUserDao;
import com.somiao.miniprogram.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class SearchUserImpl implements SearchUserDao {

    private SearchUserDao searchUserDao;
    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int deleteUser(String userName) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public List<User> searchByUserAccount(String account) {
        if(!searchUserDao.searchByUserAccount(account).isEmpty()){
            return searchUserDao.searchByUserAccount(account);
        }
        return null;
    }

    @Override
    public List<User> searchByUserName(String userName) {
        return null;
    }
    @Override
    public List<User> searchAll() {
        return null;
    }

    @Override
    public String getEncrypted_password(String account) {
        return null;
    }

    /**
     * Setter注入
     *
     * @param searchUserDao Setter注入
     */
    @Autowired
    private void setSearchUserDa(SearchUserDao searchUserDao) {

        this.searchUserDao = searchUserDao;
    }
}
