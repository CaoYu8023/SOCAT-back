package com.somiao.miniprogram.tool.interf;

import com.somiao.miniprogram.tool.impl.SearchCatImpl;
import java.util.List;

/**
 * 提供搜索功能
 *
 * @author h1c
 */
public interface SearchData {

    /**
     * 在数据库中按关键字查找并返回信息
     *
     * @param Keyword 关键字
     *
     * @return 找到则返回json类型的字符串，未找到则返回null
     */
    public String searchInfo(String Keyword);

    /**
     * 在数据库中按数据ID查找并返回信息
     *
     * @param ID 数据在数据库中的ID
     *
     * @return 找到则返回json类型的字符串，未找到则返回null
     */
    public String searchInfo(int ID);

    /**
     * 在数据库中按照毛色和状态查询并返回信息
     *
     * @Param 毛色hairColor  状态state
     *
     * @return 返回猫咪的姓名和照片，放在List中
     * */
    public List<SearchCatImpl.Object> getAtlaInformation(String hairColor, String state);


}
