package com.somiao.miniprogram.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

/**
 * 猫的信息
 *
 * @author h1c
 */
public class Cat implements Serializable {

    /**
     * 序列化
     */
    private static final long    serialVersionUID = 1L;
    /**
     * ID
     */
    private              int     ID;
    /**
     * 名字
     */
    private              String  name;
    /**
     * 是否写入图鉴
     */
    private              int     inAtlas;
    /**
     * 昵称
     */
    private              String  nickName;
    /**
     * 毛色
     */
    private              String  hairColor;
    /**
     * 毛色分类
     */
    private              String  hairColorClassification;
    /**
     * 出没地点
     */
    private              String  hauntLocation;
    /**
     * 性别
     */
    private              String  gender;
    /**
     * 状态
     */
    private              String  state;
    /**
     * 是否绝育
     */
    private              boolean isSterilization;
    /**
     * 绝育时间
     */
    private              String  sterilizationTime;
    /**
     * 出生年月
     */
    private              String  dateOfBirth;
    /**
     * 外貌简述
     */
    private              String  descriptionOfAppearance;
    /**
     * 性格
     */
    private              String  character;
    /**
     * 第一次被目击时间
     */
    private              String  firstWitnessedTime;
    /**
     * 第一次被目击地点
     */
    private              String  firstWitnessedLocation;
    /**
     * 关系
     */
    private              String  relationship;
    /**
     * 更多
     */
    private              String  more;
    /**
     * 线路
     */
    private              String  route;
    /**
     * 送养时间
     */
    private              String  adoptionTime;
    /**
     * 离世时间
     */
    private              String  timeOfDeath;
    /**
     * 离世原因
     */
    private              String  causeOfDeath;
    /**
     * 是否加音频
     */
    private              String addAudio;
    /**
     * 在数据库中的索引
     */
    private              int    indexes;
    /**
     * 审核状态
     */
    private              String    approval_status;

    /**
     * 含参构造函数
     *
     * @param ID                      ID
     * @param name                    名字
     * @param inAtlas                 是否写入图鉴
     * @param nickName                昵称
     * @param hairColor               毛色
     * @param hairColorClassification 毛色分类
     * @param hauntLocation           出没地点
     * @param gender                  性别
     * @param state                   状态
     * @param isSterilization         是否绝育
     * @param sterilizationTime       绝育时间
     * @param dateOfBirth             出生年月
     * @param descriptionOfAppearance 外貌简述
     * @param character               性格
     * @param firstWitnessedTime      第一次被目击时间
     * @param firstWitnessedLocation  第一次被目击地点
     * @param relationship            关系
     * @param more                    更多
     * @param route                   线路
     * @param adoptionTime            送养时间
     * @param timeOfDeath             离世时间
     * @param causeOfDeath            离世原因
     * @param addAudio                是否加音频
     * @param indexes                   在数据库中的索引
     */
    public Cat(int ID, String name, int inAtlas, String nickName, String hairColor, String hairColorClassification, String hauntLocation, String gender, String state, boolean isSterilization, String sterilizationTime, String dateOfBirth, String descriptionOfAppearance, String character, String firstWitnessedTime, String firstWitnessedLocation, String relationship, String more, String route, String adoptionTime, String timeOfDeath, String causeOfDeath, String addAudio, int indexes) {

        this.ID                      = ID;
        this.name                    = name;
        this.inAtlas                 = inAtlas;
        this.nickName                = nickName;
        this.hairColor               = hairColor;
        this.hairColorClassification = hairColorClassification;
        this.hauntLocation           = hauntLocation;
        this.gender                  = gender;
        this.state                   = state;
        this.isSterilization         = isSterilization;
        this.sterilizationTime       = sterilizationTime;
        this.dateOfBirth             = dateOfBirth;
        this.descriptionOfAppearance = descriptionOfAppearance;
        this.character               = character;
        this.firstWitnessedTime      = firstWitnessedTime;
        this.firstWitnessedLocation  = firstWitnessedLocation;
        this.relationship            = relationship;
        this.more                    = more;
        this.route                   = route;
        this.adoptionTime            = adoptionTime;
        this.timeOfDeath             = timeOfDeath;
        this.causeOfDeath            = causeOfDeath;
        this.addAudio                = addAudio;
        this.indexes                 = indexes;
    }

    /**
     * 无参构造函数
     */
    public Cat() {

        this.ID                      = 0;
        this.name                    = null;
        this.inAtlas                 = 0;
        this.nickName                = null;
        this.hairColor               = null;
        this.hairColorClassification = null;
        this.hauntLocation           = null;
        this.gender                  = null;
        this.state                   = null;
        this.isSterilization         = false;
        this.sterilizationTime       = null;
        this.dateOfBirth             = null;
        this.descriptionOfAppearance = null;
        this.character               = null;
        this.firstWitnessedTime      = null;
        this.firstWitnessedLocation  = null;
        this.relationship            = null;
        this.more                    = null;
        this.route                   = null;
        this.adoptionTime            = null;
        this.timeOfDeath             = null;
        this.causeOfDeath            = null;
        this.addAudio = null;
        this.indexes  = 0;
    }

    /**
     * 获取ID
     *
     * @return ID
     */
    public int getID() {

        return ID;
    }

    /**
     * 设置ID
     *
     * @param ID ID
     */
    public void setID(int ID) {

        this.ID = ID;
    }

    /**
     * 获取名字
     *
     * @return 名字
     */
    public String getName() {

        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * 查询是否存入图鉴中
     *
     * @return 是或否
     */
    public int isInAtlas() {

        return inAtlas;
    }

    /**
     * 设置是否在图鉴中
     *
     * @param inAtlas 是或否
     */
    public void setInAtlas(int inAtlas) {

        this.inAtlas = inAtlas;
    }

    /**
     * 获取昵称
     *
     * @return 昵称
     */
    public String getNickName() {

        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {

        this.nickName = nickName;
    }

    /**
     * 获取毛色
     *
     * @return 毛色
     */
    public String getHairColor() {

        return hairColor;
    }

    /**
     * 设置毛色
     *
     * @param hairColor 毛色
     */
    public void setHairColor(String hairColor) {

        this.hairColor = hairColor;
    }

    /**
     * 获取毛色分类
     *
     * @return 毛色分类
     */
    public String getHairColorClassification() {

        return hairColorClassification;
    }

    /**
     * 设置毛色分类
     *
     * @param hairColorClassification 毛色分类
     */
    public void setHairColorClassification(String hairColorClassification) {

        this.hairColorClassification = hairColorClassification;
    }

    /**
     * 获取出没地点
     *
     * @return 出没地点
     */
    public String getHauntLocation() {

        return hauntLocation;
    }

    /**
     * 设置出没地点
     *
     * @param hauntLocation 出没地点
     */
    public void setHauntLocation(String hauntLocation) {

        this.hauntLocation = hauntLocation;
    }

    /**
     * 获取性别
     *
     * @return 性别
     */
    public String getGender() {

        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(String gender) {

        this.gender = gender;
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public String getState() {

        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {

        this.state = state;
    }

    /**
     * 查看是否绝育
     *
     * @return 是或否
     */
    public boolean isSterilization() {

        return isSterilization;
    }

    /**
     * 设置是否绝育
     *
     * @param sterilization 是或否
     */
    public void setSterilization(boolean sterilization) {

        isSterilization = sterilization;
    }

    /**
     * 获取绝育时间
     *
     * @return 绝育时间
     */
    public String getSterilizationTime() {

        return sterilizationTime;
    }

    /**
     * 设置绝育时间
     *
     * @param sterilizationTime 绝育时间
     */
    public void setSterilizationTime(String sterilizationTime) {

        this.sterilizationTime = sterilizationTime;
    }

    /**
     * 获取出生日期
     *
     * @return 出生日期
     */
    public String getDateOfBirth() {

        return dateOfBirth;
    }

    /**
     * 设置出生日期
     *
     * @param dateOfBirth 出生日期
     */
    public void setDateOfBirth(String dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    /**
     * 获取外貌简述
     *
     * @return 外貌简述
     */
    public String getDescriptionOfAppearance() {

        return descriptionOfAppearance;
    }

    /**
     * 设置外貌简述
     *
     * @param descriptionOfAppearance 外貌简述
     */
    public void setDescriptionOfAppearance(String descriptionOfAppearance) {

        this.descriptionOfAppearance = descriptionOfAppearance;
    }

    /**
     * 获取性格
     *
     * @return 性格
     */
    public String getCharacter() {

        return character;
    }

    /**
     * 设置性格
     *
     * @param character 性格
     */
    public void setCharacter(String character) {

        this.character = character;
    }

    /**
     * 获取第一次被目击时间
     *
     * @return 第一次被目击时间
     */
    public String getFirstWitnessedTime() {

        return firstWitnessedTime;
    }

    /**
     * 设置第一次被目击时间
     *
     * @param firstWitnessedTime 第一次被目击时间
     */
    public void setFirstWitnessedTime(String firstWitnessedTime) {

        this.firstWitnessedTime = firstWitnessedTime;
    }

    /**
     * 获取第一次被目击地点
     *
     * @return 第一次被目击地点
     */
    public String getFirstWitnessedLocation() {

        return firstWitnessedLocation;
    }

    /**
     * 设置第一次被目击地点
     *
     * @param firstWitnessedLocation 第一次被目击地点
     */
    public void setFirstWitnessedLocation(String firstWitnessedLocation) {

        this.firstWitnessedLocation = firstWitnessedLocation;
    }

    /**
     * 获取关系
     *
     * @return 关系
     */
    public String getRelationship() {

        return relationship;
    }

    /**
     * 设置关系
     *
     * @param relationship 关系
     */
    public void setRelationship(String relationship) {

        this.relationship = relationship;
    }

    /**
     * 获取更多
     *
     * @return 更多
     */
    public String getMore() {

        return more;
    }

    /**
     * 设置更多
     *
     * @param more 更多
     */
    public void setMore(String more) {

        this.more = more;
    }

    /**
     * 获取线路
     *
     * @return 线路
     */
    public String getRoute() {

        return route;
    }

    /**
     * 设置线路
     *
     * @param route 线路
     */
    public void setRoute(String route) {

        this.route = route;
    }

    /**
     * 获取送养时间
     *
     * @return 送养时间
     */
    public String getAdoptionTime() {

        return adoptionTime;
    }

    /**
     * 送养时间
     *
     * @param adoptionTime 送养时间
     */
    public void setAdoptionTime(String adoptionTime) {

        this.adoptionTime = adoptionTime;
    }

    /**
     * 获取离世时间
     *
     * @return 离世时间
     */
    public String getTimeOfDeath() {

        return timeOfDeath;
    }

    /**
     * 设置离世时间
     *
     * @param timeOfDeath 离世时间
     */
    public void setTimeOfDeath(String timeOfDeath) {

        this.timeOfDeath = timeOfDeath;
    }

    /**
     * 获取离世原因
     *
     * @return 离世原因
     */
    public String getCauseOfDeath() {

        return causeOfDeath;
    }

    /**
     * 设置离世原因
     *
     * @param causeOfDeath 离世原因
     */
    public void setCauseOfDeath(String causeOfDeath) {

        this.causeOfDeath = causeOfDeath;
    }

    /**
     * 查看是否加音频
     *
     * @return 是或否
     */
    public String isAddAudio() {

        return addAudio;
    }

    /**
     * 设置是否加音频
     *
     * @param addAudio 是或否
     */
    public void setAddAudio(String addAudio) {

        this.addAudio = addAudio;
    }

    /**
     * 获取索引
     *
     * @return 索引
     */
    public int getIndexes() {

        return indexes;
    }

    /**
     * 设置索引
     *
     * @param indexes 索引
     */
    public void setIndexes(int indexes) {

        this.indexes = indexes;
    }

    /**
     * 设置所有的信息
     *
     * @param ID                      ID
     * @param name                    名字
     * @param inAtlas                 是否写入图鉴
     * @param nickName                昵称
     * @param hairColor               毛色
     * @param hairColorClassification 毛色分类
     * @param hauntLocation           出没地点
     * @param gender                  性别
     * @param state                   状态
     * @param isSterilization         是否绝育
     * @param sterilizationTime       绝育时间
     * @param dateOfBirth             出生年月
     * @param descriptionOfAppearance 外貌简述
     * @param character               性格
     * @param firstWitnessedTime      第一次被目击时间
     * @param relationship            关系
     * @param more                    更多
     * @param route                   线路
     * @param adoptionTime            送养时间
     * @param timeOfDeath             离世时间
     * @param causeOfDeath            离世原因
     * @param addAudio                是否加音频
     * @param index                   索引
     */
    public void setAllInfo(int ID, String name, int inAtlas, String nickName, String hairColor, String hairColorClassification, String hauntLocation, String gender, String state, boolean isSterilization, String sterilizationTime, String dateOfBirth, String descriptionOfAppearance, String character, String firstWitnessedTime, String firstWitnessedLocation, String relationship, String more, String route, String adoptionTime, String timeOfDeath, String causeOfDeath, String addAudio, int index) {

        this.ID                      = ID;
        this.name                    = name;
        this.inAtlas                 = inAtlas;
        this.nickName                = nickName;
        this.hairColor               = hairColor;
        this.hairColorClassification = hairColorClassification;
        this.hauntLocation           = hauntLocation;
        this.gender                  = gender;
        this.state                   = state;
        this.isSterilization         = isSterilization;
        this.sterilizationTime       = sterilizationTime;
        this.dateOfBirth             = dateOfBirth;
        this.descriptionOfAppearance = descriptionOfAppearance;
        this.character               = character;
        this.firstWitnessedTime      = firstWitnessedTime;
        this.firstWitnessedLocation  = firstWitnessedLocation;
        this.relationship            = relationship;
        this.more                    = more;
        this.route                   = route;
        this.adoptionTime            = adoptionTime;
        this.timeOfDeath             = timeOfDeath;
        this.causeOfDeath            = causeOfDeath;
        this.addAudio = addAudio;
        this.indexes  = index;
    }

    public String toStringName_Color(){
        return "{" +
                "name=" + hairColor +
                "imgName=" + name +
                "}";
    }


    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(this);
            return jsonString;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    public String toStringDetail() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", hauntLocation='" + hauntLocation + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                ", isSterilization=" + isSterilization +
                ", descriptionOfAppearance='" + descriptionOfAppearance + '\'' +
                ", character='" + character + '\'' +
                '}';
    }
}
