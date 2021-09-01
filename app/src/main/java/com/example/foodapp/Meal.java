package com.example.foodapp;

public class Meal {
    int _id;
    private String name, price, imageUrl;

    public Meal() {
    }

    public Meal(int _id, String name, String price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "-" + name + "*" +price + "/" + imageUrl;
    }
}
