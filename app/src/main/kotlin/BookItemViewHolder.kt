package org.gdgcd.demo

import butterknife.bindView
import org.gdgcd.demo
import rx.Observable
import rx.Subscription

public class BookItemViewHolder(itemView: android.view.View) : android.support.v7.widget.RecyclerView.ViewHolder(itemView) {
    public val coverImage: android.widget.ImageView by bindView(demo.R.id.cover)
    public val title: android.widget.TextView by bindView(demo.R.id.title)
    public val rating: android.widget.TextView  by bindView(demo.R.id.rating)
    public val context: android.content.Context = itemView.getContext()
    public val ratingSub: Subscription = Observable.empty<Any>().subscribe()
}