package com.aite.a.bean;

import java.util.List;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ShopChatBean2 {


    /**
     * name : 售前2
     * type : im
     * num : 14
     * img : https://daluxmall.com/data/resource/img/type3.png
     */

    private String name;
    private String type;
    private String num;
    private String img;
    private String title;//自定义数据

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
