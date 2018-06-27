package com.twjoin.arvin.chocolabs_exam.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.twjoin.arvin.chocolabs_exam.db.model.DramaEntity;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class Drama implements Parcelable {

    private final int dramaId;
    private final String dramaName;
    private final long totalView;
    private final String createAt;
    private final String dramaThumb;
    private final float dramaRating;

    public Drama(DramaEntity dramaEntity) {
        dramaId = dramaEntity.getDramaId();
        dramaName = dramaEntity.getDramaName();
        totalView = dramaEntity.getTotalViews();
        createAt = dramaEntity.getCreateAt();
        dramaThumb = dramaEntity.getThumb();
        dramaRating = dramaEntity.getRating();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dramaId);
        dest.writeString(this.dramaName);
        dest.writeLong(this.totalView);
        dest.writeString(this.createAt);
        dest.writeString(this.dramaThumb);
        dest.writeFloat(this.dramaRating);
    }

    private Drama(Parcel in) {
        this.dramaId = in.readInt();
        this.dramaName = in.readString();
        this.totalView = in.readLong();
        this.createAt = in.readString();
        this.dramaThumb = in.readString();
        this.dramaRating = in.readFloat();
    }

    @SuppressWarnings("unused")
    public static final Creator<Drama> CREATOR = new Creator<Drama>() {
        @Override
        public Drama createFromParcel(Parcel source) {
            return new Drama(source);
        }

        @Override
        public Drama[] newArray(int size) {
            return new Drama[size];
        }
    };

    @SuppressWarnings("unused")
    public int getDramaId() {
        return dramaId;
    }

    public String getDramaName() {
        return dramaName;
    }

    public long getTotalView() {
        return totalView;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getDramaThumb() {
        return dramaThumb;
    }

    public float getDramaRating() {
        return dramaRating;
    }
}
