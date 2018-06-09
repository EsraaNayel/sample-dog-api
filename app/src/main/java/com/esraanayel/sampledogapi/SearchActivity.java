package com.esraanayel.sampledogapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.esraanayel.sampledogapi.mainlist.MainCategoryFragment;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.utils.BaseSearchFragment;
import com.esraanayel.sampledogapi.utils.FragmentType;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Esraa on 6/8/2018.
 */

public class SearchActivity extends AppCompatActivity implements MainCategoryFragment.OnFragmentInteractionListener {

    private static final String SEARCH_QUERY = "search_query";
    private static final String FRAGMENT_TYPE = "fragment_type";
    private Menu menu;
    private String searchQuery;
    private String filterParameter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    SearchView msearchView;
    BaseSearchFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchQuery = getIntent().getStringExtra(SEARCH_QUERY);
        filterParameter=getIntent().getStringExtra(filterParameter);
        prepareToolbar();
        initializeSearchFragment((FragmentType) getIntent().getSerializableExtra(FRAGMENT_TYPE));

    }

    private void initializeSearchFragment(FragmentType type) {
        Fragment fragment = null;
        String tag = null;
//        switch (type) {
//            case CATEGORY_LISTING: {
                fragment = MainCategoryFragment.newInstance(searchQuery, filterParameter);
                tag = MainCategoryFragment.TAG;
             //   break;
            //}
//        }
        currentFragment = (BaseSearchFragment) fragment;
        getSupportFragmentManager().beginTransaction().replace(R.id.frm_search_container, fragment, tag).commit();

    }

    public static void start(Context context, String searchQuery, FragmentType type) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(SEARCH_QUERY, searchQuery);
        intent.putExtra(FRAGMENT_TYPE, type);
        context.startActivity(intent);
    }

    private void prepareToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        this.menu = menu;
        MenuItem searchItem = menu.findItem(R.id.search);
        msearchView = (SearchView) searchItem.getActionView();
        msearchView.setMaxWidth(Integer.MAX_VALUE);
        msearchView.setOnQueryTextListener(mOnSearchQueryTextListener);
        msearchView.setIconified(false);
        msearchView.setQuery(searchQuery, false);
        return true;
    }

    private SearchView.OnQueryTextListener mOnSearchQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if (TextUtils.isEmpty(query)) {
                return false;
            }
            searchCurrentFragment(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void searchCurrentFragment(String query) {
        if (currentFragment != null) {
            currentFragment.searchByText(query);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onEventClicked(CategoryModel news) {

    }
}
