package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;

import android.util.Log;

import com.github.fesswood.yandextestapp.R;
import com.github.fesswood.yandextestapp.data.api.RestConst;
import com.github.fesswood.yandextestapp.data.repository.MusicGroupDataRepositoryImpl;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroupInteractor;
import com.github.fesswood.yandextestapp.presentation.common.BasePresenter;
import com.github.fesswood.yandextestapp.presentation.main.MainRouter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by fesswood on 20.04.16.
 */
public class GroupListPresenter extends BasePresenter<GroupListView, MainRouter> {

    private static final String TAG = GroupListPresenter.class.getSimpleName();
    private final MusicGroupInteractor mGroupListInteractor;

    @Inject
    public GroupListPresenter(MusicGroupInteractor groupListInteractor) {
        this.mGroupListInteractor = groupListInteractor;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        mGroupListInteractor.executeRequest(new Subscriber<List<MusicGroup>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: " + "e   = [" + e.getMessage() + "]");
                getView().showError(R.string.network_error);
            }

            @Override
            public void onNext(List<MusicGroup> musicGroups) {
                Log.d(TAG, "onNext: check data:");
                for (MusicGroup group : musicGroups) {
                    Log.d(TAG, "MusicGroup: " +group);
                }
                Observable
                        .just(musicGroups)
                        .flatMap(Observable::from)
                        .map(MusicGroupViewModel::new)
                        .toList()
                        .subscribe(musicGroupViewModelList -> {
                            getView().fillAdapter(musicGroupViewModelList);
                        });

            }
        });
    }

    @Override
    public void onStop() {
        mGroupListInteractor.unsubscribe();
    }

    public void groupSelected(MusicGroupViewModel musicGroupViewModel) {
        getRouter().openDetail(musicGroupViewModel.getId());
    }
}
