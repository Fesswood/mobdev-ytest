package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.fesswood.yandextestapp.R;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.presentation.common.BaseFragment;
import com.github.fesswood.yandextestapp.presentation.common.BasePresenter;
import com.github.fesswood.yandextestapp.presentation.common.Layout;
import com.github.fesswood.yandextestapp.presentation.inject.MainActivityComponent;
import com.github.fesswood.yandextestapp.presentation.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@Layout(id = R.layout.fragment_group_list)
public class GroupListFragment extends BaseFragment implements GroupListView{


    @Inject
    protected GroupListPresenter mGroupListPresenter;

    public static GroupListFragment newInstance() {
        return new GroupListFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        MainActivity mainActivity = (MainActivity) getActivity();
        //noinspection unchecked
        getPresenter().setRouter(mainActivity);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //noinspection unchecked
        getPresenter().setRouter(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_list, container, false);
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void inject() {
         getMainActivityComponent().inject(this);
    }


    protected MainActivityComponent getMainActivityComponent() {
        return ((MainActivity) getActivity()).getMainActivityComponent();
    }

    @Override
    public void fillAdapter(List<MusicGroup> musicGroups) {

    }

    @Override
    public void showError(@StringRes int message) {
        Snackbar.make(getView(),message, Snackbar.LENGTH_INDEFINITE).show();
    }

}
