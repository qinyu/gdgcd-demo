package org.gdgcd.demo.viewmodel;

import android.support.annotation.NonNull;

import org.gdgcd.demo.SearchEngine;
import org.gdgcd.demo.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;


public class BooksViewModelTest {


    private TestScheduler testScheduler;
    private Observable<Book> googleSearchResult;
    private TestSubscriber<Observable<Book>> testSubscriber;

    @Before
    public void setUp() throws Exception {
        testScheduler = new TestScheduler();
        googleSearchResult = Observable.just(new Book());
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void should_trigger_searching_only_if_user_finishes_typing() {

        BooksViewModel vm = new BooksViewModel(mockSearEngine(), testScheduler);
        vm.getBooksObservable().subscribe(testSubscriber);

        vm.submitKeyword("g");
        vm.submitKeyword("go");
        vm.submitKeyword("goo");
        vm.submitKeyword("goog");
        vm.submitKeyword("googl");
        vm.submitKeyword("google");

        testScheduler.advanceTimeBy(1500, TimeUnit.MILLISECONDS);

        testSubscriber.assertReceivedOnNext(Arrays.asList(googleSearchResult));
    }

    @Test
    public void should_trigger_searching_only_if_user_typing_different_keywords() {

        BooksViewModel vm = new BooksViewModel(mockSearEngine(), testScheduler);
        vm.getBooksObservable().subscribe(testSubscriber);

        vm.submitKeyword("googl");
        vm.submitKeyword("google");
        testScheduler.advanceTimeBy(1500, TimeUnit.MILLISECONDS);
        vm.submitKeyword("googl");
        vm.submitKeyword("google");
        testScheduler.advanceTimeBy(1500, TimeUnit.MILLISECONDS);
        vm.submitKeyword("googl");
        vm.submitKeyword("google");
        testScheduler.advanceTimeBy(1500, TimeUnit.MILLISECONDS);

        testSubscriber.assertReceivedOnNext(Arrays.asList(googleSearchResult));
    }

    @NonNull
    SearchEngine mockSearEngine() {
        SearchEngine engine = mock(SearchEngine.class);

        given(engine.search(anyString())).willReturn(Matchers.<Observable<Book>>anyObject());
        given(engine.search("google")).willReturn(googleSearchResult);
        return engine;
    }


}