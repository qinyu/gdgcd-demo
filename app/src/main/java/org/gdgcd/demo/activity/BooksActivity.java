package org.gdgcd.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.gdgcd.demo.BooksAdapter;
import org.gdgcd.demo.BooksApplication;
import org.gdgcd.demo.R;
import org.gdgcd.demo.SearchEngine;
import org.gdgcd.demo.domain.Book;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class BooksActivity extends AppCompatActivity {

    @InjectView(android.R.id.list)
    RecyclerView booksListView;

    private BooksAdapter booksListAdapter;
    private Subscription booksSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
        loadBooks();
    }

    void loadBooks() {
        final SearchEngine searchEngine = ((BooksApplication) getApplication()).getObjectGraph().get(SearchEngine.class);
        booksSubscription = searchEngine.search("google")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<Book>() {
                            @Override
                            public void call(Book book) {
                                booksListAdapter.append(book);
                            }
                        }
                );
    }


    private void setUpUI() {
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);

        booksListView.setHasFixedSize(true);
        booksListView.setLayoutManager(new LinearLayoutManager(this));

        booksListAdapter = new BooksAdapter();
        booksListView.setAdapter(booksListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Picasso.with(this).pauseTag(this);
        booksSubscription.unsubscribe();
    }
}
