package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookMetaEnvelope;
import org.gdgcd.demo.service.BookService;
import org.gdgcd.demo.service.ReviewService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import rx.Observable;
import rx.Observer;
import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.*;

public class DataSourceTest {

    DataSource dataSource;
    @Inject BookService bookService;
    @Inject ReviewService reviewService;

    @Mock
    private Observer<Book> observer;
    private TestSubscriber<Book> testSubscriber;

    @Module(injects = {DataSource.class, DataSourceTest.class}, overrides = true)
    static class TestModule {
        @Provides
        @Singleton
        BookService provideBookService() {
            return mock(BookService.class);
        }

        @Provides
        @Singleton
        ReviewService provideReviewService() {
            return mock(ReviewService.class);
        }
    }

    BookMeta test = new BookMeta();
    BookMetaEnvelope ev = new BookMetaEnvelope();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testSubscriber = new TestSubscriber(observer);

        ObjectGraph objectGraph = ObjectGraph.create(new TestModule());
        objectGraph.inject(this);
        dataSource = objectGraph.get(DataSource.class);
    }

    @Test
    public void should_contain_title_from_book_meta() {
        given(bookService.getBooks(anyString())).willReturn(Observable.just(ev));

        dataSource.search("test").subscribe(testSubscriber);

        verify(observer, never()).onNext(any());
    }
}