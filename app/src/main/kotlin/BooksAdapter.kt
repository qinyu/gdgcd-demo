package org.gdgcd.demo

import com.squareup.picasso
import com.squareup.picasso.Picasso
import org.gdgcd.demo
import org.gdgcd.demo.domain.Book
import java.util.ArrayList

public class BooksAdapter : android.support.v7.widget.RecyclerView.Adapter<BookItemViewHolder>() {

    private val books: MutableList<Book> = ArrayList()

    override fun onCreateViewHolder(viewGroup: android.view.ViewGroup, i: Int): BookItemViewHolder {
        return BookItemViewHolder(android.view.LayoutInflater.from(viewGroup.getContext()).inflate(demo.R.layout.book_card, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: BookItemViewHolder, i: Int) {
        val book = books.get(i)
        Picasso.with(viewHolder.context)
                .load(book.imageUrl)
                .tag(viewHolder.context).into(viewHolder.coverImage)
        viewHolder.title.setText(book.title)

        viewHolder.ratingSub.unsubscribe()
    }

    override fun getItemCount(): Int {
        return books.size()
    }

    public fun append(book: Book) {
        val position = this.books.size()
        this.books.add(book)
        notifyItemInserted(position)
    }

}