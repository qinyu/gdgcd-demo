package org.gdgcd.demo.domain;

import rx.Observable;

public class Book {

    public String title;
    public String description;

    public String imageUrl;

    public Observable<String> rating;

}
