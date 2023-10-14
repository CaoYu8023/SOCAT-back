package com.somiao.miniprogram.controller;

import com.somiao.miniprogram.tool.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchDetailController {

    /**
     * 搜索猫
     */
    private SearchCatImpl searchCat;

    private SearchImageImpl searchImage;

    private SearchRelationshipImpl searchRelationship;

    private SearchDetailImpl searchDetail;


    /*JSONObject json = new JSONObject();
        json.put("image", encodedImage);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json.toString());*/


    //按照猫咪name返回详情
    @PostMapping("/searchDetail")
    public ResponseEntity<String> searchDetailByName(@RequestParam("name") String name)
    {
        return new ResponseEntity<>(this.searchDetail.getDetail(name), HttpStatus.OK);
    }

    /**
     * Setter注入
     *
     * @param searchDetail Setter注入
     */
    @Autowired
    private void setSearchDetail(SearchDetailImpl searchDetail) {

        this.searchDetail = searchDetail;
    }


}
