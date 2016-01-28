package org.gdgcd.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.gdgcd.demo.BooksAdapter;
import org.gdgcd.demo.BooksApplication;
import org.gdgcd.demo.R;
import org.gdgcd.demo.SearchEngine;
import org.gdgcd.demo.domain.Book;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;


public class BooksActivity extends AppCompatActivity {

    @InjectView(android.R.id.list)
    RecyclerView booksListView;

    private BooksAdapter booksListAdapter;
    private Subscription booksSubscription;
    private Subscription querySubscription;

    @InjectView(R.id.search)
    SearchView searchView;
    private PublishSubject<String> subject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("qinyu", "onQueryTextSubmit " + query);

                subject.onNext(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("qinyu", "onQueryTextChange " + newText);

                subject.onNext(newText);
                return true;
            }
        });

        final SearchEngine searchEngine = ((BooksApplication) getApplication()).getObjectGraph().get(SearchEngine.class);

        querySubscription = subject.debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        loadBooks(s, searchEngine);
                    }
                });
    }

    void loadBooks(String query, SearchEngine searchEngine) {
        if (booksSubscription != null) {
            booksSubscription.unsubscribe();
        }
        booksListAdapter = new BooksAdapter();
        booksListView.setAdapter(booksListAdapter);
        booksSubscription = searchEngine.search(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<Book>() {
                            @Override
                            public void call(Book book) {
                                booksListAdapter.append(book);
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        }
                );
    }


    private void setUpUI() {
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        booksListView.setHasFixedSize(true);
        booksListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Picasso.with(this).pauseTag(this);
        if (booksSubscription != null)
            booksSubscription.unsubscribe();
        if (querySubscription != null)
            querySubscription.unsubscribe();
    }
}
