package com.twjoin.arvin.chocolabs_exam.db.model;

import com.twjoin.arvin.chocolabs_exam.api.response.DramaDataResponse;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

import java.math.BigDecimal;

/**
 * Created by arvin on 2018/6/26.
 */
@SuppressWarnings("DefaultFileTemplate")
@Entity
public class DramaEntity {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "DRAMA_ID")
    @Index(unique = true)
    private int dramaId;

    @Property(nameInDb = "DRAMA_NAME")
    private String dramaName;

    @Property(nameInDb = "TOTAL_VIEWS")
    private long totalViews;

    @Property(nameInDb = "CREATE_AT")
    private String createAt;

    @Property(nameInDb = "THUMB")
    private String thumb;

    @Property(nameInDb = "RATING")
    private float rating;

    @Generated(hash = 2074439146)
    public DramaEntity(Long id, int dramaId, String dramaName, long totalViews,
            String createAt, String thumb, float rating) {
        this.id = id;
        this.dramaId = dramaId;
        this.dramaName = dramaName;
        this.totalViews = totalViews;
        this.createAt = createAt;
        this.thumb = thumb;
        this.rating = rating;
    }

    @Generated(hash = 1557491608)
    public DramaEntity() {
    }

    public DramaEntity(DramaDataResponse drama) {
        this.dramaId = drama.getDramaId();
        this.dramaName = drama.getDramaName();
        this.totalViews = drama.getTotalViews();
        this.createAt = drama.getCreatedAt();
        this.thumb = drama.getDramaThumb();
        this.rating = new BigDecimal(drama.getDramaRating()).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDramaId() {
        return this.dramaId;
    }

    public void setDramaId(int dramaId) {
        this.dramaId = dramaId;
    }

    public String getDramaName() {
        return this.dramaName;
    }

    public void setDramaName(String dramaName) {
        this.dramaName = dramaName;
    }

    public long getTotalViews() {
        return this.totalViews;
    }

    public void setTotalViews(long totalViews) {
        this.totalViews = totalViews;
    }

    public String getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getThumb() {
        return this.thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
