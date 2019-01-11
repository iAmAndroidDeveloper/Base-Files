package com.example.bhavesh.taskmanager.base;

import android.app.Application;
import android.content.Context;

import com.evernote.android.job.JobManager;
import com.example.bhavesh.taskmanager.Job.DemoJobCreator;
import com.example.bhavesh.taskmanager.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Admin on 07-02-2018.
 */

public class ProApplication extends Application {

    private static ProApplication instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static ProApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SF-UI-Text-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Realm.init(this);
        JobManager.create(this).addJobCreator(new DemoJobCreator());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

    }
}

