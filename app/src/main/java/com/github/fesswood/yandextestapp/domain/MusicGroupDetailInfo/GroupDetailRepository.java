package com.github.fesswood.yandextestapp.domain.MusicGroupDetailInfo;

import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import rx.Observable;

/**
 * Created by fesswood on 30.04.16.
 */
public interface GroupDetailRepository {

    Observable<MusicGroup> getMusicGroupInfo();
    Observable<MusicGroup> changeCurrentGroupById(int id);
}
