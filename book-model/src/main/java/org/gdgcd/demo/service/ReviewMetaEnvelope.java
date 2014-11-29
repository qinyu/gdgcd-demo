package org.gdgcd.demo.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ReviewMetaEnvelope {

    @Expose
    @SerializedName("books")
    private List<ReviewMeta> bookReviews = new ArrayList<ReviewMeta>();

    /**
     * @return The bookReviews
     */
    public List<ReviewMeta> getBookReviews() {
        return bookReviews;
    }

    /**
     * @param bookReviews The bookReviews
     */
    public void setBookReviews(List<ReviewMeta> bookReviews) {
        this.bookReviews = bookReviews;
    }

}

