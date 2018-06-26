package com.twjoin.arvin.chocolabs_exam.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DramaDataResponse {
    @SuppressWarnings("unused")
    @SerializedName("drama_id")
    private int dramaId;

    @SuppressWarnings("unused")
    @SerializedName("name")
    private String dramaName;

    @SuppressWarnings("unused")
    @SerializedName("total_views")
    private long totalViews;

    @SuppressWarnings("unused")
    @SerializedName("created_at")
    private String createdAt;

    @SuppressWarnings("unused")
    @SerializedName("thumb")
    private String dramaThumb;

    @SuppressWarnings("unused")
    @SerializedName("rating")
    private float dramaRating;

    @SuppressWarnings("unused")
    public int getDramaId() {
        return dramaId;
    }

    public String getDramaName() {
        return dramaName;
    }

    @SuppressWarnings("unused")
    public long getTotalViews() {
        return totalViews;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDramaThumb() {
        return dramaThumb;
    }

    public float getDramaRating() {
        return dramaRating;
    }
}
