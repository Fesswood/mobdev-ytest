package com.github.fesswood.yandextestapp.domain.MusicGroupDetailInfo;

import android.util.Log;

import com.github.fesswood.yandextestapp.domain.common.Interactor;
import com.github.fesswood.yandextestapp.domain.musicGroup.ClosableGroupDataRepository;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.presentation.inject.DataModule;
import com.github.fesswood.yandextestapp.presentation.inject.DomainModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by fesswood on 30.04.16.
 */
public class MusicGroupDetailInfoInteractor  extends Interactor<MusicGroup> {

    private ClosableGroupDetailRepository mRepository;

    @Inject
    public MusicGroupDetailInfoInteractor(@Named(DomainModule.JOB) Scheduler jobScheduler,
                                          @Named(DomainModule.UI) Scheduler uiScheduler,
                                          @Named(DataModule.DETAIL)
                                              ClosableGroupDetailRepository repository) {
        super(jobScheduler, uiScheduler);
        mRepository = repository;
    }

    @Override
    protected Observable<MusicGroup> prepareRequest() {
        mRepository.open();
        return mRepository.getMusicGroupInfo();
    }


    @Override
    public void unsubscribe() {
        super.unsubscribe();
        mRepository.close();
    }
}
