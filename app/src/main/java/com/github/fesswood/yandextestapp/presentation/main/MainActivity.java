package com.github.fesswood.yandextestapp.presentation.main;

import android.content.Intent;
import android.os.Bundle;

import com.github.fesswood.yandextestapp.R;
import com.github.fesswood.yandextestapp.presentation.common.BaseActivity;
import com.github.fesswood.yandextestapp.presentation.common.Layout;
import com.github.fesswood.yandextestapp.presentation.detail.DetailActivity;
import com.github.fesswood.yandextestapp.presentation.inject.DataModule;
import com.github.fesswood.yandextestapp.presentation.inject.DomainModule;
import com.github.fesswood.yandextestapp.presentation.inject.MainActivityComponent;
import com.github.fesswood.yandextestapp.presentation.main.musicGroupList.GroupListFragment;

@Layout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements MainRouter {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MainActivityComponent mMainActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new GroupListFragment())
                .commit();
        mMainActivityComponent =
                DaggerMainActivityComponent
                        .builder()
                        .dataModule(new DataModule())
                        .domainModule(new DomainModule())
                        .build();
    }

    @Override
    public void openDetail() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    public MainActivityComponent getMainActivityComponent() {
        return mMainActivityComponent;
    }
}
