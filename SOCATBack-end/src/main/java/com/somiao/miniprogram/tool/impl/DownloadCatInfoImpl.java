package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.dao.SearchCatDao;
import com.somiao.miniprogram.entity.Cat;
import com.somiao.miniprogram.tool.interf.DownloadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 下载猫的信息
 *
 * @author h1c
 */
@Service("DownloadCatInfo")
public class DownloadCatInfoImpl implements DownloadData {

    /**
     * 数据库接口
     */
    private SearchCatDao searchCatDao;

    /**
     * 根据图片在服务器中的路径下载图片
     *
     * @param path 图片路径
     *
     * @return 字节数据
     */
    @Override
    public byte[] downloadImage(String path) {

        File file = new File(path);
        byte[] bytes = new byte[0];

        //检查图片是否存在
        if (!file.exists()) {

            //不存在则返回空
            System.out.println("No Image!");
            return null;
        } else {

            System.out.println(path);
            //存在则返回数据
            try {

                FileInputStream inputStream = new FileInputStream(file);
                try {

                    bytes = new byte[inputStream.available()];
                    inputStream.read(bytes, 0, inputStream.available());
                    return bytes;
                } catch (IOException e) {

                    e.printStackTrace();
                    //todo 向调用者抛异常
                    //throw
                }
            } catch (FileNotFoundException e) {

                e.printStackTrace();
                //todo 向调用者抛异常
                //throw
            }
        }
        return null;
    }


    /**
     * 根据数据类型和关键字内容返回数据库中相关的数据
     *
     * @param type     数据类型
     * @param keywords 关键字
     *
     * @return json字符串
     */
    @Override
    public List<Cat> downloadString(String type, String keywords) {

        switch (type) {

            case "ID": {

                return searchCatDao.searchByID(Integer.parseInt(keywords));
            }
            case "name":{

                return searchCatDao.searchByName(keywords);
            }
            case "inAtlas":{

                return searchCatDao.searchByInAtlas(Integer.parseInt(keywords));
            }
            case "hairColor":{

                return searchCatDao.searchByHairColorClassification(Integer.valueOf(keywords));
            }
            case "ALL":{

                return  searchCatDao.searchAll();
            }
            //todo 还有更多情况需要添加
            default:{

                return null;
            }
        }
    }

    /**
     * Setter注入
     *
     * @param searchCatDao Setter注入
     */
    @Autowired
    private void setSearchCatDao(SearchCatDao searchCatDao) {

        this.searchCatDao = searchCatDao;
    }
}
