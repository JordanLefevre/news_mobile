package com.example.jordanlefevre.helloworld;

import android.content.ContentValues;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jordanlefevre on 14/03/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class News {
    public int id;
    @JsonProperty("title")
    public String titre;

    @JsonProperty("content")
    public String description;

    @JsonProperty("author")
    public Author auteur;

    @JsonProperty("date")
    public String publicationDate;

    public Attachments[] attachments;

    @JsonProperty("comment_count")
    public int nb_comments;

    public boolean important;

    public Category[] categories;

    public News() {
        this.titre = "";
        this.description = "";
        this.publicationDate = "";
        this.nb_comments = 0;
        this.important = false;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put("title",this.titre);
        cv.put("content", this.description);
        cv.put("author",this.auteur.id);
        cv.put("date", this.publicationDate);
        cv.put("attachments", this.attachments[0].id);
        cv.put("comment_count", this.nb_comments);
        cv.put("categories", this.categories[0].id);

        return cv;
    }
}
