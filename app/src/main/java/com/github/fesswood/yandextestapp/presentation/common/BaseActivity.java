package com.github.fesswood.yandextestapp.presentation.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;

/**
 * Created by fesswood on 20.04.16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.id());
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
