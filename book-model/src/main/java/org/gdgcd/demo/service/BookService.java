package org.gdgcd.demo.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface BookService {
    @GET("search/{query}")
    Observable<BookMetaEnvelope> getBooks(@Path("query") String query);
}

