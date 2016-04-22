package com.github.fesswood.yandextestapp.presentation.inject;

import com.github.fesswood.yandextestapp.data.MusicGroupDataProviderImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fesswood on 20.04.16.
 */
@Module
public class DataModule {

    @Singleton
    @Provides
    public MusicGroupDataProviderImpl provideMusicGroupDataProvider() {
        return new MusicGroupDataProviderImpl();
    }
}
