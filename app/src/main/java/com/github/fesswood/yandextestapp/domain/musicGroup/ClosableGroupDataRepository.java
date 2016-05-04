package com.github.fesswood.yandextestapp.domain.musicGroup;

/**
 * Created by fesswood on 20.04.16.
 */
public interface ClosableGroupDataRepository extends GroupDataRepository {
    void open();
    void close();
}
