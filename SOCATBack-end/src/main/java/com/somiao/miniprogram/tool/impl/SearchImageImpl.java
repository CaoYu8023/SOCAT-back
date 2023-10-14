package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.SearchImageDao;
import com.somiao.miniprogram.dao.SearchUserDao;
import com.somiao.miniprogram.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SearchImage")
public class SearchImageImpl {

    private Image image;

    private SearchImageDao searchImageDao;

    private SearchUserDao searchUserDao;

    public String searchCatImageById(int id)
    {
        if(!searchImageDao.searchCatImageById(id).isEmpty()){
            return searchImageDao.searchCatImageById(id).toString();
        }
        return null;
    }

    public String searchUserImageById(int id)
    {
        if(!searchImageDao.searchUserImageById(id).isEmpty()){
            return searchImageDao.searchUserImageById(id).toString();
        }
        return null;
    }

    public String searchUserImageByAccount(String account)
    {
        if(!searchImageDao.searchUserImageByAccount(account).isEmpty()){
            return searchImageDao.searchUserImageByAccount(account).toString();
        }
        return null;
    }

    public String searchArticleImageById(int id)
    {
        if(!searchImageDao.searchArticleImageById(id).isEmpty()){
            return searchImageDao.searchArticleImageById(id).toString();
        }
        return null;
    }

    /**
     * Setter注入
     *
     * @param searchImageDao Setter注入
     */
    @Autowired
    private void setSearchCatDao(SearchImageDao searchImageDao) {

        this.searchImageDao = searchImageDao;
    }
}
