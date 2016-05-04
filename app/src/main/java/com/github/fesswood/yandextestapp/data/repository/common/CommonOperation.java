package com.github.fesswood.yandextestapp.data.repository.common;

import android.support.annotation.NonNull;

import com.github.fesswood.yandextestapp.domain.musicGroup.Cover;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class CommonOperation {
    public CommonOperation() {
    }

    public static MusicGroup createMusicGroup(MusicGroupData x) {
        MusicGroup musicGroup = new MusicGroup();
        musicGroup.setAlbums(x.getAlbums());
        CoverData cover = x.getCover();
        musicGroup.setCover(createCover(cover));
        musicGroup.setDescription(x.getDescription());
        musicGroup.setGenres(getGenreTitles(x));
        musicGroup.setId(x.getId());
        musicGroup.setLink(x.getLink());
        musicGroup.setName(x.getName());
        musicGroup.setTracks(x.getTracks());
        return musicGroup;
    }

    @NonNull
    public static ArrayList<String> getGenreTitles(MusicGroupData x) {
        RealmList<GenreData> genres = x.getGenres();
        ArrayList<String> genresList = new ArrayList<String>();
        for (GenreData genre : genres) {
            genresList.add(genre.getGenreTitle());
        }
        return genresList;
    }

    public static Cover createCover(CoverData cover) {
        return new Cover(cover.getBig(), cover.getSmall());
    }

    public static MusicGroupData createMusicGroupData(MusicGroup x) {
        MusicGroupData musicGroupData = new MusicGroupData();
        musicGroupData.setAlbums(x.getAlbums());
        musicGroupData.setCover(createCoverData(x.getCover()));
        musicGroupData.setDescription(x.getDescription());
        musicGroupData.setGenres(createGenresData(x.getGenres()));
        musicGroupData.setId(x.getId());
        musicGroupData.setLink(x.getLink());
        musicGroupData.setName(x.getName());
        musicGroupData.setTracks(x.getTracks());
        return musicGroupData;
    }

    public static RealmList createGenresData(List<String> genres) {
        RealmList<GenreData> genreData = new RealmList<GenreData>();
        for (String genreName : genres) {
            genreData.add(createGenreData(genreName));
        }
        return genreData;
    }

    public static GenreData createGenreData(String s) {
        GenreData genreData = new GenreData();
        genreData.setGenreTitle(s);
        return genreData;
    }

    public static CoverData createCoverData(Cover cover) {
        CoverData coverData = new CoverData();
        coverData.setBig(cover.getBig());
        coverData.setSmall(cover.getSmall());
        return coverData;
    }
}