package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.fesswood.yandextestapp.R;
import com.github.fesswood.yandextestapp.presentation.common.BaseFragment;
import com.github.fesswood.yandextestapp.presentation.common.BasePresenter;
import com.github.fesswood.yandextestapp.presentation.common.Layout;
import com.github.fesswood.yandextestapp.presentation.common.SimpleDividerItemDecoration;
import com.github.fesswood.yandextestapp.presentation.detail.DetailActivity;
import com.github.fesswood.yandextestapp.presentation.inject.DetailActivityComponent;
import com.github.fesswood.yandextestapp.presentation.inject.MainActivityComponent;
import com.github.fesswood.yandextestapp.presentation.main.MainActivity;
import com.github.fesswood.yandextestapp.presentation.main.common.MainActivityComponentProvider;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GroupListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@Layout(id = R.layout.fragment_group_list)
public class GroupListFragment extends BaseFragment implements GroupListView,
        MusicGroupAdapter.OnMusicGroupClickListener {


    private static final String TAG = GroupListFragment.class.getCanonicalName();
    @Inject
    protected GroupListPresenter mGroupListPresenter;
    @Bind(R.id.rvMusicGroups)
    RecyclerView rvMusicGroups;

    private MusicGroupAdapter mMusicGroupAdapter;
    private String title = null;

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

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return mGroupListPresenter;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    public MainActivityComponent getActivityComponent() {
        return ((MainActivity)getActivity()).getComponent();
    }



    @Override
    public void fillAdapter(List<MusicGroupViewModel> musicGroups) {
        Log.d(TAG, "fillAdapter: check data:");
        for (MusicGroupViewModel group : musicGroups) {
            Log.d(TAG, "fillAdapter: " + group);
        }
        mMusicGroupAdapter = new MusicGroupAdapter(musicGroups, getContext());
        mMusicGroupAdapter.setOnMusicGroupClickListener(this);
        rvMusicGroups.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        rvMusicGroups.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMusicGroups.setAdapter(mMusicGroupAdapter);
    }

    @Override
    public void showError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void selectMusicGroup(MusicGroupViewModel viewModel) {
        Log.d(TAG, "selectMusicGroup() called with: " + "viewModel = [" + viewModel + "]");
            mGroupListPresenter.groupSelected(viewModel);
    }
}
