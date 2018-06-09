package com.esraanayel.sampledogapi.mainlist.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esraanayel.sampledogapi.R;
import com.esraanayel.sampledogapi.SearchActivity;
import com.esraanayel.sampledogapi.controls.LoadMoreRecyclerView;
import com.esraanayel.sampledogapi.mainlist.MainCategoryFragment;
import com.esraanayel.sampledogapi.mainlist.adapter.CategoryListingRecyclerViewAdapter;
import com.esraanayel.sampledogapi.mainlist.di.CategoryModule;
import com.esraanayel.sampledogapi.mainlist.di.DaggerCategoryListingComponent;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.presenter.CategoryListingPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.esraanayel.sampledogapi.utils.Constant.getBaseURL;

/**
 * Created by Esraa on 6/8/2018.
 */

public class CategoryActivity extends AppCompatActivity implements CategoryListingView {
    public static final String TAG = "category_listing_fragment";
    private static final int PAGE_LIMIT = 20;
    private String searchQuery;
    private String filterCategoryName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView categoryRecyclerView;
    SearchView msearchView;
    private Menu menu;

    private static final String SEARCH_QUERY = "search_query";
    private static final java.lang.String FILTER_CATEGORY_NAME = "filter_category_name";

    @Inject
    CategoryListingPresenter mPresenter;
    private CategoryListingRecyclerViewAdapter mAdapter;

    @Override
    public void showProgress() {

    }

    @Override
    public void showLoadingMoreProgress() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void hideLoadingMoreProgress() {

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        DaggerCategoryListingComponent.builder().categoryModule(new CategoryModule(this, getBaseURL(),getApplicationContext()))
                .build().inject(this);
        mPresenter.loadCategoryList(searchQuery, filterCategoryName);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        prepareToolbar();
    }

    @Override
    public void updateData(CategoryModel items) {
        if (mAdapter == null) {
            mAdapter = new CategoryListingRecyclerViewAdapter(items.getMessage(), onCategoryClickListener);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            categoryRecyclerView.setLayoutManager(layoutManager);
            categoryRecyclerView.setAdapter(mAdapter);
//            categoryRecyclerView.setOnScrolledToEndListener(onCategoriesRecyclerViewScrolledToEndListener);
        } else {
            mAdapter.addAll(items.getMessage());
        }
//        mPresenter.LoadImages("african");

    }

    private void prepareToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    View.OnClickListener onCategoryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = categoryRecyclerView.getChildLayoutPosition(v);
//            CategoryModel selectedNewsItem = mAdapter.getItemByPosition(position);

            String categoryModel = mAdapter.getItemByPosition(position);
            Toast.makeText(getApplicationContext(), "" + categoryModel, Toast.LENGTH_SHORT).show();
//
//            // Start MSANotificationsDetailsActivity
//            Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
//            intent.putExtra(SELECTED_NEWS_DETAILS_MODEL, selectedNewsItem);
//            startActivity(intent);
        }
    };


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
            mAdapter.getFilter().filter(query);
            return true;
        }


        @Override
        public boolean onQueryTextChange(String newText) {
            mAdapter.getFilter().filter(newText);
            return true;
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
//            mPresenter.onDestroy();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!msearchView.isIconified()) {
            msearchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

}
