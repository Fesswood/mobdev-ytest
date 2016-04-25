package com.github.fesswood.yandextestapp.domain.musicGroup;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by fesswood on 20.04.16.
 */
public interface GroupDataRepository {
    Observable<List<MusicGroup>> getAllMusicGroup(Scheduler scheduler);

    void saveAllMusicGroup(List<MusicGroup> musicGroups);

    boolean isEmpty();
}
