package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.Cat;
import com.somiao.miniprogram.entity.Image;
import com.somiao.miniprogram.entity.Relationship;
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
public interface SearchRelationshipDao {

    List<Relationship> searchRelationshipById(Integer ID);
}
