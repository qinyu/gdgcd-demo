package org.gdgcd.demo.service;

import org.gdgcd.demo.DataSource;
import org.gdgcd.demo.service.BookService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.RestAdapter;

@Module (injects= {DataSource.class}, complete = false)
public class BookServiceModule {

    @Provides @Singleton
    BookService provideBookService(@Named("book service") String endpoint) {
        return new RestAdapter.Builder().setEndpoint(endpoint).build().create(BookService.class);
    }
}
