package com.somiao.miniprogram.entity;

public class Image_List {

    private String image_path;

    public Image_List(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    @Override
    public String toString() {
        return "Image_List{" +
                "image_path='" + image_path + '\'' +
                '}';
    }
}
