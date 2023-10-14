package com.somiao.miniprogram.entity;

public class detailInfo {

    private String userImg;
    private String userName;
    private String title;

    private String content;
    private String detailImg;
    private String detailTime;

    @Override
    public String toString() {
        return "detailInfo{" +
                "userImg='" + userImg + '\'' +
                ", userName='" + userName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", detailImg='" + detailImg + '\'' +
                ", detailTime='" + detailTime + '\'' +
                '}';
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public detailInfo(String userImg, String userName, String title, String content, String detailImg, String detailTime) {
        this.userImg = userImg;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.detailImg = detailImg;
        this.detailTime = detailTime;
    }
}
