package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;

import javax.inject.Inject;

/**
 * Created by fesswood on 20.04.16.
 */
public class GroupListPresenter {

    @Inject
    public MainPresenter(GroupListInteractor groupListInteractor) {
        this.mTaskListInteractor = taskListInteractor;
    }
}
