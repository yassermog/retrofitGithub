package com.example.apigithub.model;

public class ItemModel {
    private final String title;
    private final String body;
    private final String topics;
    private final String htmlUrl;

    public ItemModel(String title, String body,String topics,String htmlUrl) {
        this.title = title;
        this.body = body;
        this.topics = topics;
        this.htmlUrl=htmlUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTopics() {
        return topics;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}
