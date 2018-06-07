package com.esraanayel.sampledogapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esraanayel.sampledogapi.mainlist.MainCategoryFragment;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;

public class MainActivity extends AppCompatActivity implements MainCategoryFragment.OnFragmentInteractionListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeCategoryListingFragment();

    }
    void initializeCategoryListingFragment() {
        if (getSupportFragmentManager().findFragmentByTag(MainCategoryFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_container, MainCategoryFragment.newInstance(), MainCategoryFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onEventClicked(CategoryModel news) {

    }
}
