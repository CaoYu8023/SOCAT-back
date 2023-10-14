package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.Cat;
import com.somiao.miniprogram.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 提供数据库操作接口
 *
 * @author h1c
 */
@Repository
@Mapper
public interface SearchImageDao {

    List<Image> searchCatImageById(Integer ID);

    List<Image> searchCommentImageById(Integer ID);

    List<Image> searchArticleImageById(Integer ID);

    List<Image> searchUserImageById(Integer ID);

    List<Image> searchUserImageByAccount(String account);
}
