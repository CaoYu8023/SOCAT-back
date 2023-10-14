package com.somiao.miniprogram.tool.interf;

import com.somiao.miniprogram.entity.Cat;

import java.util.List;

/**
 * 提供下载功能的接口
 *
 * @author h1c
 */
public interface DownloadData {

    /**
     * 根据关键字下载相关的文件
     *
     * @param keyWords 关键字
     *
     * @return 字节数据
     */
    public byte[] downloadImage(String keyWords);

    /**
     * 根据关键字返回数据库中相关的数据
     *
     * @param type 关键字类型
     * @param keywords 关键字
     *
     * @return List
     */
    public List<Cat> downloadString(String type, String keywords);
}
