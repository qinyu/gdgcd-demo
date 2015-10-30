package org.gdgcd.demo.module;

import org.gdgcd.demo.BuildConfig;
import org.gdgcd.demo.service.BookServiceModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {BookServiceModule.class})
public class AndroidModule {

    @Provides
    @Named("book service")
    String provideBookServiceEndpoint() {
        return BuildConfig.BOOK_SERVICE_ENDPOINT;
    }

}
