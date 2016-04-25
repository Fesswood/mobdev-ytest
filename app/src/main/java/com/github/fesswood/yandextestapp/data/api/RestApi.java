package com.github.fesswood.yandextestapp.data.api;

import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by fesswood on 24.04.16.
 */
public interface RestApi {

    abstract Observable<List<MusicGroup>> getMusicList();
}
