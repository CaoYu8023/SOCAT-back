package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Comments)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-06 09:40:57
 */
@Repository
@Mapper
public interface SearchCommentsDao {

    /**
     * 通过猫咪ID查询数据
     *
     * @param id 主键
     * @return 实例对象
     */
    List<Comments> searchByCatId(Integer id);


}

