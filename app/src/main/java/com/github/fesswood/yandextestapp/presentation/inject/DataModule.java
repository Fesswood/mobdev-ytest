package com.github.fesswood.yandextestapp.presentation.inject;

import com.github.fesswood.yandextestapp.data.repository.MusicGroupDataRepositoryImpl;
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

    @Singleton
    @Provides
    @Named(REPOSITORY)
    public ClosableGroupDataRepository provideMusicGroupDataProvider() {
        return new MusicGroupDataRepositoryImpl();
    }
}
