package com.somiao.miniprogram.dao;

import com.somiao.miniprogram.entity.Cat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 提供数据库操作接口
 *
 * @author h1c
 */
@Repository
@Mapper
public interface SearchCatDao {


    List<Cat> searchOneLevelCatList();

    int deleteByID(int ID);


    List<Cat> searchByID(Integer ID);

    List<Cat> searchByName(String name);

    List<Cat> searchByInAtlas(Integer inAtlas);

    List<Cat> searchAll();

    List<Cat> searchByNickName(String nickName);

    List<Cat> searchByHairColor(int hairColor);

    List<Cat> searchByHairColorClassification(Integer hairColorClassification);

    List<Cat> searchByHauntLocation(String hauntLocation);

    List<Cat> searchByGender(String gender);

    List<Cat> searchByState(String state);

    List<Cat> searchByIsSterilization(Integer isSterilization);

    List<Cat> searchBySterilizationTime(String sterilizationTime);

    List<Cat> searchByDateOfBirth(String dateOfBirth);

    List<Cat> searchByDescriptionOfAppearance(String descriptionOfAppearance);

    List<Cat> searchByCharacterOfCat(Integer characterOfCat);

    List<Cat> searchByFirstWitnessedTime(String firstWitnessedTime);

    List<Cat> searchByFirstWitnessedLocation(String firstWitnessedLocation);

    List<Cat> searchByRelationship(String relationship);

    List<Cat> searchByMore(String more);

    List<Cat> searchByRoute(String route);

    List<Cat> searchByAdoptionTime(String adoptionTime);

    List<Cat> searchByTimeOfDeath(String timeOfDeath);

    List<Cat> searchByCauseOfDeath(String causeOfDeath);

    List<Cat> searchByAddAudio(String addAudio);

    List<Integer> searchIdByHairColorAndState(String hairColor,String state);

    int updateByPrimaryKey(Cat cat);
}
