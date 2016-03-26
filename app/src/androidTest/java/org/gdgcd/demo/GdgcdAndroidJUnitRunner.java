package org.gdgcd.demo;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;

/**
 * Created by yqin on 3/25/16.
 */
public class GdgcdAndroidJUnitRunner extends AndroidJUnitRunner {

    @Override
    public void onCreate(Bundle arguments) {
        arguments.putString("listener", "org.gdgcd.demo.GdgcdRunListener");
        super.onCreate(arguments);
    }
}
