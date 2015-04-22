package org.gdgcd.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.module.kotlin.KotlinModule;

import org.gdgcd.demo.SearchEngine;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.JacksonConverter;

@Module(injects = {SearchEngine.class}, complete = false)
public class BookServiceModule {

    @Provides
    @Singleton
    BookService provideBookService(@Named("book service") String endpoint) {
        final ObjectMapper objectMapper = new ObjectMapper().registerModule(new KotlinModule())
                .setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);


        return new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new AndroidLog("page"))
                .setConverter(new JacksonConverter(objectMapper))
                .setEndpoint(endpoint).build().create(BookService.class);
    }
}
