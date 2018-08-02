package main.testapp.core;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Andriy Chopovenko on 02.08.2018.
 */
public class RealmApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("realm-note.db")
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
