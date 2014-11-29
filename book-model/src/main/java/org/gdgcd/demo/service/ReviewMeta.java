package org.gdgcd.demo.service;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ReviewMeta{

    @Expose
    private Integer id;
    @Expose
    private String isbn;
    @Expose
    private String isbn13;
    @SerializedName("ratings_count")
    @Expose
    private Integer ratingsCount;
    @SerializedName("reviews_count")
    @Expose
    private Integer reviewsCount;
    @SerializedName("text_reviews_count")
    @Expose
    private Integer textReviewsCount;
    @SerializedName("work_ratings_count")
    @Expose
    private Integer workRatingsCount;
    @SerializedName("work_reviews_count")
    @Expose
    private Integer workReviewsCount;
    @SerializedName("work_text_reviews_count")
    @Expose
    private Integer workTextReviewsCount;
    @SerializedName("average_rating")
    @Expose
    private String averageRating;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn
     * The isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return
     * The isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     *
     * @param isbn13
     * The isbn13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     *
     * @return
     * The ratingsCount
     */
    public Integer getRatingsCount() {
        return ratingsCount;
    }

    /**
     *
     * @param ratingsCount
     * The ratings_count
     */
    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    /**
     *
     * @return
     * The reviewsCount
     */
    public Integer getReviewsCount() {
        return reviewsCount;
    }

    /**
     *
     * @param reviewsCount
     * The reviews_count
     */
    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    /**
     *
     * @return
     * The textReviewsCount
     */
    public Integer getTextReviewsCount() {
        return textReviewsCount;
    }

    /**
     *
     * @param textReviewsCount
     * The text_reviews_count
     */
    public void setTextReviewsCount(Integer textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
    }

    /**
     *
     * @return
     * The workRatingsCount
     */
    public Integer getWorkRatingsCount() {
        return workRatingsCount;
    }

    /**
     *
     * @param workRatingsCount
     * The work_ratings_count
     */
    public void setWorkRatingsCount(Integer workRatingsCount) {
        this.workRatingsCount = workRatingsCount;
    }

    /**
     *
     * @return
     * The workReviewsCount
     */
    public Integer getWorkReviewsCount() {
        return workReviewsCount;
    }

    /**
     *
     * @param workReviewsCount
     * The work_reviews_count
     */
    public void setWorkReviewsCount(Integer workReviewsCount) {
        this.workReviewsCount = workReviewsCount;
    }

    /**
     *
     * @return
     * The workTextReviewsCount
     */
    public Integer getWorkTextReviewsCount() {
        return workTextReviewsCount;
    }

    /**
     *
     * @param workTextReviewsCount
     * The work_text_reviews_count
     */
    public void setWorkTextReviewsCount(Integer workTextReviewsCount) {
        this.workTextReviewsCount = workTextReviewsCount;
    }

    /**
     *
     * @return
     * The averageRating
     */
    public String getAverageRating() {
        return averageRating;
    }

    /**
     *
     * @param averageRating
     * The average_rating
     */
    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

}