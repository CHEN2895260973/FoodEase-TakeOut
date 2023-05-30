package com.example.order.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {
    private int id;
    private String foodName;   	  //菜品名称
    private String popularity;    //人气
    private String saleNum;    	  //月售量
    private int price;	  //价格
    private int count;         	  //添加到购物车中的数量
    private int foodPic;        //菜品图片


    public OrderBean(int id, String foodName, String popularity, String saleNum, int price, int count, int foodPic) {
        this.id = id;
        this.foodName = foodName;
        this.popularity = popularity;
        this.saleNum = saleNum;
        this.price = price;
        this.count = count;
        this.foodPic = foodPic;
    }


    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
    public String getPopularity() {
        return popularity;
    }
    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
    public String getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getFoodPic() {
        return foodPic;
    }
    public void setFoodPic(int foodPic) {
        this.foodPic = foodPic;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}