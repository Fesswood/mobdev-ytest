package com.github.fesswood.yandextestapp.domain.musicGroup;

import com.github.fesswood.yandextestapp.data.repository.MusicGroupData;

import java.util.List;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by fesswood on 20.04.16.
 */
public interface ClosableGroupDataRepository extends GroupDataRepository {

    void close();


}
