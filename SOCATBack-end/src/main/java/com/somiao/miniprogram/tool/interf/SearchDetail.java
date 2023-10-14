package com.somiao.miniprogram.tool.interf;

import com.somiao.miniprogram.tool.impl.SearchCatImpl;

import java.util.List;

public interface SearchDetail {

    /**
     /**
     * 获得猫的所有图片和有关系的所有猫
     *
     * @Param 猫咪姓名name
     *
     * @return 返回猫咪详情信息
     * */
    public String getDetail(String name);
}
