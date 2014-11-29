package org.gdgcd.demo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.ObjectGraph;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class BooksActivity extends ActionBarActivity {

    @InjectView(R.id.book_list)
    RecyclerView recyclerView;

    @InjectView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private BooksAdapter adapter;
    private Subscription subscription;


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

    void load() {
        DataSource dataSource = ((BooksApplication)getApplication()).getObjectGraph().get(DataSource.class);

        swipeRefreshLayout.setRefreshing(true);
        subscription = dataSource.search("google")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        book -> {
                            adapter.append(book);
                            endRefreshingAnimation();
                        },
                        ex -> {
                            ex.printStackTrace();
                            endRefreshingAnimation();
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
