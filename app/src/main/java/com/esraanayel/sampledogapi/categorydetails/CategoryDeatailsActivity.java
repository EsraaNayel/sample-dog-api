package com.esraanayel.sampledogapi.categorydetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.esraanayel.sampledogapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Esraa on 6/9/2018.
 */

public class CategoryDeatailsActivity extends AppCompatActivity {
    public static final String CATEGORY_TITLE_KEY = "Category_Title";

    @BindView(R.id.category_details_recycler)
    RecyclerView categoryDetailsRecycler;

    @BindView(R.id.txt_category_title)
    TextView textviewCategoryTitle;

    @BindView(R.id.img_category)
    ImageView imageviewCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(CATEGORY_TITLE_KEY);
        Log.d("title", title);
        textviewCategoryTitle.setText(title);


    }
}
