package org.gdgcd.demo.service;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class BookMetaEnvelope implements Page<BookMeta> {
    @JsonProperty("Error")
    public String errorNo;

    @JsonProperty("Time")
    public Float responseTime;

    @JsonProperty("Total")
    public String totalCount;

    @JsonProperty("Page")
    public Integer pageNo;

    @JsonProperty("Books")
    public List<BookMeta> bookList = new ArrayList<BookMeta>();

    @Override
    public List<BookMeta> getContentList() {
        return bookList;
    }
}
