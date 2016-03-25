package org.gdgcd.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.gdgcd.demo.domain.Book;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BookViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.cover)
    ImageView coverImage;
    @InjectView(R.id.title)
    TextView title;

    Context context;

    public BookViewHolder(View itemView) {
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
