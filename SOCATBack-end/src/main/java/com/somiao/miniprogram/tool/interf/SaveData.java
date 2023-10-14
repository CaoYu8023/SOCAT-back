package com.somiao.miniprogram.tool.interf;

import com.somiao.miniprogram.exception.SaveException;
import org.springframework.web.multipart.MultipartFile;

/**
 * 提供保存功能的接口
 *
 * @author h1c
 */
public interface SaveData {

    /**
     * 按数据来源保存
     *
     * @param programType miniProgram/APP 小程序/APP
     * @param fileName   yyyy_MM_dd_HH_mm_ss_SSSS 年_月_日_小时_分钟_秒_毫秒
     * @param file       文件
     * @param dirPath     文件夹路径
     *
     * @return 保存成功则返回，失败则抛异常
     *
     * @throws SaveException 保存异常
     */
    public String save(String programType, String fileName, MultipartFile file, String dirPath) throws SaveException;

    public String save_miniProgram(String programType, String imageName, String image, String dirPath) throws SaveException;
}
