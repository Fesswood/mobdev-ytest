package com.github.fesswood.yandextestapp.data;

import com.github.fesswood.yandextestapp.domain.GroupDataProvider;
import com.github.fesswood.yandextestapp.domain.MusicData;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by fesswood on 20.04.16.
 */
public class MusicGroupDataProviderImpl implements GroupDataProvider {
    @Override
    public Observable<List<MusicData>> getAllMessages(Scheduler scheduler) {
        return Observable.empty();
    }
}
