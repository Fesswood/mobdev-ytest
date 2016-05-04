package com.github.fesswood.yandextestapp.data.repository;

import android.util.Log;

import com.github.fesswood.yandextestapp.data.repository.common.CommonOperation;
import com.github.fesswood.yandextestapp.data.repository.common.MusicGroupData;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.domain.musicGroup.ClosableGroupDataRepository;

import java.util.List;

import io.realm.Realm;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by fesswood on 20.04.16.
 */
public class MusicGroupDataRepositoryImpl implements ClosableGroupDataRepository {


    private static final String TAG = MusicGroupDataRepositoryImpl.class.getSimpleName();
    private Realm mRealm;

    public MusicGroupDataRepositoryImpl() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<List<MusicGroup>> getAllMusicGroup(Scheduler scheduler) {
        List<MusicGroupData> musicGroupDatas = mRealm.copyFromRealm(mRealm
                .where(MusicGroupData.class).findAll());
      return   Observable.just(musicGroupDatas)
                .flatMap(Observable::from)
                .map(CommonOperation::createMusicGroup)
                .toList().asObservable();
    }

    @Override
    public void saveAllMusicGroup(List<MusicGroup> musicGroups) {
        mRealm.executeTransaction(realm -> Observable.just(musicGroups)
                .flatMap(Observable::from)
                .map(CommonOperation::createMusicGroupData)
                .toList()
                .subscribe(realm::copyToRealmOrUpdate));
    }

    @Override
    public boolean isEmpty() {
        return mRealm.where(MusicGroupData.class).count() == 0;
    }


    @Override
    public void open() {
        if (mRealm == null || mRealm.isClosed()) {
            Log.d(TAG, "open: restore realm instance");
            mRealm = Realm.getDefaultInstance();
        }
    }

    @Override
    public void close() {
        mRealm.close();
    }
}
