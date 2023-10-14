package com.somiao.miniprogram.entity;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * (Comments)实体类
 *
 * @author makejava
 * @since 2023-07-06 09:40:58
 */
public class Comments {

    private Integer id;

    private String userAccount;
    
    private String text;
    
    private Integer userId;
    
    private Integer catId;
    
    private Integer parentCommentId;

    private Date comment_time;

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date time) {
        this.comment_time = time;
    }

    public String getUserAccount() { return userAccount; }

    public void setUserAccount(String userAccount) { this.userAccount = userAccount; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "userAccount='" + userAccount + '\'' +
                ", text='" + text + '\'' +
                ", comment_time=" + comment_time +
                '}';
    }
}

