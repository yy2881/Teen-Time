package com.example.myapplication;

import java.text.DateFormat;
import java.util.Date;

public class Article {
    public int articleId;
    public String title;
    public boolean articleIsSaved;
    public String content;
    public String resource;
    public Date releasedDate;
    public boolean isViewed = false;
    public boolean isRelationship;
    public boolean isSexualHealth;
    public boolean isSexualBehavior;


    public Article(int articleId, String title, boolean articleIsSaved, String content, String resource, Date releasedDate,  boolean isRelationship, boolean isSexualHealth, boolean isSexualBehavior) {
        this.articleId = articleId;
        this.title = title;
        this.articleIsSaved = articleIsSaved;
        this.content = content;
        this.resource = resource;
        this.releasedDate = releasedDate;

        this.isRelationship = isRelationship;
        this.isSexualHealth = isSexualHealth;
        this.isSexualBehavior = isSexualBehavior;
    }

    public Article() {

    }
}
