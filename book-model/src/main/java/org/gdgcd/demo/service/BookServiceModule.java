package org.gdgcd.demo.service;

import org.gdgcd.demo.SearchEngine;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

@Module(injects = {SearchEngine.class}, complete = false)
public class BookServiceModule {

    @Provides
    @Singleton
    BookService provideBookService(@Named("book service") String endpoint) {

        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("page"))
                .setEndpoint(endpoint).build().create(BookService.class);

    }
}
