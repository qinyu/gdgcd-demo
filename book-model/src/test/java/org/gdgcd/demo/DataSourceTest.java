package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookMetaEnvelope;
import org.gdgcd.demo.service.BookService;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import rx.Observable;
import rx.Observer;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.*;

public class DataSourceTest {

    DataSource dataSource;
    @Inject BookService bookService;

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
    }

    BookMeta proGAE, gmap2nd;
    BookMetaEnvelope ev = new BookMetaEnvelope();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testSubscriber = new TestSubscriber(observer);

        ObjectGraph objectGraph = ObjectGraph.create(new TestModule());
        objectGraph.inject(this);
        dataSource = objectGraph.get(DataSource.class);

        proGAE = new BookMeta();
        proGAE.setTitle("Programming Google App Engine");
        gmap2nd = new BookMeta();
        gmap2nd.setTitle("Google Maps API, 2nd Edition");
        gmap2nd.setDescription("");

        ev.setBooks(Arrays.asList(proGAE, gmap2nd));
        ev.setError("0");
    }

    @Test
    public void should_append_all_books_in_envelope() {
        given(bookService.getBooks(anyString())).willReturn(Observable.just(ev));

        dataSource.search("google").subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();

        verify(observer, times(2)).onNext(any(Book.class));
        assertEquals(2, testSubscriber.getOnNextEvents().size());
    }

    @Test
    public void should_contain_title_in_book_meta() {
        given(bookService.getBooks(anyString())).willReturn(Observable.just(ev));

        dataSource.search("google").subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();

        List<Book> books = testSubscriber.getOnNextEvents();
        assertEquals("Programming Google App Engine", books.get(0).title);
        assertEquals("Google Maps API, 2nd Edition", books.get(1).title);

    }
}