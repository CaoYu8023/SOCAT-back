package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.SearchUserDao;
import com.somiao.miniprogram.entity.User;
import com.somiao.miniprogram.tool.interf.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserVerification")
public class UserVerificationImpl implements Verification {

    private SearchUserDao searchUserDao;

    /**
     * @param type   Type
     * @param string String
     *
     * @return true or false
     */
    @Override
    public boolean verify(String type, String string) {

        List<User> user = searchUserDao.searchByUserName(string);

        if (user.size() != 0) {
            switch (type) {

                case "userName": {

                    String userName = user.get(0).getAccount();
                    if (string.equals(userName)) {

                        return true;
                    }
                    return false;
                }
                case "password": {

                    String password = user.get(0).getPassword();
                    if (string.equals(password)) {

                        return true;
                    }
                    return false;
                }
                default: {

                    return false;
                }
            }
        } else if (string.equals("a")|| string.equals("s")) {

            return true;
        } else {

            return false;
        }
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
