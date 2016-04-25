package com.github.fesswood.yandextestapp.app;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * В App будем инициализировать базу данных Realm и делать миграции
 * Created by fesswood on 21.04.16.
 */
public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        Fresco.initialize(this);
    }

    private void initRealm() {
        Log.d(TAG, "initRealm() starts");
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder(getApplicationContext());
        builder.deleteRealmIfMigrationNeeded();
        builder.rxFactory(new RealmObservableFactory());
        RealmConfiguration defaultConfiguration = builder.build();
        Realm.setDefaultConfiguration(defaultConfiguration);
        Realm.deleteRealm(defaultConfiguration);
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();

        } catch (IllegalArgumentException exception) {
            Log.e(TAG, "Can't migrate realm, deleting realm base....  Cause: ", exception);
            Realm.deleteRealm(defaultConfiguration);
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }
}
