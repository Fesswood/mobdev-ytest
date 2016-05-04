package com.github.fesswood.yandextestapp.presentation.detail.DetailInfo;

import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fesswood on 26.04.16.
 */
public class MusicGroupDetailViewModel {

    private int id;
    private String name;
    private List<String> genres = new ArrayList<String>();
    private Integer tracks;
    private Integer albums;
    private String coverBiglUrl;
    private String link;
    private String description;
    public MusicGroupDetailViewModel(MusicGroup x) {
        id = x.getId();
        name = x.getName();
        genres = x.getGenres();
        tracks = x.getTracks();
        albums = x.getAlbums();
        coverBiglUrl = x.getCover().getBig();
        link = x.getLink();
        description = x.getDescription();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCoverBiglUrl() {
        return coverBiglUrl;
    }

    public void setCoverBiglUrl(String coverBiglUrl) {
        this.coverBiglUrl = coverBiglUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
