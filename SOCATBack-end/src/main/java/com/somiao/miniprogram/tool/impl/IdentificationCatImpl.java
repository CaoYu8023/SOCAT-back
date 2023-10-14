package com.somiao.miniprogram.tool.impl;

import com.somiao.miniprogram.exception.IdentificationException;
import com.somiao.miniprogram.tool.interf.Identification;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

/**
 * 识别程序，提供图片路径，返回识别结果
 *
 * @author h1c
 */
@Service("IdentificationCat")
public class IdentificationCatImpl implements Identification {

    /**
     * 识别结果
     */
    private String result;
    /**
     * 识别程序文件位置
     */
    private String pyPath;
    /**
     * 权重文件位置
     */
    private String weightPath;
    /**
     * 识别结果文件位置
     */
    private String resultPath;
    /**
     * conda环境名称
     */
    private String envName;

    /**
     * 设置参数和环境
     *
     * @param pyPath     识别程序的位置
     * @param weightPath 权重文件的位置
     * @param resultPath 识别结果存放的位置
     * @param envName    环境名称
     */
    @Override
    public void setParam(String pyPath, String weightPath, String resultPath, String envName) {

        this.result     = "";
        this.pyPath     = pyPath;
        this.weightPath = weightPath;
        this.resultPath = resultPath;
        this.envName    = envName;
    }

    /**
     * 提供图片路径，返回识别结果
     *
     * @param imagePath 图片路径
     *
     * @return 识别成功返回true，调用失败返回false，出错抛异常
     */
    @Override
    public boolean identification(String imagePath) throws IdentificationException{

        //清空识别结果
        this.result = "";

        //检查图片是否存在
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {

            //控制台输出开始识别的提示信息
            System.out.println("---------------------------Start YOLO---------------------------------");
            //识别进程
            Process process = null;
            try {

                //使用bash
                //! Ubuntu下使用/bin/bash，Windows下使用cmd
//                process = Runtime.getRuntime().exec("/bin/bash");
                process = Runtime.getRuntime().exec("cmd");
            } catch (IOException e) {

                //抛出IO异常
                e.printStackTrace();
                throw new IdentificationException(e);
            }

            //读取进程中的运行结果
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(process.getInputStream(),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            //构造识别命令，pyPath是识别程序的位置，imagePath是图像位置，weightPath是权重
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(process.getOutputStream())), true);

            //构造识别命令，pyPath是识别程序的位置，imagePath是图像位置，weightPath是权重
            //! Ubuntu下使用
//            out.println("bash /home/h1c/SOMiao/identificationCat.sh " + this.envName + " " + this.pyPath + " " + imagePath + " " + this.weightPath);
            //Windows下使用
            out.println("conda activate " + this.envName);
            // 显示当前conda环境
            out.println("conda info --envs");
            out.println("cd C:\\MyData\\Code\\cat_identify\\cat_rec");
            out.println("C:\\Users\\admin\\.conda\\envs\\SOCAT\\python.exe" + this.pyPath + " " + imagePath);
            //发送完所有命令必须发送这个语句
            out.println("exit");

            //输出读取到的进程中的所有结果
            String line = null;
            while (true) {

                try {

                    if ((line = in.readLine()) != null) {
                    } else {
                        break;
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }

                System.out.println(line);
            }

            //关闭
            try {

                in.close();
            } catch (IOException e) {

                e.printStackTrace();
                throw new IdentificationException(e);
            }
            try {

                process.waitFor();
            } catch (InterruptedException e) {

                e.printStackTrace();
                throw new IdentificationException(e);
            }
            out.close();
            process.destroy();

            //检查python执行是否成功
            try {

                //控制台打印python运行结果，返回值为0表示调用成功，1表示调用失败
                System.out.println("process.waitFor():" + process.waitFor());
                //调用失败则返回false
                if (process.waitFor() == 1) {

                    return false;
                }
            } catch (InterruptedException e) {

                //出现异常则控制台打印异常并抛出
                e.printStackTrace();
                throw new IdentificationException(e.toString());
            }
            //调用结束，控制台输出提示
            System.out.println(this.envName);
            System.out.println(this.pyPath);
            System.out.println("---------------------------End YOLO---------------------------------");

            //开始读取python识别结果，控制台输出提示
            System.out.println("Read result.txt");
            //读取文件
            File file = new File(this.resultPath);
            //如果存在且是文件
            if (file.exists() && file.isFile()) {

                //尝试读取
                try {

                    //构造一个BufferedReader类来读取文件
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    //一行一行读取
                    String result;
                    //使用readLine方法，一次读一行
                    while ((result = bufferedReader.readLine()) != null) {

                        //向识别结果添加内容
                        this.result = this.result + result + "\n";
                    }
                    //关闭流
                    bufferedReader.close();
                } catch (Exception e) {

                    //出现异常则在控制台输出，并抛出
                    e.printStackTrace();
                    throw new IdentificationException(e.toString());
                }
//                //删除文件
//                try {
//
//                    file.delete();
//                } catch (Exception e) {
//
//                    //出现异常则控制台输出并抛出
//                    e.printStackTrace();
//                    throw new IdentificationException(e.toString());
//                }

                //根据识别结果更新猫名
                System.out.println("result:" + this.result);
                return true;
            } else {

                //如果不是文件
                throw new IdentificationException("NO result.txt!");
            }
        } else {

            //如果图像不存在
            throw new IdentificationException("NO image!");
        }
    }

    /**
     * 获取识别结果
     *
     * @return 名字
     */
    @Override
    public String getResult() {

        return this.result;
    }
}
