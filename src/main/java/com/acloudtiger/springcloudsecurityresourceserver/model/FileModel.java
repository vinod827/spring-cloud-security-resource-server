package com.acloudtiger.springcloudsecurityresourceserver.model;

public class FileModel {

    private String name;

    private String date;

    private String link;

    public FileModel(String name, String date, String link) {
        this.name = name;
        this.date = date;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
