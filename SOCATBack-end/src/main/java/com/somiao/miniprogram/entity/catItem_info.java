package com.somiao.miniprogram.entity;

public class catItem_info {

    private String name;
    private int cat_id;
    private String hairColor;
    private String hauntLocation;
    private String state;
    private String descriptionOfAppearance;
    private String relationship;
    private String firstWitnessedTime;
    private String isSterilization;
    private String gender;
    private String characterOfCat;



    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHauntLocation() {
        return hauntLocation;
    }

    public void setHauntLocation(String hauntLocation) {
        this.hauntLocation = hauntLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescriptionOfAppearance() {
        return descriptionOfAppearance;
    }

    public void setDescriptionOfAppearance(String descriptionOfAppearance) {
        this.descriptionOfAppearance = descriptionOfAppearance;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFirstWitnessedTime() {
        return firstWitnessedTime;
    }

    public void setFirstWitnessedTime(String firstWitnessedTime) {
        this.firstWitnessedTime = firstWitnessedTime;
    }

    public String getIsSterilization() {
        return isSterilization;
    }

    public void setIsSterilization(String isSterilization) {
        this.isSterilization = isSterilization;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCharacterOfCat() {
        return characterOfCat;
    }

    public void setCharacterOfCat(String characterOfCat) {
        this.characterOfCat = characterOfCat;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public catItem_info(String name, int cat_id, String hairColor, String hauntLocation, String state, String descriptionOfAppearance, String relationship, String firstWitnessedTime, String isSterilization, String gender, String characterOfCat) {
        this.name = name;
        this.cat_id = cat_id;
        this.hairColor = hairColor;
        this.hauntLocation = hauntLocation;
        this.state = state;
        this.descriptionOfAppearance = descriptionOfAppearance;
        this.relationship = relationship;
        this.firstWitnessedTime = firstWitnessedTime;
        this.isSterilization = isSterilization;
        this.gender = gender;
        this.characterOfCat = characterOfCat;
    }

    @Override
    public String toString() {
        return "catItem_info{" +
                "name='" + name + '\'' +
                ", cat_id=" + cat_id +
                ", hairColor='" + hairColor + '\'' +
                ", hauntLocation='" + hauntLocation + '\'' +
                ", state='" + state + '\'' +
                ", descriptionOfAppearance='" + descriptionOfAppearance + '\'' +
                ", relationship='" + relationship + '\'' +
                ", firstWitnessedTime='" + firstWitnessedTime + '\'' +
                ", isSterilization='" + isSterilization + '\'' +
                ", gender='" + gender + '\'' +
                ", characterOfCat='" + characterOfCat + '\'' +
                '}';
    }
}
