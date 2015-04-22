package org.gdgcd.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookMetaEnvelope;
import org.gdgcd.demo.service.Page;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class BooksActivity extends ActionBarActivity {

    @InjectView(R.id.book_list)
    RecyclerView recyclerView;

    @InjectView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private BooksAdapter adapter;
    private Subscription subscription;
    private Books books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ButterKnife.inject(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BooksAdapter();
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);


        load();
    }

    class Books extends Paginated<BookMeta> {
        Books(SearchEngine searchEngine) {
            this.searchEngine = searchEngine;
        }

        SearchEngine searchEngine;

        @Override
        protected Observable<? extends Page<BookMeta>> getPageObservable(int page) {
            Log.d("page", "getPageObservable: start:" + page);
            Observable<BookMetaEnvelope> google = searchEngine.search1("google");
            Log.d("page", "getPageObservable: end:" + page);
            return google;
        }
    }

    void load() {
        final SearchEngine searchEngine = ((BooksApplication) getApplication()).getObjectGraph().get(SearchEngine.class);

        swipeRefreshLayout.setRefreshing(true);
//        books = new Books(searchEngine);
//        books.nextPage();
//        subscription = books.getContentObservable().map(new Func1<BookMeta, Book>() {
//            @Override
//            public Book call(BookMeta bookMeta) {
//                return SearchEngine.buildBook(bookMeta);
//            }
//        })
        searchEngine.search("google")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<Book>() {
                               @Override
                               public void call(Book book) {
                                   adapter.append(book);
                                   endRefreshingAnimation();
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                throwable.printStackTrace();
                                endRefreshingAnimation();
                            }
                        });



        final PageLoadHelper pageLoadHelper = new PageLoadHelper(5);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                pageLoadHelper.loadNextPageIfNeccessary(recyclerView, books, recyclerView.getLayoutManager());
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void endRefreshingAnimation() {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Picasso.with(this).pauseTag(this);
        subscription.unsubscribe();
    }
}
