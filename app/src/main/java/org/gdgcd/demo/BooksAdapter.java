package org.gdgcd.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.gdgcd.demo.domain.Book;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    public BooksAdapter() {
        books = new ArrayList<>();
    }

    private List<Book> books;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Book book = books.get(position);
        Picasso.with(viewHolder.context)
                .load(book.imageUrl)
                .tag(viewHolder.context)
                .into(viewHolder.coverImage);
        viewHolder.title.setText(book.title);

        viewHolder.ratingSub.unsubscribe();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cover)
        ImageView coverImage;
        @InjectView(R.id.title)
        TextView title;

        @InjectView(R.id.rating)
        TextView rating;

        Context context;

        Subscription ratingSub = Observable.empty().subscribe();

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            context = itemView.getContext();
        }
    }

    public void append(Book book) {
        int position = this.books.size();
        this.books.add(book);
        notifyItemInserted(position);
    }

}
