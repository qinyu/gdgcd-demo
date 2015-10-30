package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookMetaEnvelope;
import org.gdgcd.demo.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import rx.Observable;
import rx.Observer;
import rx.observers.TestSubscriber;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.*;

public class SearchEngineTest {

    SearchEngine searchEngine;
    @Inject BookService bookService;

    @Mock
    private Observer<Book> observer;
    private TestSubscriber<Book> testSubscriber;

    @Module(injects = {SearchEngine.class, SearchEngineTest.class}, overrides = true)
    static class TestModule {
        @Provides
        @Singleton
        BookService provideBookService() {
            return mock(BookService.class);
        }
    }

    BookMeta proGAE, gmap2nd;
    BookMetaEnvelope bookMetaEnvelope;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        testSubscriber = new TestSubscriber(observer);

        ObjectGraph objectGraph = ObjectGraph.create(new TestModule());
        objectGraph.inject(this);
        searchEngine = objectGraph.get(SearchEngine.class);

        proGAE = new BookMeta();
        proGAE.setTitle("Programming Google App Engine");
        gmap2nd = new BookMeta();
        gmap2nd.setTitle("Google Maps API, 2nd Edition");
        gmap2nd.setDescription("");

        bookMetaEnvelope = new BookMetaEnvelope();
        bookMetaEnvelope.setBooks(Arrays.asList(proGAE, gmap2nd));
        bookMetaEnvelope.setError("0");
    }

    @Test
    public void should_append_all_books_in_envelope() {
        given(bookService.getBooks(anyString())).willReturn(Observable.just(bookMetaEnvelope));

        searchEngine.search("google").subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();

        verify(observer, times(2)).onNext(any(Book.class));
        assertThat(testSubscriber.getOnNextEvents().size(), is(2));
    }
}