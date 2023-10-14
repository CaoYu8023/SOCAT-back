package com.somiao.miniprogram.entity;

public class rescueAllInfo {

    private int id;

    private String userImg;
    private String userName;
    private String title;
    private String detailImg;
    private String detailTime;
    private String mode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailImg() {
        return detailImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public String getDetailTime() {
        return detailTime;
    }

    public void setDetailTime(String detailTime) {
        this.detailTime = detailTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public rescueAllInfo(int id, String userImg, String userName, String title, String detailImg, String detailTime, String mode) {
        this.id = id;
        this.userImg = userImg;
        this.userName = userName;
        this.title = title;
        this.detailImg = detailImg;
        this.detailTime = detailTime;
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "rescueAllInfo{" +
                "id=" + id +
                ", userImg='" + userImg + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", detailImg='" + detailImg + '\'' +
                ", detailTime='" + detailTime + '\'' +
                ", mode='" + mode + '\'' +
                '}';
    }
}
