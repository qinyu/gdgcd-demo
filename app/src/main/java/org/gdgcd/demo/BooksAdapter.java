package org.gdgcd.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.gdgcd.demo.domain.Book;

import com.google.common.annotations.VisibleForTesting;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
        viewHolder.updateView(books.get(position));
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

        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            context = itemView.getContext();
        }

        void updateView(Book book) {
            Picasso.with(context)
                    .load(book.imageUrl)
                    .into(coverImage);
            title.setText(book.title);
        }
    }

    public void append(Book book) {
        int position = this.books.size();
        this.books.add(book);
        notifyInsert(position);
    }

    @VisibleForTesting
    public void notifyInsert(int position) {
        notifyItemInserted(position);
    }

}
