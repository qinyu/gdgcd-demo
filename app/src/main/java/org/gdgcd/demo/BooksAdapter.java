package org.gdgcd.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.gdgcd.demo.domain.Book;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BookViewHolder> {
    public BooksAdapter() {
        books = new ArrayList<>();
    }

    private List<Book> books;

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new BookViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder viewHolder, int position) {
        viewHolder.updateView(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
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
