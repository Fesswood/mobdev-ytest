package com.github.fesswood.yandextestapp.data.api;

import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by fesswood on 24.04.16.
 */
public class RestApiImpl implements RestApi {


    public RestApiImpl() {
    }

    @Override
    public Observable<List<MusicGroup>> getMusicList() {
        RestApiService service = ServiceFactory.createRetrofitService(RestApiService.class,
                RestConst.API_ENDPOINT);
        return service.getMusicGroup();
    }

}
