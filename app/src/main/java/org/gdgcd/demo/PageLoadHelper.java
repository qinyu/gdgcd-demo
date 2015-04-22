package org.gdgcd.demo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class PageLoadHelper {

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public PageLoadHelper(int visibleThreshold) {
        this.visibleThreshold = visibleThreshold;
    }

    public void loadNextPageIfNeccessary(RecyclerView recyclerView, Paginated<?> page, RecyclerView.LayoutManager layoutManager) {
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            Log.i("page", "end called");
            // Do something
            loading = page.nextPage();
        }
    }
}
