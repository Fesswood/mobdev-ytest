package com.github.fesswood.yandextestapp.data.repository.common;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by fesswood on 24.04.16.
 */
public class CoverData extends RealmObject{

    @SerializedName("small")
    private String small;
    @SerializedName("big")
    private String big;

    /**
     *
     * @return
     * The small
     */
    public String getSmall() {
        return small;
    }

    /**
     *
     * @param small
     * The small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     *
     * @return
     * The big
     */
    public String getBig() {
        return big;
    }

    /**
     *
     * @param big
     * The big
     */
    public void setBig(String big) {
        this.big = big;
    }

}