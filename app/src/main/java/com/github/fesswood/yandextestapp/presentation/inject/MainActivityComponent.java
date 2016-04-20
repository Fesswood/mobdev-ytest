package com.github.fesswood.yandextestapp.presentation.inject;

import com.github.fesswood.yandextestapp.presentation.musicGroupList.GroupListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fesswood on 20.04.16.
 */
@Singleton
@Component(modules = {DomainModule.class, DataModule.class})
public interface MainActivityComponent {

    void inject(GroupListFragment contactsFragment);
}