package com.example.foodapp;

public class FavouritesInfo {
    private String Name ,imgUrl, Price;

    public FavouritesInfo() {
    }

    public FavouritesInfo(String name, String imgURL, String price) {
        this.Name = name;
        this.imgUrl = imgURL;
        this.Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImgURL() {
        return imgUrl;
    }

    public void setImgURL(String imgURL) {
        this.imgUrl = imgURL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }
}
