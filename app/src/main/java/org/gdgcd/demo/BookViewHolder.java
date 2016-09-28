package org.gdgcd.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.secondactivity.SecondActivity;
import com.ionicframework.starter.MainActivity;
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

    public BookViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
        org.apache.cordova.CallbackContext c;
        context = itemView.getContext();
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext().getApplicationContext(), MainActivity.class));
            }
        });
    }

    void updateView(Book book) {
        Picasso.with(context)
                .load(book.imageUrl)
                .into(coverImage);
        title.setText(book.title);
    }
}
