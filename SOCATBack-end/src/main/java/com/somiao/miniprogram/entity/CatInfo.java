package com.somiao.miniprogram.entity;

import java.awt.*;

public class CatInfo {
    private Integer ID;
    private String name;
    private String imagePath;

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        ID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "catInfo{" +
                "Id=" + ID +
                ", name='" + name + '\'' +
                ", image=" + imagePath +
                '}';
    }

    public Integer getId() {
        return ID;
    }

}
