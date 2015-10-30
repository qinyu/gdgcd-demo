package org.gdgcd.demo.service;

import com.google.gson.annotations.Expose;

/**
 * Created by twer on 11/4/14.
 */
public class BookMeta {
    @Expose
    private String ID;
    @Expose
    private String Title;
    @Expose
    private String SubTitle;
    @Expose
    private String Description;
    @Expose
    private String Image;
    @Expose
    private String isbn;

    /**
     *
     * @return
     * The ID
     */
    public String getID() {
        return ID;
    }

    /**
     *
     * @param ID
     * The ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     *
     * @return
     * The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The SubTitle
     */
    public String getSubTitle() {
        return SubTitle;
    }

    /**
     *
     * @param SubTitle
     * The SubTitle
     */
    public void setSubTitle(String SubTitle) {
        this.SubTitle = SubTitle;
    }

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param Description
     * The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     *
     * @return
     * The Image
     */
    public String getImage() {
        return Image;
    }

    /**
     *
     * @param Image
     * The Image
     */
    public void setImage(String Image) {
        this.Image = Image;
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
}
