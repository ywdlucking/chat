package com.ywd.model;

/**
 * Created by admin on 2017/7/3.
 */
public class Message {

    private String name;
    private String img;
    private String time;
    private String context;

    public Message() {
    }

    public Message(String name, String img, String time, String context) {
        this.name = name;
        this.img = img;
        this.time = time;
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
