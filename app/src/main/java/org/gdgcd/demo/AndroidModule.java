package org.gdgcd.demo;

import org.gdgcd.demo.service.BookServiceModule;
import org.gdgcd.demo.service.ReviewServiceModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ReviewServiceModule.class, BookServiceModule.class})
public class AndroidModule {

    @Provides
    @Named("book service")
    String provideBookServiceEndpoint() {
        return BuildConfig.BOOK_SERVICE_ENDPOINT;
    }

    @Provides
    @Named("review service")
    String provideReviewServiceEndpoint() {
        return BuildConfig.REVIEW_SERVICE_ENDPOINT;
    }
}
