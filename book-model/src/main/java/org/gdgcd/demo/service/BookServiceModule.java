package org.gdgcd.demo.service;

import org.gdgcd.demo.SearchEngine;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module(injects = {SearchEngine.class}, complete = false)
public class BookServiceModule {

    @Provides
    @Singleton
    BookService provideBookService(@Named("book service") String endpoint) {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(endpoint)
                .build()
                .create(BookService.class);
    }
}
