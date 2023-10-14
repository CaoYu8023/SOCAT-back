package com.somiao.miniprogram.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.somiao.miniprogram.entity.Cat;
import com.somiao.miniprogram.entity.Prediction;
import com.somiao.miniprogram.exception.IdentificationException;
import com.somiao.miniprogram.tool.impl.IdentificationCatImpl;
import com.somiao.miniprogram.tool.impl.SearchCatImpl;
import com.somiao.miniprogram.tool.interf.DownloadData;
import com.somiao.miniprogram.tool.interf.Identification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * 用于控制识别
 *
 * @author h1c
 */
@RestController
public class IdentificationController {

    /**
     * 读取数据存放的路径
     */
    @Value("${dirPath}")
    private String dirPath;
    /**
     * 识别程序文件位置
     */
    @Value("${pyPath}")
    private String pyPath;
    /**
     * 权重文件位置
     */
    @Value("${weightPath}")
    private String weightPath;
    /**
     * 识别结果文件位置
     */
    @Value("${resultPath}")
    private String resultPath;
    /**
     * conda环境名称
     */
    @Value("${envName}")
    private String envName;

    /**
     * 添加猫识别功能
     */
    private Identification identificationCat;
    /**
     * 下载器
     */
    private DownloadData   downloadData;

    private SearchCatImpl searchCat;

    @Value("${CatGalleryPath}")
    private String CatGalleryPath;

    /**
     * 识别接口，识别的前提是需要识别的图片已经上传至服务器
     *
     * @param programType 发送的数据来自于哪
     * @param imageName   图片名称
     *
     * @return 识别结果 前端调用url格式为： /identificationCat?programType=【是小程序则填miniProgram，是APP则填APP】?imageName=【前端给图片起好名称，这个名称就是图片最终在文件夹内的名称】
     */
    @GetMapping("/identificationCat")
//    public ResponseEntity<String> identificationCat(@RequestParam("programType") String programType, @RequestParam("imageName") String imageName) {
//
//        identificationCat.setParam(pyPath, weightPath, resultPath, envName);
//
//        //设置图片所在的位置
//        String imagePath = this.dirPath +
//                "/image/" +
//                programType +
//                "/" +
//                imageName;
//
//        //调用识别
//        try {
//
//            System.out.println("identification...");
//            //如果识别成功
//            if (identificationCat.identification(imagePath)) {
//
//                //返回识别结果
//                return new ResponseEntity<>(identificationCat.getResult(), HttpStatus.OK);
//            } else {
//
//                System.out.println("YOLO Error");
//                return new ResponseEntity<>("YOLO Error", HttpStatus.ACCEPTED);
//            }
//        } catch (IdentificationException e) {
//
//            System.out.println("identification fail");
//            //控制台打印异常并返回异常
//            e.printStackTrace();
//            //并向前端返回错误信息
//            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
//        }
//    }
//    public List<Cat> identificationCat(@RequestParam("programType") String programType, @RequestParam("imageName") String imageName) {
    public List<Prediction> identificationCat(@RequestParam("programType") String programType, @RequestParam("imageName") String imageName) {

        identificationCat.setParam(pyPath, weightPath, resultPath, envName);

        //设置图片所在的位置
        String imagePath = this.dirPath +
                "/Cache/" +
                "image/" +
                programType +
                "/" +
                imageName;

        System.out.println(imagePath);

        //调用识别
        try {

            System.out.println("identification...");
            //如果识别成功
            // TODO: 一定删除true！
            if (identificationCat.identification(imagePath)) {

                //返回识别结果
                //return Collections.singletonList(identificationCat.getResult());
//                String[] result = new String[10];
//                result = identificationCat.getResult().split(" ");
//                return downloadData.downloadString("ID", result[0]);
                List<Prediction> predictions = new ArrayList<>();

                // 读取result.txt文件并解析内容
                try (BufferedReader br = new BufferedReader(new FileReader(this.resultPath))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        int label = Integer.parseInt(parts[0].split(":")[1].trim());
                        // TODO: 根据数字查询数据库，获取猫咪的名字
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode node = mapper.readTree(this.searchCat.searchById(label));
                        String catName = node.get(0).get("name").asText();;
                        System.out.println("cat: " + catName);
                        // 创建File对象
                        File folder = new File(this.CatGalleryPath + "\\" + catName);
                        // 获取文件夹下所有文件的文件名
                        String[] files = folder.list((dir, name) -> new File(dir, name).isFile());
                        //  然后根据名字获取图片
                        String encodedImage = Base64.getEncoder().encodeToString(downloadData.downloadImage(this.dirPath +
                                "/CatGallery/" +
                                catName + "/" + files[0]));
                        double probability = Double.parseDouble(parts[1].split(":")[1].trim());
                        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
                        String formattedProbability = decimalFormat.format(probability);
//                        predictions.add(new Prediction(label, formattedProbability));
                        predictions.add(new Prediction(catName, formattedProbability, encodedImage));
                    }

                    br.close();

                    return predictions;

                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {

                System.out.println("YOLO Error");
                //return Collections.singletonList("YOLO Error");
                return null;
            }
        } catch (IdentificationException e) {

            System.out.println("identification fail");
            //控制台打印异常并返回异常
            e.printStackTrace();
            //并向前端返回错误信息
            //return Collections.singletonList(e.toString());
            return null;
        }

        return null;
    }

    /**
     * Setter注入
     *
     * @param identificationCatImpl Setter注入
     */
    @Autowired
    @Qualifier("IdentificationCat")
    public void setIdentificationCat(IdentificationCatImpl identificationCatImpl) {

        this.identificationCat = identificationCatImpl;
    }

    /**
     * Setter注入
     *
     * @param downloadData Setter注入
     */
    @Autowired
    @Qualifier("DownloadCatInfo")
    public void setDownloadData(DownloadData downloadData) {

        this.downloadData = downloadData;
    }

    /**
     * Setter注入
     *
     * @param searchCatImpl Setter注入
     */
    @Autowired
    @Qualifier("SearchCat")
    public void setSearchCat(SearchCatImpl searchCatImpl) {

        this.searchCat = searchCatImpl;
    }
}
