package com.github.fesswood.yandextestapp.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.fesswood.yandextestapp.R;
import com.github.fesswood.yandextestapp.presentation.common.BaseActivity;
import com.github.fesswood.yandextestapp.presentation.common.Layout;
import com.github.fesswood.yandextestapp.presentation.detail.DetailInfo.DetailInfoFragment;
import com.github.fesswood.yandextestapp.presentation.detail.common.DetailActivityComponentProvider;
import com.github.fesswood.yandextestapp.presentation.inject.DaggerDetailActivityComponent;
import com.github.fesswood.yandextestapp.presentation.inject.DataModule;
import com.github.fesswood.yandextestapp.presentation.inject.DetailActivityComponent;
import com.github.fesswood.yandextestapp.presentation.inject.DomainModule;

import butterknife.Bind;

@Layout(id = R.layout.activity_detail)
public class DetailActivity extends BaseActivity implements DetailRouter,
        DetailActivityComponentProvider {

    private static final String ARG_GROUP_ID = "ARG_GROUP_ID";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private DetailActivityComponent mDetailActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int groupId = getIntent().getIntExtra(ARG_GROUP_ID,-1);
        if(groupId == -1){
            throw new IllegalArgumentException("you must set ARG_GROUP_ID to launch this activity.");
        }
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DetailInfoFragment.newInstance(groupId))
                .commit();

        mDetailActivityComponent =
                DaggerDetailActivityComponent
                        .builder()
                        .dataModule(new DataModule(groupId))
                        .domainModule(new DomainModule())
                        .build();
    }

    public static Intent startActivity(Context context, int musicGroupId) {
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra(ARG_GROUP_ID, musicGroupId);
        return i;
    }

    @Override
    public void routeToMusicGroupWebPage(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public DetailActivityComponent getComponent() {
        return mDetailActivityComponent;
    }
}
