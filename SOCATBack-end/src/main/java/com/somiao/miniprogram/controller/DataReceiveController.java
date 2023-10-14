package com.somiao.miniprogram.controller;

import com.somiao.miniprogram.exception.SaveException;
import com.somiao.miniprogram.tool.impl.SaveImageImpl;
import com.somiao.miniprogram.tool.interf.SaveData;
import com.somiao.miniprogram.tool.interf.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用于控制各种数据的接收
 *
 * @author h1c
 */
@RestController
public class DataReceiveController {

    /**
     * 读取数据存放的路径
     */
    @Value("${dirPath}")
    private String dirPath;

    /**
     * 图片保存
     */
    private SaveData     saveImage;
    /**
     *
     */
    private Verification userVerification;

    /**
     * 接收前端发送的图片，图片数据类型为MultipartFile 前端调用url格式为： /uploadImage?programType=【是小程序则填miniProgram，是APP则填APP】?imageName=【前端给图片起好名称，这个名称就是图片最终在文件夹内的名称】
     * 示例： http://localhost:8080/uploadImage?programType=miniProgram&imageName=389-409-1635513634415.png
     *
     * @param programType 数据来源，是小程序则填miniProgram，是APP则填APP
     * @param imageName   图片名称
     * @param image       图片
     *
     * @return 成功则为HttpStatus.OK，失败则为HttpStatus.BAD_REQUEST
     */
    @PostMapping("/uploadImage")
    public ResponseEntity<String> getImage(@RequestParam("programType") String programType, @RequestParam("imageName") String imageName, @RequestParam("image") MultipartFile image) {

        try {

            System.out.println("getImage...");
            //如果不报错就向前端返回结果
            return new ResponseEntity<>(saveImage.save(programType, imageName, image, this.dirPath), HttpStatus.OK);
        } catch (SaveException e) {

            System.out.println("getImage fail");
            //如果保存图片时出错就在控制台打印错误
            e.printStackTrace();
            //并向前端返回错误信息
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/uploadImage_miniProgram")
    public ResponseEntity<String> getImage_miniProgram(@RequestParam("programType") String programType,@RequestParam("imageName") String imageName, @RequestParam("data") String image) throws SaveException {

        System.out.println("getImage...");
        //如果不报错就向前端返回结果
            return new ResponseEntity<>(saveImage.save_miniProgram(programType, imageName, image, this.dirPath), HttpStatus.OK);
    }

    @GetMapping("verificationUser")
    public String verificationUser(@RequestParam String userName, @RequestParam String password) {

        //System.out.println(userName);
        //System.out.println(password);
        if (userVerification.verify("userName", userName) && userVerification.verify("password", password)) {

            return "1";
        }
        return "0";
    }

    @PostMapping("/uploadRescue_Release")
    public void uploadRescueRelease(@RequestParam String title, @RequestParam String type, @RequestParam String date, @RequestParam String time, @RequestParam String position, @RequestParam String detail) {


    }
    /**
     * 前端发送需要查询的猫的名称，后端返回数据库中查到的信息
     *
     * @param catName 猫的名称
     * @return json字符串
     */
//    @GetMapping("/searchCat")
//    public ResponseEntity<String> searchCat(@RequestParam("catName") String catName) {
//
//        //查找信息
//        SearchCatImpl searchCat=new SearchCatImpl();
//
//        searchCat(catName)
//    }

    /**
     * Setter注入
     *
     * @param saveImageImpl Setter注入
     */
    @Autowired
    @Qualifier("SaveImage")
    public void setSaveImage(SaveImageImpl saveImageImpl) {

        this.saveImage = saveImageImpl;
    }

    /**
     * Setter注入
     *
     * @param verification Setter注入
     */
    @Autowired
    @Qualifier("UserVerification")
    public void setSaveImage(Verification verification) {

        this.userVerification = verification;
    }
}
