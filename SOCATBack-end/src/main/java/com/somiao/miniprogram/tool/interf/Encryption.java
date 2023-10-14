package com.somiao.miniprogram.tool.interf;

import com.somiao.miniprogram.entity.User;

public interface Encryption {

    //public void encrypted(int id,String password);

    public void addUser(User user);

    public boolean contrast(String account,String password);
}
