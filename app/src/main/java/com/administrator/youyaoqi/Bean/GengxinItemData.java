package com.administrator.youyaoqi.Bean;

/**
 * Created by Administrator on 2016/3/9.
 */
public class GengxinItemData {

    private String name;
    private String num;
    private String id;
    private String picNum;

    public String getPicNum() {
        return picNum;
    }

    public void setPicNum(String picNum) {
        this.picNum = picNum;
    }

    public GengxinItemData(String name, String picNum, String id, String num) {
        this.name = name;
        this.picNum = picNum;
        this.id = id;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GengxinItemData(String name, String id, String num) {
        this.name = name;
        this.id = id;
        this.num = num;
    }

    public GengxinItemData() {
    }

    public GengxinItemData(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
