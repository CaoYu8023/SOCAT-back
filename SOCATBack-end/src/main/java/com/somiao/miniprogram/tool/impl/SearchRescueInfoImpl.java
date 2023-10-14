package com.somiao.miniprogram.tool.impl;
import com.somiao.miniprogram.dao.SearchRelationshipDao;
import com.somiao.miniprogram.dao.SearchRescueInfoDao;
import com.somiao.miniprogram.entity.Image;
import com.somiao.miniprogram.entity.Relationship;
import com.somiao.miniprogram.entity.RescueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SearchRescueInfo")
public class SearchRescueInfoImpl {

    private RescueInfo rescueInfo;

    private SearchRescueInfoDao searchRescueInfoDao;

    public String searchRescueInfo()
    {
        if(!searchRescueInfoDao.searchRescueInfo().isEmpty()){
            return searchRescueInfoDao.searchRescueInfo().toString();
        }
        return null;
    }

    public String searchRescueInfoByArticleId(int id)
    {
        if(!searchRescueInfoDao.searchRescueInfoByArticleId(id).isEmpty()){
            return searchRescueInfoDao.searchRescueInfoByArticleId(id).toString();
        }
        return null;
    }

    /**
     * Setter注入
     *
     * @param searchRescueInfoDao Setter注入
     */
    @Autowired
    private void setSearchCatDao(SearchRescueInfoDao searchRescueInfoDao) {

        this.searchRescueInfoDao = searchRescueInfoDao;
    }
}
