package org.gdgcd.demo.service;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface BookService {
    @GET("/search/{query}")
    public Observable<BookMetaEnvelope> getBooks(@Path("query") String query);
}

