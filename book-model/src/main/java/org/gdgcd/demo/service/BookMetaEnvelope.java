package org.gdgcd.demo.service;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twer on 11/4/14.
 */
public class BookMetaEnvelope {
    @Expose
    private String Error;
    @Expose
    private Float Time;
    @Expose
    private String Total;
    @Expose
    private Integer Page;
    @Expose
    private List<BookMeta> Books = new ArrayList<BookMeta>();

    /**
     *
     * @return
     * The Error
     */
    public String getError() {
        return Error;
    }

    /**
     *
     * @param Error
     * The Error
     */
    public void setError(String Error) {
        this.Error = Error;
    }

    /**
     *
     * @return
     * The Time
     */
    public Float getTime() {
        return Time;
    }

    /**
     *
     * @param Time
     * The Time
     */
    public void setTime(Float Time) {
        this.Time = Time;
    }

    /**
     *
     * @return
     * The Total
     */
    public String getTotal() {
        return Total;
    }

    /**
     *
     * @param Total
     * The Total
     */
    public void setTotal(String Total) {
        this.Total = Total;
    }

    /**
     *
     * @return
     * The Page
     */
    public Integer getPage() {
        return Page;
    }

    /**
     *
     * @param Page
     * The Page
     */
    public void setPage(Integer Page) {
        this.Page = Page;
    }

    /**
     *
     * @return
     * The Books
     */
    public List<BookMeta> getBooks() {
        return Books;
    }

    /**
     *
     * @param Books
     * The Books
     */
    public void setBooks(List<BookMeta> Books) {
        this.Books = Books;
    }
}
