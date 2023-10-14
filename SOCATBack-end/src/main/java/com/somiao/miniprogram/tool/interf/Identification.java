package com.somiao.miniprogram.tool.interf;

import com.somiao.miniprogram.exception.IdentificationException;

import java.util.List;

/**
 * 提供识别功能的接口
 * 基于yolo的识别
 *
 * @author h1c
 */
public interface Identification {

    /**
     * 识别
     *
     * @param imagePath 图片路径
     *
     * @return 识别成功返回true，调用失败返回false，出错抛异常
     *
     * @throws IdentificationException 识别异常
     */
    public boolean identification(String imagePath) throws IdentificationException;

    /**
     * 设置参数和环境
     *
     * @param pyPath     识别程序的位置
     * @param weightPath 权重文件的位置
     * @param resultPath 识别结果存放的位置
     * @param envName    环境名称
     */
    public void setParam(String pyPath, String weightPath, String resultPath, String envName);

    /**
     * 获取识别结果
     *
     * @return 名字
     */
    public String getResult();
}
