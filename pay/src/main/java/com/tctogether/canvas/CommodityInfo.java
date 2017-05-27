package com.tctogether.canvas;

/**
 * 商品信息
 */

public class CommodityInfo {

    private String id;
    private String name;
    private String price;
    private int payType;
    private String drawid;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getDrawid() {
        return drawid;
    }

    public void setDrawid(String drawid) {
        this.drawid = drawid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
