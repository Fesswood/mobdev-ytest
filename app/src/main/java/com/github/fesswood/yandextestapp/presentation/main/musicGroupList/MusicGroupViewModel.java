package com.github.fesswood.yandextestapp.presentation.main.musicGroupList;

import com.github.fesswood.yandextestapp.domain.musicGroup.Cover;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fesswood on 20.04.16.
 */
public class MusicGroupViewModel {

    private int id;
    private String name;
    private List<String> genres = new ArrayList<String>();
    private Integer tracks;
    private Integer albums;
    private String coverSmalUrl;

    public MusicGroupViewModel(MusicGroup x) {
        id = x.getId();
        name = x.getName();
        genres = x.getGenres();
        tracks = x.getTracks();
        albums = x.getAlbums();
        coverSmalUrl = x.getCover().getSmall();

    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getTracks() {
        return tracks;
    }

    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    public Integer getAlbums() {
        return albums;
    }

    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    public String getCoverSmalUrl() {
        return coverSmalUrl;
    }

    public void setCoverSmalUrl(String coverSmalUrl) {
        this.coverSmalUrl = coverSmalUrl;
    }
}
