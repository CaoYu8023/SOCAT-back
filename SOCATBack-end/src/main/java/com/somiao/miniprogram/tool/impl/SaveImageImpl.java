package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.exception.SaveException;
import com.somiao.miniprogram.tool.interf.SaveData;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 程序会保存图片到指定文件夹的/image/miniProgram或/image/APP中，指定的文件夹路径在properties的dirPath中修改，文件夹若不存在则手动新建一个，新建的文件夹必须包含/image/miniprogram和/image/APP这两个文件夹
 *
 * @author h1c
 */
@Service("SaveImage")
public class SaveImageImpl implements SaveData {

    /**
     * 保存图片
     *
     * @param programType miniProgram/APP 小程序/APP
     * @param imageName   yyyy_MM_dd_HH_mm_ss_SSSS 年_月_日_小时_分钟_秒_毫秒
     * @param image       图片文件
     * @param dirPath     文件夹路径
     *
     * @return Save image success! 保存成功则返回，失败则抛异常
     *
     * @throws SaveException 保存异常
     */
    @Override
    public String save(String programType, String imageName, MultipartFile image, String dirPath) throws SaveException {

        //首先判断图片是否存在
        if (image.isEmpty()) {

            //不存在则抛出异常
            throw new SaveException("Image is empty!");
        }
        //存在则尝试保存图片
        else {

            //设置文件要保存的位置
            String savePath = dirPath +
                    "/Cache/" +
                    "image/" +
                    programType +
                    "/" +
                    imageName;

            File imageFileDestination = new File(savePath);

            //判断目标文件夹是否存在
            if (imageFileDestination.getParentFile().exists()) {
                try {

                    //目录存在且图片名称不冲突则保存图片
                    if (!imageFileDestination.exists()) {

                        //返回成功信息
                        image.transferTo(imageFileDestination);
                        return "Save image success!";
                    }
                    //图片名称冲突则不保存
                    else {

                        //向调用者抛出异常
                        throw new SaveException("Save image fail!Image exists!");
                    }
                }
                //捕获保存图片时抛出的IO异常
                catch (IOException e) {

                    //控制台打印错误信息
                    e.printStackTrace();
                    //抛出异常给调用者
                    throw new SaveException("Save image fail! IOException!");
                }
            }
            //目标文件夹不存在则给调用者抛出异常
            else {

                throw new SaveException("Save image fail! No directory!");
            }
        }

    }

    @Override
    public String save_miniProgram(String programType, String imageName, String image, String dirPath) throws SaveException {

        //首先判断图片是否存在
        if (image.isEmpty()) {

            //不存在则抛出异常
            throw new SaveException("Image is empty!");
        }
        //存在则尝试保存图片
        else {

            //设置文件要保存的位置
            String savePath = dirPath +
                    "/Cache/" +
                    "image/" +
                    programType +
                    "/" +
                    imageName;

            File imageFileDestination = new File(savePath);

            //判断目标文件夹是否存在
            if (imageFileDestination.getParentFile().exists()) {
                try {

                    //目录存在且图片名称不冲突则保存图片
                    if (!imageFileDestination.exists()) {

                        //返回成功信息
                        // base64字符串转字节数组
//                        byte[] bytes = Base64.getDecoder().decode(image);
                        byte[] bytes = Base64.getMimeDecoder().decode(image.replaceAll("+","%2B"));
                        try (FileOutputStream fos = new FileOutputStream(imageFileDestination)) {
                            fos.write(bytes);
                        }

                        return "Save image success!";
                    }
                    //图片名称冲突则不保存
                    else {

                        //向调用者抛出异常
                        throw new SaveException("Save image fail!Image exists!");
                    }
                }
                //捕获保存图片时抛出的IO异常
                catch (IOException e) {

                    //控制台打印错误信息
                    e.printStackTrace();
                    //抛出异常给调用者
                    throw new SaveException("Save image fail! IOException!");
                }
            }
            //目标文件夹不存在则给调用者抛出异常
            else {

                throw new SaveException("Save image fail! No directory!");
            }
        }

    }

}
