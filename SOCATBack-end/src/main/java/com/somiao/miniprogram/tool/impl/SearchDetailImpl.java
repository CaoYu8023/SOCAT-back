package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.*;
import com.somiao.miniprogram.entity.Comments;
import com.somiao.miniprogram.tool.interf.SearchDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("SearchDetail")
public class SearchDetailImpl implements SearchDetail {


    /**
     * 数据库接口
     */
    private SearchCatDao searchCatDao;

    private SearchImageDao searchImageDao;

    private SearchRelationshipDao searchRelationshipDao;

    private SearchCommentsDao searchCommentsDao;



    /**
     * 构造返回对象
     * */
    public class CatDetail {

        private String info;
        private String images;
        private List<Relations> relations;
        private List<Comments> comments;

        public CatDetail() {
        }

        public CatDetail(String info, String images, List<Relations> relations, List<Comments> comments) {
            this.info = info;
            this.images = images;
            this.relations = relations;
            this.comments = comments;
        }
    }


    public class Relations {

        private String name;
        private String image;
        private String relationship;

        public Relations(String name, String image, String relationship) {
            this.name = name;
            this.image = image;
            this.relationship = relationship;
        }

        @Override
        public String toString() {
            return "Relations{" +
                    "name='" + name + '\'' +
                    ", image='" + image + '\'' +
                    ", relationship='" + relationship + '\'' +
                    '}'+"\n";
        }
    }

    @Override
    public String getDetail(String name) {

        List<Relations> relations = new ArrayList<>();

        if(!searchCatDao.searchByName(name).isEmpty()) {
            int id = searchCatDao.searchByName(name).get(0).getID();
            System.out.println(id);
            int cat_id_1 = searchRelationshipDao.searchRelationshipById(id).get(0).getRid();
            int cat_id_2 = searchRelationshipDao.searchRelationshipById(id).get(1).getRid();
            String info = searchCatDao.searchByID(id).get(0).toStringDetail();
            String images = searchImageDao.searchCatImageById(id).get(0).getImage_path();
            relations.add(new Relations(searchCatDao.searchByID(cat_id_1).get(0).getName(),
                    searchImageDao.searchCatImageById(cat_id_1).get(0).getImage_path(),
                    searchRelationshipDao.searchRelationshipById(id).get(0).getRelationship()));
            relations.add(new Relations(searchCatDao.searchByID(cat_id_2).get(0).getName(),
                    searchImageDao.searchCatImageById(cat_id_2).get(0).getImage_path(),
                    searchRelationshipDao.searchRelationshipById(id).get(0).getRelationship()));
            List<Comments> comments = searchCommentsDao.searchByCatId(id);

            CatDetail catDetail = new CatDetail(info,images,relations,comments);

            return catDetail.info+"\n"+"images:"+catDetail.images+"\n"+relations+"\n"+catDetail.comments;
        }
        return null;
    }

    /**
     * Setter注入
     *
     * @param searchCatDao Setter注入
     */
    @Autowired
    @Qualifier("searchCatDao")
    public void setSearchCatDao(SearchCatDao searchCatDao) {
        this.searchCatDao = searchCatDao ;
    }

    /**
     * Setter注入
     *
     * @param searchRelationshipDao Setter注入
     */
    @Autowired
    @Qualifier("searchRelationshipDao")
    public void SearchRelationshipDao(SearchRelationshipDao searchRelationshipDao) {

        this.searchRelationshipDao = searchRelationshipDao;
    }

    /**
     * Setter注入
     *
     * @param searchImageDao Setter注入
     */
    @Autowired
    @Qualifier("searchImageDao")
    public void setSearchImageDao(SearchImageDao searchImageDao) {

        this.searchImageDao = searchImageDao;
    }

    /**
     * Setter注入
     *
     * @param searchCommentsDao Setter注入
     */
    @Autowired
    @Qualifier("searchCommentsDao")
    public void setSearchCommentsDao(SearchCommentsDao searchCommentsDao) {

        this.searchCommentsDao = searchCommentsDao;
    }
}
