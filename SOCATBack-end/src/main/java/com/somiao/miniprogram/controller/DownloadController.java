package com.somiao.miniprogram.controller;

import com.alibaba.fastjson.JSONObject;
import com.somiao.miniprogram.entity.Cat;
import com.somiao.miniprogram.tool.interf.DownloadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.List;
import java.util.Base64;

import java.io.File;
import java.util.Arrays;

/**
 * 用于下载数据
 *
 * @author h1c
 */
@RestController
public class DownloadController {

    /**
     * 下载器
     */
    private DownloadData downloadData;

    @Value("${CatGalleryPath}")
    private String CatGalleryPath;

    @GetMapping("/getCatInfo")
    public List<Cat> getCatInfo(@RequestParam String type, @RequestParam String keywords) {

        return downloadData.downloadString(type, keywords);
    }

//    @GetMapping(value = "getCatImage")
//    public ResponseEntity<String> getCatImage(@RequestParam String path) {
//
//        String encodedImage = Base64.getEncoder().encodeToString(downloadData.downloadImage(path));
//        JSONObject json = new JSONObject();
//        json.put("image", encodedImage);
//
//        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(json.toString());
//    }


    @GetMapping(value = "/getCatImage")
    public ResponseEntity<List<String>> getCatImage(@RequestParam String catName) {

        // 创建File对象
        File folder = new File(this.CatGalleryPath + "\\" + catName);

        // 获取文件夹下所有文件的文件名
        String[] files = folder.list((dir, name) -> new File(dir, name).isFile());

        System.out.println(Arrays.toString(files));

        // 创建中文排序器
        Collator cmp = Collator.getInstance(Locale.CHINA);
        // 对文件名进行升序排序
        Arrays.sort(files, cmp);

        String encodedImage = new String();
        List<String> encodedImages = new java.util.ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            encodedImage = Base64.getEncoder().encodeToString(downloadData.downloadImage(this.CatGalleryPath + "\\" + catName + "/" + files[i]));
            encodedImage = "data:image/" + files[i].substring(files[i].lastIndexOf(".")+1) + ";base64," + encodedImage;
            encodedImages.add(encodedImage);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(encodedImages);

    }


    /**
     * Setter注入
     *
     * @param downloadData Setter注入
     */
    @Autowired
    @Qualifier("DownloadCatInfo")
    public void setDownloadData(DownloadData downloadData) {

        this.downloadData = downloadData;
    }
}
