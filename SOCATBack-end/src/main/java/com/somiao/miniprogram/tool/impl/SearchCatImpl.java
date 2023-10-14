package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.*;
import com.somiao.miniprogram.entity.*;
import com.somiao.miniprogram.tool.interf.DownloadData;
import com.somiao.miniprogram.tool.interf.SearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找猫的信息
 *
 * @author h1c
 */
@Service("SearchCat")
public class SearchCatImpl implements SearchData {

    /**
     * 猫
     */
    private Cat          cat = new Cat();
    /**
     * 猫的json信息
     */
    private String       catInfoJson;
    /**
     * 数据库接口
     */
    private SearchCatDao searchCatDao;

    private SearchImageDao searchImageDao;

    private SearchRelationshipDao searchRelationshipDao;

    private DownloadData downloadData;

    /**
     * 获取猫
     *
     * @return 猫
     */
    public Cat getCat() {

        return this.cat;
    }

    /**
     * 把猫的信息转为json字符串
     *
     * @return json字符串
     */
//    @Override
//    public String toString() {
//
//        //数据库查询接口
//        //@Autowired
//        ////////////////
//
//        //查到的字符串存入map，方便转json
//        Map<String,String> catInfoMap=new HashMap<>(20);
//
//        //存入map
//        //catInfoMap.put();
//        ////////////////
//
//        //转json
//        //this.catInfoJson=
//    }

    /**
     * 根据猫的名字在数据库中查找信息
     *
     * @param catName 猫的名字
     *
     * @return 找到则返回json类型的字符串，未找到则返回null
     */
    @Override
    public String searchInfo(String catName) {

        if(!searchCatDao.searchByName(catName).isEmpty()){
            System.out.println(searchCatDao.searchByName(catName).toString());
            return searchCatDao.searchByName(catName).toString();
        }
        System.out.println("11111111111111111111");
        return null;
    }

    public String searchOneLevelCatList()
    {
        if(!searchCatDao.searchOneLevelCatList().isEmpty()){
            System.out.println(searchCatDao.searchOneLevelCatList().toString());
            return searchCatDao.searchOneLevelCatList().toString();
        }
        return null;
    }

    public String searchByHairColor(int color)
    {
        if(!searchCatDao.searchByHairColorClassification(color).isEmpty()){
            return searchCatDao.searchByHairColorClassification(color).toString();
        }
        return null;
    }

    public String searchByState(String state)
    {
        if(!searchCatDao.searchByState(state).isEmpty()){
            return searchCatDao.searchByState(state).toString();
        }
        return null;
    }

    public String searchById(int id)
    {
        if(!searchCatDao.searchByID(id).isEmpty()){
            return searchCatDao.searchByID(id).toString();
        }
        return null;
    }
    /**
     * 在数据库中按数据ID查找并返回信息
     *
     * @param ID 数据在数据库中的id
     *
     * @return 找到则返回json类型的字符串，未找到则返回null
     */
    @Override
    public String searchInfo(int ID) {

        return null;
    }


   /* *//**
     * 构造返回对象
     * *//*
    public class AtlaInf {
        private String image;
        private String name;

        public AtlaInf() {
        }

        public AtlaInf(String image, String name) {
            this.image = image;
            this.name = name;
        }

    }*/

    public class Object{
        private String image;
        private String name;

        public Object() {
        }

        public Object(String image, String name) {
            this.image = image;
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 在数据库中按照毛色和状态查询并返回信息
     *
     * @Param 毛色hairColor  状态state
     *
     * @return 返回猫咪的姓名和照片，放在List中
     * */

    //Object object = new Object();
    List<Object> atlas = new ArrayList<>();
    @Override
    public List<Object> getAtlaInformation(String hairColor, String state) {


        List<Integer> id = new ArrayList<>();

        if(!searchCatDao.searchIdByHairColorAndState(hairColor,state).isEmpty()) {
            id = searchCatDao.searchIdByHairColorAndState(hairColor, state);
        }
        System.out.println(id);
            for (int i = 0; i < id.size(); i++) {
              // images = searchCatInfoDao.searchByCatID(id.get(i));
                System.out.println(searchImageDao.searchCatImageById(id.get(i)).get(0).getImage_path());
                System.out.println(searchCatDao.searchByID(id.get(i)).get(0).getName());
                Object ob = new Object(searchImageDao.searchCatImageById(id.get(i)).get(0).getImage_path(),searchCatDao.searchByID(id.get(i)).get(0).getName());
              //  String encodedImage = Base64.getEncoder().encodeToString(downloadData.downloadImage(searchImageDao.searchCatImageById(id.get(i)).get(0).getImage_path()));
                atlas.add(ob);
               // atlas.add("name:"+searchCatDao.searchByID(id.get(i)).get(0).getName());
               /* new AtlaInf(searchImageDao.searchCatImageById(id.get(i)).get(0).getImage_path(),
                        searchCatDao.searchByID(id.get(i)).get(0).getName());*/
            }
            return atlas;
    }





    /**
     * Setter注入
     *
     * @param searchCatDao Setter注入
     */
    @Autowired
    private void setSearchCatDao(SearchCatDao searchCatDao) {

        this.searchCatDao = searchCatDao;
    }

    /**
     * Setter注入
     *
     * @param searchImageDao Setter注入
     */
    @Autowired
    private void setSearchImageDao(SearchImageDao searchImageDao) {

        this.searchImageDao = searchImageDao;
    }

    /**
     * Setter注入
     *
     * @param downloadData Setter注入
     */
    @Autowired
    private void setDownloadData(DownloadData downloadData) {

        this.downloadData = downloadData;
    }

}
