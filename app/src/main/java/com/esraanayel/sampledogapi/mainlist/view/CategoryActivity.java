package com.esraanayel.sampledogapi.mainlist.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.esraanayel.sampledogapi.R;
import com.esraanayel.sampledogapi.categorydetails.CategoryDeatailsActivity;
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

    public static final String CATEGORY_TITLE_KEY = "Category_Title";
//    @BindView(R.id.prgrs_load_more)
//    ProgressBar mainProgressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView categoryRecyclerView;
    SearchView msearchView;

//    @BindView(R.id.swipe_to_refresh_items)
//    SwipeRefreshLayout swipeRefreshLayout;
    private Menu menu;
    @Inject
    CategoryListingPresenter mPresenter;
    private CategoryListingRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        DaggerCategoryListingComponent.builder().categoryModule(new CategoryModule(this, getBaseURL(), getApplicationContext()))
                .build().inject(this);
//        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        mPresenter.loadCategoryList();
//        mPresenter.LoadImages();
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
//        swipeRefreshLayout.setRefreshing(false);

//        mPresenter.LoadImages("african");

    }

//    @Override
//    public void updateImages(CategoryModel items) {
//        if (mAdapter == null) {
//            mAdapter = new CategoryListingRecyclerViewAdapter(items.getMessage(), onCategoryClickListener);
//            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//            categoryRecyclerView.setLayoutManager(layoutManager);
//            categoryRecyclerView.setAdapter(mAdapter);
////            categoryRecyclerView.setOnScrolledToEndListener(onCategoriesRecyclerViewScrolledToEndListener);
//        } else {
//            mAdapter.addAll(items.getMessage());
//        }
//    }

    private void prepareToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    View.OnClickListener onCategoryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = categoryRecyclerView.getChildLayoutPosition(v);
            String categoryTitle = mAdapter.getItemByPosition(position);
            Toast.makeText(getApplicationContext(), "" + categoryTitle, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), CategoryDeatailsActivity.class);
            intent.putExtra(CATEGORY_TITLE_KEY, categoryTitle);
            startActivity(intent);
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


    @Override
    public void showProgress() {
//        mainProgressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void showError() {
        showToast(R.string.general_error);
    }

    @Override
    public void hideProgress() {
//        mainProgressBar.setVisibility(View.GONE);
//        swipeRefreshLayout.setRefreshing(false);
    }

    SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (mAdapter != null) {
                mAdapter.clearDataset();
            }
            mPresenter.loadCategoryList();
        }
    };

    private void showToast(@StringRes int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }
}
