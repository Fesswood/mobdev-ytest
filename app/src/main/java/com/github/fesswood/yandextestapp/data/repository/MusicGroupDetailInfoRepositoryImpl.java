package com.github.fesswood.yandextestapp.data.repository;

import android.util.Log;

import com.github.fesswood.yandextestapp.data.repository.common.CommonOperation;
import com.github.fesswood.yandextestapp.data.repository.common.MusicGroupData;
import com.github.fesswood.yandextestapp.domain.MusicGroupDetailInfo.ClosableGroupDetailRepository;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by fesswood on 20.04.16.
 */
public class MusicGroupDetailInfoRepositoryImpl implements ClosableGroupDetailRepository {


    private static final String TAG = MusicGroupDetailInfoRepositoryImpl.class.getSimpleName();
    private Realm mRealm;
    private int mMGroupId;

    public MusicGroupDetailInfoRepositoryImpl(int mGroupId) {
        mMGroupId = mGroupId;
        mRealm = Realm.getDefaultInstance();
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

    @Override
    public Observable<MusicGroup> getMusicGroupInfo() {
         RealmResults<MusicGroupData> musicGroupData =
                mRealm.where(MusicGroupData.class).equalTo("id", mMGroupId).findAll();
        return   Observable.just(mRealm.copyFromRealm(musicGroupData))
                .flatMap(Observable::from)
                .map(CommonOperation::createMusicGroup)
                .asObservable();
    }

    @Override
    public Observable<MusicGroup> changeCurrentGroupById(int id) {
        mMGroupId = id;
        return getMusicGroupInfo();
    }
}
