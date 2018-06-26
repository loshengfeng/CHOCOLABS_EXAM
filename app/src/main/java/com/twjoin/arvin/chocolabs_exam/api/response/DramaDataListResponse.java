package com.twjoin.arvin.chocolabs_exam.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DramaDataListResponse {
    @SuppressWarnings("unused")
    @SerializedName("data")
    private List<DramaDataResponse> dramaData;

    public List<DramaDataResponse> getDramaData() {
        return dramaData;
    }
}
