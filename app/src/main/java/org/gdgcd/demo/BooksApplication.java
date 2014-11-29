package org.gdgcd.demo;

import android.app.Application;

import java.util.Arrays;

import dagger.ObjectGraph;

/**
 * Created by twer on 11/26/14.
 */
public class BooksApplication extends Application {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new AndroidModule());
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
