package com.github.fesswood.yandextestapp.data.repository;

import io.realm.RealmObject;

/**
 * Created by fesswood on 24.04.16.
 */
public class GenreData extends RealmObject{
    private String genreTitle;

    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }

    public String getGenreTitle() {
        return genreTitle;
    }
}
