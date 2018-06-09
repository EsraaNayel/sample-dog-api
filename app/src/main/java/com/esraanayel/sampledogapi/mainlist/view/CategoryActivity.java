package com.esraanayel.sampledogapi.mainlist.view;

import android.support.v7.app.AppCompatActivity;

import com.esraanayel.sampledogapi.R;
import com.esraanayel.sampledogapi.SearchActivity;
import com.esraanayel.sampledogapi.controls.LoadMoreRecyclerView;
import com.esraanayel.sampledogapi.mainlist.MainCategoryFragment;
import com.esraanayel.sampledogapi.mainlist.adapter.CategoryListingRecyclerViewAdapter;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.presenter.CategoryListingPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Esraa on 6/8/2018.
 */

public class CategoryActivity extends SearchActivity implements CategoryListingView {
    public static final String TAG = "category_listing_fragment";
    private static final int PAGE_LIMIT = 20;
    private String searchQuery;
    private String filterCategoryName;
    @BindView(R.id.recycler)
    LoadMoreRecyclerView categoryRecyclerView;
    private static final String SEARCH_QUERY = "search_query";
    private static final java.lang.String FILTER_CATEGORY_NAME = "filter_category_name";

    @Inject
    CategoryListingPresenter mPresenter;
    private MainCategoryFragment.OnFragmentInteractionListener mListener;
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
    public void updateData(CategoryModel items) {

    }
}
