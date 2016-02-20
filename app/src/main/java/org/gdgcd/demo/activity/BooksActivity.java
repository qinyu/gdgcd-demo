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
import org.gdgcd.demo.viewmodel.BooksViewModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class BooksActivity extends AppCompatActivity {

    private BooksViewModel booksViewModel;
    @InjectView(android.R.id.list)
    RecyclerView booksListView;

    private BooksAdapter booksListAdapter;
    private Subscription booksSubscription;
    private Subscription querySubscription;

    @InjectView(R.id.search)
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
        booksViewModel = new BooksViewModel(((BooksApplication) getApplication()).getObjectGraph().get(SearchEngine.class));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        bindSearchView();
        bindSearchResult();
    }

    private void bindSearchResult() {
        querySubscription = booksViewModel.getBooksObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Observable<Book>>() {
                    @Override
                    public void call(Observable<Book> bookObservable) {
                        loadBooks(bookObservable);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("Books", "search error", throwable);
                    }
                });

    }

    private void bindSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                booksViewModel.submitKeyword(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                booksViewModel.submitKeyword(newText);
                return true;
            }
        });
    }

    void loadBooks(Observable<Book> bookObservable) {
        booksListAdapter = new BooksAdapter();
        booksListView.setAdapter(booksListAdapter);

        if (booksSubscription != null) {
            booksSubscription.unsubscribe();
        }
        booksSubscription = bookObservable
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
                                Log.e("Books", "search error", throwable);
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
