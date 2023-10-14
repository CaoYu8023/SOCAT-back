package com.somiao.miniprogram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.somiao.miniprogram.entity.*;
import com.somiao.miniprogram.tool.impl.SearchCatImpl;
import com.somiao.miniprogram.tool.impl.SearchImageImpl;
import com.somiao.miniprogram.tool.impl.SearchRelationshipImpl;
import com.somiao.miniprogram.tool.impl.SearchRescueInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 用于控制搜索
 *
 * @author h1c
 */

@RestController
public class SearchController {

    /**
     * 搜索猫
     */
    private SearchCatImpl searchCat;

    private SearchImageImpl searchImage;

    private SearchRelationshipImpl searchRelationship;

    private SearchRescueInfoImpl searchRescueInfo;

    /**
     * 查到并返回信息
     *
     * @param catName 猫的名字
     *
     * @return 找到则返回json类型的字符串，未找到则返回null
     */
    @PostMapping("/searchCat")
    public ResponseEntity<String> getCatInfo(@RequestParam("name") String catName) {

        System.out.println("输出" + catName + "输出");

        return new ResponseEntity<>(this.searchCat.searchInfo(catName), HttpStatus.OK);
        //return null;
    }

    @GetMapping("/getOneLevelCatList")
    public ResponseEntity<String> getOneLevelCatList()
    {
        return new ResponseEntity<>(this.searchCat.searchOneLevelCatList(), HttpStatus.OK);
    }

    //按花色分类猫
    @PostMapping("/searchByHairColor")
    public List searchByHairColor(@RequestParam("color") int color) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchCat.searchByHairColor(color));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<AtSchool_CatList> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            int id = node.get("id").asInt();
            String name = node.get("name").asText();
            AtSchool_CatList atSchool_catlist = new AtSchool_CatList(id,name);
            objectList.add(atSchool_catlist);
        }
        return objectList;
    }

    //根据猫的毛色和状态查找猫的图片和姓名
    @GetMapping("/getAtlasInfo")
    public List<SearchCatImpl.Object> getAtlaInformation(@RequestParam("hairColor") String hairColor, @RequestParam("state") String state) {

        return searchCat.getAtlaInformation(hairColor, state);
    }

    //按猫咪状态查找（离世，健康，送养，失踪）
    @PostMapping("/searchByState")
    public List searchByState(@RequestParam("state") String state) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchCat.searchByState(state));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<AtSchool_CatList> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String name = node.get("name").asText();
            int id = node.get("id").asInt();
            AtSchool_CatList atSchool_catlist = new AtSchool_CatList(id,name);
            objectList.add(atSchool_catlist);
        }
        return objectList;
    }


    //按猫咪ID查找
    @PostMapping("/searchById")
    public List searchById(@RequestParam("id") int id) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchCat.searchById(id));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<catItem_info> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String name = node.get("name").asText();
            int cat_id = node.get("id").asInt();
            String hairColor = node.get("hairColor").asText();
            String hauntLocation = node.get("hauntLocation").asText();
            String state = node.get("state").asText();
            String descriptionOfAppearance = node.get("descriptionOfAppearance").asText();
            String relationship = node.get("relationship").asText();
            String firstWitnessedTime = node.get("firstWitnessedTime").asText();
            int xxx = node.get("sterilization").asInt();
            String isSterilization = null;
            if(xxx == -1){
                isSterilization = "未知";
            } else if (xxx == 0) {
                isSterilization = "未绝育";
            } else if (xxx ==1) {
                isSterilization = "绝育";
            }
            String gender = node.get("gender").asText();
            String characterOfCat = node.get("character").asText();
            if(hairColor.equals(""))
            {
                hairColor = "暂无";
            }
            if(hauntLocation.equals(""))
            {
                hauntLocation = "暂无";
            }
            if(state.equals(""))
            {
                state = "暂无";
            }
            if(descriptionOfAppearance.equals(""))
            {
                descriptionOfAppearance = "暂无";
            }
            if(relationship.equals(""))
            {
                relationship = "暂无";
            }
            if(firstWitnessedTime.equals(""))
            {
                firstWitnessedTime = "暂无";
            }
            if(gender.equals(""))
            {
                gender = "暂无";
            }
            if(characterOfCat.equals(""))
            {
                characterOfCat = "暂无";
            }
            catItem_info catItem_info = new catItem_info(name,cat_id,hairColor,hauntLocation,state,descriptionOfAppearance,relationship,firstWitnessedTime,isSterilization,gender,characterOfCat);
            objectList.add(catItem_info);
        }
        return objectList;
    }

    //按指定猫咪ID查找照片
    @PostMapping("/searchCatImageById")
    public List searchCatImageById(@RequestParam("id") int id) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchImage.searchCatImageById(id));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<Image_List> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String image_path = node.get("image_path").asText();
            Image_List image_List = new Image_List(image_path);
            objectList.add(image_List);
        }
        return objectList;
    }

    //按指定救助文章ID查找照片
    @PostMapping("/searchArticleImageById")
    public List searchArticleImageById(@RequestParam("id") int id) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchImage.searchArticleImageById(id));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<Image_List> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String image_path = node.get("image_path").asText();
            Image_List image_List = new Image_List(image_path);
            objectList.add(image_List);
        }
        return objectList;
    }

    //按指定用户ID查找照片
    @PostMapping("/searchUserImageById")
    public List searchUserImageById(@RequestParam("id") int id) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchImage.searchUserImageById(id));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<Image_List> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String image_path = node.get("image_path").asText();
            Image_List image_List = new Image_List(image_path);
            objectList.add(image_List);
        }
        return objectList;
    }

    //按照用户account查找照片
    @PostMapping("searchUserImageByAccount")
    public List searchUserImageByAccount(@RequestParam("account") String account) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchImage.searchUserImageByAccount(account));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<Image_List> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String image_path = node.get("image_path").asText();
            Image_List image_List = new Image_List(image_path);
            objectList.add(image_List);
        }
        return objectList;
    }

    //按照cat的id找关系
    @PostMapping("/searchRelationshipById")
    public List searchRelationshipById(@RequestParam("id") int id) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(this.searchRelationship.searchRelationshipById(id));

        if(this.searchRelationship.searchRelationshipById(id).equals("null"))
        {
            return new ArrayList<>();
        }
        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchRelationship.searchRelationshipById(id));

        System.out.println(this.searchRelationship.searchRelationshipById(id));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<Relationship> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            int Rid = node.get("rid").asInt();
            String relationship = node.get("relationship").asText();

            Relationship Relationship = new Relationship(id,relationship);
            objectList.add(Relationship);
        }
        return objectList;
    }

    //查找所有救助文章
    @GetMapping ("/searchRescueInfo")
    public List searchRescueInfo() throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchRescueInfo.searchRescueInfo());

        // 从JSON中提取多个字段组成的对象并放入列表
        List<rescueAllInfo> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            int id = node.get("article_id").asInt();
            String userImg = node.get("userImg").asText();
            String userName = node.get("userName").asText();
            String title = node.get("title").asText();
            String detailImg = node.get("detailImg").asText();
            String detailTime = node.get("detailTime").asText();
            String mode = node.get("mode").asText();
            rescueAllInfo rescueAllInfo = new rescueAllInfo(id,userImg,userName,title,detailImg,detailTime,mode);
            objectList.add(rescueAllInfo);
        }
        return objectList;
    }

    //按指定救助文章id查找文章
    @PostMapping("/searchRescueInfoByArticleId")
    public List searchRescueInfoByArticleId(@RequestParam("id") int id) throws JsonProcessingException {
        // 创建Jackson ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();

        // 解析JSON字符串
        JsonNode jsonNode = objectMapper.readTree(this.searchRescueInfo.searchRescueInfoByArticleId(id));

        // 从JSON中提取多个字段组成的对象并放入列表
        List<detailInfo> objectList = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            String userImg = node.get("userImg").asText();
            String userName = node.get("userName").asText();
            String title = node.get("title").asText();
            String content = node.get("content").asText();
            String detailImg = node.get("detailImg").asText();
            String detailTime = node.get("detailTime").asText();
            String mode = node.get("mode").asText();
            detailInfo detailInfo = new detailInfo(userImg,userName,title,content,detailImg,detailTime);
            objectList.add(detailInfo);
        }
        return objectList;
    }

    /**
     * Setter注入
     *
     * @param searchCatImpl Setter注入
     */
    @Autowired
    @Qualifier("SearchCat")
    public void setSearchCat(SearchCatImpl searchCatImpl) {

        this.searchCat = searchCatImpl;
    }

    /**
     * Setter注入
     *
     * @param searchImage Setter注入
     */
    @Autowired
    @Qualifier("SearchImage")
    public void setSearchCat(SearchImageImpl searchImage) {

        this.searchImage = searchImage;
    }

    /**
     * Setter注入
     *
     * @param searchRelationship Setter注入
     */
    @Autowired
    @Qualifier("SearchRelationship")
    public void setSearchCat(SearchRelationshipImpl searchRelationship) {

        this.searchRelationship = searchRelationship;
    }

    /**
     * Setter注入
     *
     * @param searchRescueInfo Setter注入
     */
    @Autowired
    @Qualifier("SearchRescueInfo")
    public void setSearchCat(SearchRescueInfoImpl searchRescueInfo) {

        this.searchRescueInfo = searchRescueInfo;
    }
}
