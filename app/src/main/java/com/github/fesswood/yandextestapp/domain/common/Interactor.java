package com.github.fesswood.yandextestapp.domain.common;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public abstract class Interactor<ResultType> {
    private final CompositeSubscription subscription = new CompositeSubscription();
    protected final Scheduler jobScheduler;
    private final Scheduler uiScheduler;

    public Interactor(Scheduler jobScheduler, Scheduler uiScheduler) {
        this.jobScheduler = jobScheduler;
        this.uiScheduler = uiScheduler;
    }

    protected abstract Observable<ResultType> prepareRequest();

    public void executeRequest(Subscriber<ResultType> subscriber) {
        subscription.add(prepareRequest()
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void execute(Observable<ResultType> observable, Subscriber<ResultType> subscriber) {
        subscription.add(observable
                .subscribeOn(jobScheduler)
                .observeOn(uiScheduler)
                .subscribe(subscriber));
    }

    public void unsubscribe() {
        subscription.clear();
    }

}
