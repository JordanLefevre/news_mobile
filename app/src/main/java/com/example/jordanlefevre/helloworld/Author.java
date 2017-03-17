package com.example.jordanlefevre.helloworld;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by jordanlefevre on 17/03/2017.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Author {
    public int id;
    public String name;
}
