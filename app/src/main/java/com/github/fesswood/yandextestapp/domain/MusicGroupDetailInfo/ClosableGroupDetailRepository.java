package com.github.fesswood.yandextestapp.domain.MusicGroupDetailInfo;

/**
 * Created by fesswood on 30.04.16.
 */
public interface ClosableGroupDetailRepository extends GroupDetailRepository {
    void open();
    void close();
}
