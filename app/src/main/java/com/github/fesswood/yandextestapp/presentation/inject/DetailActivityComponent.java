package com.github.fesswood.yandextestapp.presentation.inject;

import com.github.fesswood.yandextestapp.presentation.detail.DetailInfo.DetailInfoFragment;
import com.github.fesswood.yandextestapp.presentation.main.musicGroupList.GroupListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fesswood on 20.04.16.
 */
@Singleton
@Component(modules = {DomainModule.class, DataModule.class})
public interface DetailActivityComponent {

    void inject(DetailInfoFragment groupListFragment);


}