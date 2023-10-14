package com.somiao.miniprogram.tool.impl;


import com.somiao.miniprogram.dao.SearchImageDao;
import com.somiao.miniprogram.dao.SearchRelationshipDao;
import com.somiao.miniprogram.entity.Image;
import com.somiao.miniprogram.entity.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SearchRelationship")
public class SearchRelationshipImpl {

    private Relationship relationship;

    private SearchRelationshipDao searchRelationshipDao;

    public String searchRelationshipById(int id)
    {
        if(!searchRelationshipDao.searchRelationshipById(id).isEmpty()){
            return searchRelationshipDao.searchRelationshipById(id).toString();
        }
        return "null";
    }

    /**
     * Setter注入
     *
     * @param searchRelationshipDao Setter注入
     */
    @Autowired
    private void setSearchCatDao(SearchRelationshipDao searchRelationshipDao) {

        this.searchRelationshipDao = searchRelationshipDao;
    }
}
