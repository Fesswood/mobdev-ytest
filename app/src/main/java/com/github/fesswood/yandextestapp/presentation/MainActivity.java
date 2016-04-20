package com.github.fesswood.yandextestapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.fesswood.yandextestapp.R;

public class MainActivity extends AppCompatActivity implements MainRouter {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void openDetail() {

    }
}
