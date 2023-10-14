package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.RescueInfo;
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
public interface SearchRescueInfoDao {

    List<RescueInfo> searchRescueInfo();

    List<RescueInfo> searchRescueInfoByArticleId(Integer ID);
}
