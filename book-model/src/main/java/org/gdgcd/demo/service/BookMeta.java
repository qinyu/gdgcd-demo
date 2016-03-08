package org.gdgcd.demo.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookMeta {
    @JsonProperty("ID")
    public String id;

    @JsonProperty("Title")
    public String title;

    @JsonProperty("SubTitle")
    public String subTitle;

    @JsonProperty("Description")
    public String description;

    @JsonProperty("Image")
    public String imageUrl;
    public String isbn;

}
