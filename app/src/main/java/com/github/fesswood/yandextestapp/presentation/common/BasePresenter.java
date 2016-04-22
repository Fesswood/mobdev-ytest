package com.github.fesswood.yandextestapp.presentation.common;

/**
 * Базовый презентер хранит ссылки на роутер и вьб и предоставляет к ним доступ
 * жизненный цикл описывается методами {@link #onStart()} и {@link #onStop()} ()}
 *
 * @param <View>
 * @param <Router>
 */
public abstract class BasePresenter<View, Router> {
    private View view;
    private Router router;

    public abstract void onStart();

    public abstract void onStop();

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}


