package com.github.fesswood.yandextestapp.presentation.inject;

import javax.inject.Singleton;

import com.github.fesswood.yandextestapp.presentation.main.musicGroupList.GroupListFragment;
import dagger.Component;

/**
 * Created by fesswood on 20.04.16.
 */
@Singleton
@Component(modules = {DomainModule.class, DataModule.class})
public interface MainActivityComponent {

    void inject(GroupListFragment groupListFragment);
}