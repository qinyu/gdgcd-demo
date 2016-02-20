package org.gdgcd.demo.viewmodel;

import com.google.common.annotations.VisibleForTesting;

import org.gdgcd.demo.SearchEngine;
import org.gdgcd.demo.domain.Book;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class BooksViewModel {
    private PublishSubject<String> keywordSubject = PublishSubject.create();
    private final SearchEngine searchEngine;
    private Scheduler scheduler;

    public BooksViewModel(SearchEngine searchEngine) {
        this(searchEngine, Schedulers.computation());
    }

    @VisibleForTesting
    BooksViewModel(SearchEngine searchEngine, Scheduler scheduler) {
        this.searchEngine = searchEngine;
        this.scheduler = scheduler;
    }

    public Observable<Observable<Book>> getBooksObservable() {
        return keywordSubject
                .debounce(1000, TimeUnit.MILLISECONDS, scheduler)
                .distinctUntilChanged()
                .map(new Func1<String, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(String keyword) {
                        return searchEngine.search(keyword);
                    }
                });
    }

    public void submitKeyword(String query) {
        keywordSubject.onNext(query);
    }
}
