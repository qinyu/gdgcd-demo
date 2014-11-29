package org.gdgcd.demo.service;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface ReviewService {
    @GET("/review_counts.json")
    Observable<ReviewMetaEnvelope> getReview(@Query("isbns[]")String isbn);
}
