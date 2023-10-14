package com.somiao.miniprogram.entity;

public class Prediction {
//    private int label;
    private String name;
    private String probability;
    private String image;

//    public Prediction(int label, String probability) {
//        this.label = label;
//        this.probability = probability;
//    }

    public Prediction(String name, String probability, String image) {
        this.name = name;
        this.probability = probability;
        this.image = image;
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public String getProbability() {
        return probability;
    }

    public String getImage() {
        return image;
    }

//    public int getLabel() {
//        return label;
//    }

//    public String getProbability() {
//        return probability;
//    }

//    @Override
//    public String toString() {
//        return "Label: " + label + ", Probability: " + probability;
//    }
}

