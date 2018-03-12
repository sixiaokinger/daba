package com.longke.shot;

import android.app.Application;

/**
 * Created by longke on 2018/2/4.
 */

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        UUIDS.buidleID(this).check();
    }
}
