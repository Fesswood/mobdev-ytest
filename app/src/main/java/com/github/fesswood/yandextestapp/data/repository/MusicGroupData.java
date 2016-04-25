package com.github.fesswood.yandextestapp.data.repository;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by fesswood on 24.04.16.
 */
public class MusicGroupData extends RealmObject{

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    
    private String name;
    @SerializedName("genres")
    
    private RealmList<GenreData> genres = new RealmList<GenreData>();
    @SerializedName("tracks")
    
    private Integer tracks;
    @SerializedName("albums")
    
    private Integer albums;
    @SerializedName("link")
    
    private String link;
    @SerializedName("description")
    
    private String description;
    @SerializedName("cover")
    
    private CoverData cover;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The genres
     */
    public RealmList<GenreData> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres
     * The genres
     */
    public void setGenres(RealmList genres) {
        this.genres = genres;
    }

    /**
     *
     * @return
     * The tracks
     */
    public Integer getTracks() {
        return tracks;
    }

    /**
     *
     * @param tracks
     * The tracks
     */
    public void setTracks(Integer tracks) {
        this.tracks = tracks;
    }

    /**
     *
     * @return
     * The albums
     */
    public Integer getAlbums() {
        return albums;
    }

    /**
     *
     * @param albums
     * The albums
     */
    public void setAlbums(Integer albums) {
        this.albums = albums;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The cover
     */
    public CoverData getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     * The cover
     */
    public void setCover(CoverData cover) {
        this.cover = cover;
    }
}
