package com.github.fesswood.yandextestapp.data.api;


import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface RestApiService {


    @GET(RestConst.api.GET_ARTISTS)
    Observable<List<MusicGroup>> getMusicGroup();
} 