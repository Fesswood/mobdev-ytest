package com.github.fesswood.yandextestapp.presentation.detail.DetailInfo;

import android.util.Log;

import com.github.fesswood.yandextestapp.domain.MusicGroupDetailInfo.MusicGroupDetailInfoInteractor;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.presentation.common.BasePresenter;
import com.github.fesswood.yandextestapp.presentation.detail.DetailRouter;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by fesswood on 26.04.16.
 */
public class DetailInfoPresenter extends BasePresenter<DetailInfoView, DetailRouter> {


    private static final String TAG = DetailInfoPresenter.class.getSimpleName();
    private final MusicGroupDetailInfoInteractor mGroupListInteractor;
    private MusicGroup mMusicGroup;

    @Inject
    public DetailInfoPresenter(MusicGroupDetailInfoInteractor groupListInteractor) {
        this.mGroupListInteractor = groupListInteractor;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: subscribe for MusicGroup from interactor... ");
        mGroupListInteractor.executeRequest(new Subscriber<MusicGroup>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: " + "e = [" + e + "]");
            }

            @Override
            public void onNext(MusicGroup musicGroup) {
                Log.d(TAG, "onNext() called with: " + "musicGroup = [" + musicGroup + "]");
                mMusicGroup = musicGroup;
                getView().setDetailInfoViewModel(new MusicGroupDetailViewModel(musicGroup));
            }
        });
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: unsubscribe from interactor");
        mGroupListInteractor.unsubscribe();
    }

    public void routeToMusicGroupWebPage() {
        getRouter().routeToMusicGroupWebPage(mMusicGroup.getLink());
    }
}
