package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface SearchUserDao {

    int addUser(User user);

    int deleteUser(String userName);

    int updateUser(User user);

    List<User> searchByUserAccount(String account);

    List<User> searchByUserName(String userName);

    List<User> searchAll();

    String getEncrypted_password(String account);
}
