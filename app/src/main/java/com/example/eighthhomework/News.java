package com.example.eighthhomework;

public class News {
    private String title;
    private String publishTime;
    private String name;

    void setTitle(String title) {
        this.title = title;
    }

    void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
    public String getPublishTime() {
        return publishTime;
    }
    public String getTitle() {
        return title;
    }
    public String toString(){
        return ""+title+""+publishTime+""+name;
    }
}
