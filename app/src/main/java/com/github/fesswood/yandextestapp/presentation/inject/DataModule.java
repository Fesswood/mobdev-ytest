package com.github.fesswood.yandextestapp.presentation.inject;

import com.github.fesswood.yandextestapp.data.repository.MusicGroupDataRepositoryImpl;
import com.github.fesswood.yandextestapp.data.repository.MusicGroupDetailInfoRepositoryImpl;
import com.github.fesswood.yandextestapp.domain.MusicGroupDetailInfo.ClosableGroupDetailRepository;
import com.github.fesswood.yandextestapp.domain.musicGroup.ClosableGroupDataRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fesswood on 20.04.16.
 */
@Module
public class DataModule {

    public static final String REPOSITORY = "repository";
    public static final String COMMON = "COMMON";
    public static final String DETAIL = "DETAIL";
    private final int mGroupId;

    public DataModule() {
        mGroupId = 0;
    }

    public DataModule(int groupId) {
            mGroupId = groupId;
    }


    @Singleton
    @Provides
    @Named(COMMON)
    public ClosableGroupDataRepository provideMusicGroupListProvider() {
        return new MusicGroupDataRepositoryImpl();
    }

    @Singleton
    @Provides
    @Named(DETAIL)
    public ClosableGroupDetailRepository provideMusicGroupDetailInfoProvider() {
        return new MusicGroupDetailInfoRepositoryImpl(mGroupId);
    }
}
