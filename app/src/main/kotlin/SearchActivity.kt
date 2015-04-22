package org.gdgcd.demo

import android.graphics.Rect
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.ActionBarActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.View
import butterknife.bindView
import org.gdgcd.demo.BooksAdapter
import org.gdgcd.demo.BooksApplication
import org.gdgcd.demo.SearchEngine
import org.gdgcd.demo.R
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import java.util.concurrent.TimeUnit

public class SearchActivity : ActionBarActivity() {
    val searchView: SearchView by bindView(R.id.search)
    val recyclerView: RecyclerView by bindView(android.R.id.list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                outRect?.set(20, 20, 20, 20)
            }
        })
    }

    val subject: PublishSubject<String> = PublishSubject.create()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                subject.onNext(p0)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                subject.onNext(p0)
                return true
            }

        })
        val adapter = BooksAdapter()
        recyclerView.setAdapter(adapter)
        val engine = (getApplication() as BooksApplication).getObjectGraph().get<SearchEngine>(javaClass<SearchEngine>())

        subject.debounce(1000, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .map(engine.search)
                .su
                .subscribe {
                    str ->
                    Log.d("OpenParty", "str:${str}")


                    engine.search(str).subscribe {
                        book ->
                        adapter.append(book)
                    }
                }
    }


}
