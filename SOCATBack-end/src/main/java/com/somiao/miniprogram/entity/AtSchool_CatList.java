package com.somiao.miniprogram.entity;

public class AtSchool_CatList {

    private int id;

    private String name;

    public AtSchool_CatList(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AtSchool_CatList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
