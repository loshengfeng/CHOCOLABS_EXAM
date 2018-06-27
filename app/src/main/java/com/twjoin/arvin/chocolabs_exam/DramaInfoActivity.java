package com.twjoin.arvin.chocolabs_exam;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twjoin.arvin.chocolabs_exam.db.model.DramaEntity;
import com.twjoin.arvin.chocolabs_exam.db.operation.DramaOperation;
import com.twjoin.arvin.chocolabs_exam.model.Drama;
import com.twjoin.arvin.chocolabs_exam.utils.StringUtils;

import java.util.List;

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

        final Uri uri = getIntent().getData();
        if (null == uri) {
            final Drama dramaModel = getIntent().getParcelableExtra(INTENT_DRAMA_DATA);
            final String thumbUrl = dramaModel.getDramaThumb();
            final String dramaName = dramaModel.getDramaName();
            final String dramaRating = String.valueOf(dramaModel.getDramaRating());
            final String dramaCreateAt = StringUtils.getConvertTime(dramaModel.getCreateAt());
            final String dramaTotalView = String.valueOf(dramaModel.getTotalView());

            setUpView(thumbUrl, dramaName, dramaRating, dramaCreateAt, dramaTotalView);
        } else {
            final List pathSegments = uri.getPathSegments();
            final String dramaId = pathSegments.get(1).toString();

            final DramaOperation dramaOperation = DramaOperation.getInstance(this);
            final DramaEntity dramaEntity = dramaOperation.getDramaByQueryId(Long.valueOf(dramaId));

            final String thumbUrl = dramaEntity.getThumb();
            final String dramaName = dramaEntity.getDramaName();
            final String dramaRating = String.valueOf(dramaEntity.getRating());
            final String dramaCreateAt = StringUtils.getConvertTime(dramaEntity.getCreateAt());
            final String dramaTotalView = String.valueOf(dramaEntity.getTotalViews());

            setUpView(thumbUrl, dramaName, dramaRating, dramaCreateAt, dramaTotalView);
        }
    }

    private void setUpView(String thumbUrl, String dramaName, String dramaRating, String dramaCreateAt, String dramaTotalView) {
        Glide.with(this).load(thumbUrl).into(imgThumb);
        textDramaName.setText(dramaName);
        textRating.setText(dramaRating);
        textCreate.setText(dramaCreateAt);
        textTotalView.setText(dramaTotalView);
    }
}