package org.gdgcd.demo;


import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import java.io.File;

/**
 * Created by yqin on 3/23/16.
 */
public class GdgcdRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        super.testRunStarted(description);
        System.out.print("Started!");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        System.out.print("Ended!");
    }

}
