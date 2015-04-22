package org.gdgcd.demo;

import android.util.Log;

import org.gdgcd.demo.service.Page;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.subjects.PublishSubject;

public abstract class Paginated<T> {

    private PublishSubject<Integer> subject;
    private int page;
    private ConnectableObservable<T> replay;

    public Paginated() {
        subject = PublishSubject.create();
        total = Integer.MAX_VALUE;

        replay = subject
                .map((rx.functions.Func1<Integer, Observable<? extends Page<T>>>) new Func1<Integer, Observable<? extends Page<T>>>() {
                    @Override
                    public Observable<? extends Page<T>> call(Integer t1) {
                        return Paginated.this.getPageObservable(t1);
                    }
                })
                .concatMap(new Func1<Observable<? extends Page<T>>, Observable<? extends T>>() {
                    @Override
                    public Observable<? extends T> call(Observable<? extends Page<T>> pageEnvelopeObservable) {
                        return pageEnvelopeObservable.flatMap(new Func1<Page<T>, Observable<? extends T>>() {
                            @Override
                            public Observable<? extends T> call(Page<T> tPageEnvelope) {
                                Log.d("page", "flatMap: start:" + loaded);
                                List<T> hotSpotsList = tPageEnvelope.getContentList();
                                loaded += hotSpotsList.size();
                                Log.d("page", "flatMap: end:" + loaded);
                                return Observable.from(hotSpotsList);
                            }
                        });
                    }
                }).replay();
        replay.connect();
    }

    protected int total;
    protected int loaded;

    public Observable<T> getContentObservable() {
        return replay;
    }

    protected abstract Observable<? extends Page<T>> getPageObservable(int page);

    public boolean nextPage() {
        if (loaded < total) {
            Log.d("page", "next: start:" + page);
            subject.onNext(page++);
            Log.d("page", "next: end:" + page);
            return true;
        }
        return false;
    }
}
