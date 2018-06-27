package com.twjoin.arvin.chocolabs_exam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twjoin.arvin.chocolabs_exam.R;
import com.twjoin.arvin.chocolabs_exam.db.model.DramaEntity;
import com.twjoin.arvin.chocolabs_exam.listener.OnClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by arvin on 2018/6/26.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DramaAdapter extends RecyclerView.Adapter<DramaAdapter.MyViewHolder> {

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<DramaEntity> dramaDataList;
    private OnClickListener mOnClickListener;

    public DramaAdapter(@NonNull Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        dramaDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.adapter_drama, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DramaEntity drama = dramaDataList.get(position);

        final String thumbUrl = drama.getThumb();
        final String dramaName = drama.getDramaName();
        final String dramaRating = String.valueOf(drama.getRating());
        final String dramaCreateAt = drama.getCreateAt();

        Glide.with(mContext).load(thumbUrl).into(holder.imgThumb);
        holder.textDramaName.setText(dramaName);
        holder.textRating.setText(dramaRating);
        holder.textCreate.setText(dramaCreateAt);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onClick(drama);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dramaDataList.size();
    }

    public void addAllData(List<DramaEntity> dramaEntityList) {
        dramaDataList = dramaEntityList;
        notifyDataSetChanged();
    }

//    private void addData(@NonNull DramaEntity dramaEntity) {
//        dramaDataList.add(dramaEntity);
//        notifyDataSetChanged();
//    }

    public void setOnClickListener(@NonNull OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_thumb)
        ImageView imgThumb;
        @BindView(R.id.text_drama_name)
        TextView textDramaName;
        @BindView(R.id.text_rating)
        TextView textRating;
        @BindView(R.id.text_create)
        TextView textCreate;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}