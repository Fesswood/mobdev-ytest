package com.github.fesswood.yandextestapp.domain.musicGroup;

import android.util.Log;

import com.github.fesswood.yandextestapp.data.api.RestApi;
import com.github.fesswood.yandextestapp.data.api.RestApiImpl;
import com.github.fesswood.yandextestapp.domain.common.Interactor;
import com.github.fesswood.yandextestapp.presentation.inject.DataModule;
import com.github.fesswood.yandextestapp.presentation.inject.DomainModule;

import java.util.List;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by fesswood on 24.04.16.
 */
public class MusicGroupInteractor extends Interactor<List<MusicGroup>> {


    private static final String TAG = MusicGroupInteractor.class.getSimpleName();
    private final RestApi mRestApi;
    private final ClosableGroupDataRepository mGroupDataRepository;

    public MusicGroupInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler,
                                @Named(DomainModule.UI) Scheduler uiScheduler,
                                @Named(DataModule.REPOSITORY)
                                ClosableGroupDataRepository repository) {
        super(jobScheduler, uiScheduler);
        mRestApi = new RestApiImpl();
        mGroupDataRepository = repository;
    }

    @Override
    public void executeRequest(Subscriber<List<MusicGroup>> subscriber) {
        Log.d(TAG, "executeRequest: Is db has music groups?");
        if(mGroupDataRepository.isEmpty()){
            Log.d(TAG, "executeRequest: No. sends request...");
            super.executeRequest(new Subscriber<List<MusicGroup>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    subscriber.onError(e);
                }

                @Override
                public void onNext(List<MusicGroup> musicGroupRest) {
                    mGroupDataRepository.saveAllMusicGroup(musicGroupRest);
                    mGroupDataRepository.getAllMusicGroup(jobScheduler).subscribe(subscriber);
                }
            });
        }else {
            Log.d(TAG, "executeRequest: Yes. getting from db...");
            mGroupDataRepository.getAllMusicGroup(jobScheduler).subscribe(subscriber);
        }
    }

    @Override
    public void executeRequest(Action1<List<MusicGroup>> subscriber) {
        Log.d(TAG, "executeRequest: Is db has music groups?");
        if(mGroupDataRepository.isEmpty()){
            Log.d(TAG, "executeRequest: No. sends request...");
            super.executeRequest(musicGroupRest -> {
                mGroupDataRepository.saveAllMusicGroup(musicGroupRest);
                mGroupDataRepository.getAllMusicGroup(jobScheduler).subscribe(subscriber);
            });
        }else {
            Log.d(TAG, "executeRequest: Yes. getting from db...");
            mGroupDataRepository.getAllMusicGroup(jobScheduler).subscribe(subscriber);
        }
    }

    @Override
    protected Observable<List<MusicGroup>> prepareRequest() {
        return mRestApi.getMusicList();
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        mGroupDataRepository.close();
    }
}
