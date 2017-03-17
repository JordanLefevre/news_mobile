package com.example.jordanlefevre.helloworld;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jordanlefevre on 16/03/2017.
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseNews {
    public News[] posts;
}