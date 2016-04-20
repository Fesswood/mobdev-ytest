package com.github.fesswood.yandextestapp.domain;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by fesswood on 20.04.16.
 */
public interface GroupDataProvider {
    Observable<List<MusicData>> getAllMessages(Scheduler scheduler);
}
