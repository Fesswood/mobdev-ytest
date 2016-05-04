package com.github.fesswood.yandextestapp.presentation.detail.DetailInfo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.fesswood.yandextestapp.R;
import com.github.fesswood.yandextestapp.presentation.common.BaseFragment;
import com.github.fesswood.yandextestapp.presentation.common.BasePresenter;
import com.github.fesswood.yandextestapp.presentation.common.Layout;
import com.github.fesswood.yandextestapp.presentation.detail.DetailActivity;
import com.github.fesswood.yandextestapp.presentation.detail.common.DetailActivityComponentProvider;
import com.github.fesswood.yandextestapp.presentation.inject.DetailActivityComponent;
import com.github.fesswood.yandextestapp.presentation.main.musicGroupList.MusicGroupViewModel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fesswood on 26.04.16.
 */
@Layout(id = R.layout.fragment_musicgroup_detail_info)
public class DetailInfoFragment extends BaseFragment implements DetailInfoView {

    private static final String ARG_GROUP_ID = "ARG_GROUP_ID";
    @Bind(R.id.tvAlbums)
    TextView tvAlbums;
    @Bind(R.id.tvTracks)
    TextView tvTracks;
    @Bind(R.id.tvGenres)
    TextView tvGenres;
    @Bind(R.id.tvDescription)
    TextView tvDescription;
    private SimpleDraweeView dvCover;
    @Inject
    protected DetailInfoPresenter mDetailInfoPresenter;


    public static DetailInfoFragment newInstance(int groupId) {

        Bundle args = new Bundle();
        args.putInt(ARG_GROUP_ID, groupId);
        DetailInfoFragment fragment = new DetailInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        DetailActivity detailActivity = (DetailActivity) getActivity();
        //noinspection unchecked
        Bundle arguments = getArguments();
        if (arguments != null) {
            int groupId = arguments.getInt(ARG_GROUP_ID);
            getPresenter().setRouter(detailActivity);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        dvCover = (SimpleDraweeView) getActivity().findViewById(R.id.dvCover);
        fab.setOnClickListener(fabView -> mDetailInfoPresenter.routeToMusicGroupWebPage());
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return mDetailInfoPresenter;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);

    }

    public DetailActivityComponent getActivityComponent() {
        return ((DetailActivity)getActivity()).getComponent();
    }


    @Override
    public void showError(@StringRes int message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void setDetailInfoViewModel(MusicGroupDetailViewModel musicGroupDetailViewModel) {
        bindView(musicGroupDetailViewModel);
    }

    private void bindView(MusicGroupDetailViewModel musicGroup) {
        tvAlbums.setText(getContext().getString(R.string.albums_desc,
                musicGroup.getAlbums()));
        tvTracks.setText(getContext().getString(R.string.tracks_desc,
                musicGroup.getTracks()));
        tvGenres.setText(TextUtils.join(", ", musicGroup.getGenres()));
        tvDescription.setText(musicGroup.getDescription());
        dvCover.setImageURI(Uri.parse(musicGroup.getCoverBiglUrl()));
        getActivity().setTitle(musicGroup.getName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
