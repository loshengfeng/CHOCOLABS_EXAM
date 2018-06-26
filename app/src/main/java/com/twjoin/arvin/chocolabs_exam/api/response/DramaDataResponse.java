package com.twjoin.arvin.chocolabs_exam.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DramaDataResponse {
    @SerializedName("drama_id")
    private int dramaId;

    @SerializedName("name")
    private String dramaName;

    @SerializedName("total_views")
    private long totalViews;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("thumb")
    private String dramaThumb;

    @SerializedName("rating")
    private float dramaRating;

    public int getDramaId() {
        return dramaId;
    }

    public String getDramaName() {
        return dramaName;
    }

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
