package com.esraanayel.sampledogapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.esraanayel.sampledogapi.mainlist.MainCategoryFragment;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.utils.FragmentType;
import com.esraanayel.sampledogapi.utils.NetworkHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        MainCategoryFragment.OnFragmentInteractionListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FragmentType currentFragmentType;
    private Menu menu;
    SearchView msearchView;
    int selectedFilterValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeCategoryListingFragment();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        prepareToolbar();

    }

    void initializeCategoryListingFragment() {
        if (getSupportFragmentManager().findFragmentByTag(MainCategoryFragment.TAG) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_container, MainCategoryFragment.newInstance(), MainCategoryFragment.TAG)
                    .commit();
        }
    }

    private void prepareToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void filterRequested(String filter) {
        FragmentType type = currentFragmentType;
        if (type != null) {
            switch (type) {
                case CATEGORY_LISTING: {
                    MainCategoryFragment categoryFragment = (MainCategoryFragment)
                            getSupportFragmentManager().findFragmentByTag(MainCategoryFragment.TAG);
                    if (categoryFragment != null) {
                        categoryFragment.requestFilter(filter);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        this.menu = menu;
        MenuItem searchItem = menu.findItem(R.id.search);
        msearchView = (SearchView) searchItem.getActionView();
        msearchView.setMaxWidth(Integer.MAX_VALUE);
        msearchView.setOnQueryTextListener(mOnSearchQueryTextListener);

        return true;
    }

    private SearchView.OnQueryTextListener mOnSearchQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            searchRequested(query);
            return true;
        }


        @Override
        public boolean onQueryTextChange(String newText) {
            return true;
        }
    };

    private void searchRequested(String query) {
        if (TextUtils.isEmpty(query)) {
            return;
        }

        if (NetworkHelper.isNetworkAvailable(getApplicationContext())) {
            SearchActivity.start(this, query, currentFragmentType);
            msearchView.setQuery("", false);
        } else {
            showNoInternetConnection();
        }
    }


    public void showNoInternetConnection() {
        showToast(R.string.no_intenrent_connection);
    }

    private void showToast(@StringRes int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    private void resetFilterDialogValue() {
        selectedFilterValue = 0;
    }

    @Override
    public void onEventClicked(CategoryModel news) {

    }

    @Override
    public void onBackPressed() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        super.onBackPressed();
    }
}
