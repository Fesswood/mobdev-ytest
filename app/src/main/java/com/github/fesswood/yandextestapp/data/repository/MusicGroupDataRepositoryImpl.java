package com.github.fesswood.yandextestapp.data.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.fesswood.yandextestapp.domain.musicGroup.Cover;
import com.github.fesswood.yandextestapp.domain.musicGroup.MusicGroup;
import com.github.fesswood.yandextestapp.domain.musicGroup.ClosableGroupDataRepository;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by fesswood on 20.04.16.
 */
public class MusicGroupDataRepositoryImpl implements ClosableGroupDataRepository {


    private static final String TAG = MusicGroupDataRepositoryImpl.class.getSimpleName();
    private Realm mRealm;

    public MusicGroupDataRepositoryImpl() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<List<MusicGroup>> getAllMusicGroup(Scheduler scheduler) {
        List<MusicGroupData> musicGroupDatas = mRealm.copyFromRealm(mRealm
                .where(MusicGroupData.class).findAll());
      return   Observable.just(musicGroupDatas)
                .flatMap(Observable::from)
                .map(MusicGroupDataRepositoryImpl.this::createMusicGroup)
                .toList().asObservable();
    }

    @Override
    public void saveAllMusicGroup(List<MusicGroup> musicGroups) {
        mRealm.executeTransaction(realm -> Observable.just(musicGroups)
                .flatMap(Observable::from)
                .map(MusicGroupDataRepositoryImpl.this::createMusicGroupData)
                .toList()
                .subscribe(realm::copyToRealmOrUpdate));
    }

    @Override
    public boolean isEmpty() {
        return mRealm.where(MusicGroupData.class).count() == 0;
    }

    private MusicGroup createMusicGroup(MusicGroupData x) {
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
    private ArrayList<String> getGenreTitles(MusicGroupData x) {
        RealmList<GenreData> genres = x.getGenres();
        ArrayList<String> genresList = new ArrayList<>();
        for (GenreData genre : genres) {
            genresList.add(genre.getGenreTitle());
        }
        return genresList;
    }

    private Cover createCover(CoverData cover) {
        return new Cover(cover.getBig(), cover.getSmall());
    }


    private MusicGroupData createMusicGroupData(MusicGroup x) {
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

    private RealmList createGenresData(List<String> genres) {
        RealmList<GenreData> genreData = new RealmList<>();
        for (String genreName : genres) {
            genreData.add(createGenreData(genreName));
        }
        return genreData;
    }

    private GenreData createGenreData(String s) {
        GenreData genreData = new GenreData();
        genreData.setGenreTitle(s);
        return genreData;
    }

    private CoverData createCoverData(Cover cover) {
        CoverData coverData = new CoverData();
        coverData.setBig(cover.getBig());
        coverData.setSmall(cover.getSmall());
        return coverData;
    }

    @Override
    public void open() {
        if (mRealm == null || mRealm.isClosed()) {
            Log.d(TAG, "open: restore realm instance");
            mRealm = Realm.getDefaultInstance();
        }
    }

    @Override
    public void close() {
        mRealm.close();
    }
}
