package com.github.fesswood.yandextestapp.domain.musicGroup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fesswood on 24.04.16.
 */
public class Cover {

    @SerializedName("small")
    private String small;
    @SerializedName("big")
    private String big;

    public Cover(String big, String small) {
        this.small = small;
        this.big = big;
    }

    /**
     * @return The small
     */
    public String getSmall() {
        return small;
    }

    /**
     * @param small The small
     */
    public void setSmall(String small) {
        this.small = small;
    }

    /**
     * @return The big
     */
    public String getBig() {
        return big;
    }

    /**
     * @param big The big
     */
    public void setBig(String big) {
        this.big = big;
    }

}