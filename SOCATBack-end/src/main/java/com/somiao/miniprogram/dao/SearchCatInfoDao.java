package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.Cat;
import com.somiao.miniprogram.entity.CatInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface SearchCatInfoDao {

    int addImage(CatInfo catInfo);

    int deleteImage(Integer ID);

    List<CatInfo> searchByCatID(Integer ID);

    List<CatInfo> searchAll();

    int getCountsOfImage(Integer id);
}
