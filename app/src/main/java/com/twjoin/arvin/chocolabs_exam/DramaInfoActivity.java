package com.twjoin.arvin.chocolabs_exam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twjoin.arvin.chocolabs_exam.model.Drama;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DramaInfoActivity extends AppCompatActivity {

    private static final String INTENT_DRAMA_DATA = "intent_drama_data";

    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.img_thumb)
    ImageView imgThumb;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.text_drama_name)
    TextView textDramaName;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.text_rating)
    TextView textRating;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.text_create)
    TextView textCreate;
    @SuppressWarnings("WeakerAccess")
    @BindView(R.id.text_total_view)
    TextView textTotalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama_info);
        ButterKnife.bind(this);

        final Drama dramaModel = getIntent().getParcelableExtra(INTENT_DRAMA_DATA);
        final String thumbUrl = dramaModel.getDramaThumb();
        final String dramaName = dramaModel.getDramaName();
        final String dramaRating = String.valueOf(dramaModel.getDramaRating());
        final String dramaCreateAt = dramaModel.getCreateAt();
        final String dramaTotalView = String.valueOf(dramaModel.getTotalView());

        Glide.with(this).load(thumbUrl).into(imgThumb);
        textDramaName.setText(dramaName);
        textRating.setText(dramaRating);
        textCreate.setText(dramaCreateAt);
        textTotalView.setText(dramaTotalView);
    }
}
