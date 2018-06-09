package com.esraanayel.sampledogapi.mainlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esraanayel.sampledogapi.R;
import com.esraanayel.sampledogapi.controls.LoadMoreRecyclerView;
import com.esraanayel.sampledogapi.mainlist.adapter.CategoryListingRecyclerViewAdapter;
import com.esraanayel.sampledogapi.mainlist.di.CategoryModule;
import com.esraanayel.sampledogapi.mainlist.di.DaggerCategoryListingComponent;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.presenter.CategoryListingPresenter;
import com.esraanayel.sampledogapi.mainlist.view.CategoryListingView;
import com.esraanayel.sampledogapi.utils.BaseSearchFragment;
import com.esraanayel.sampledogapi.utils.FilterFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.esraanayel.sampledogapi.utils.Constant.getBaseURL;

/**
 * Created by Esraa on 6/6/2018.
 */

public class MainCategoryFragment extends BaseSearchFragment implements CategoryListingView, FilterFragment {
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
    private OnFragmentInteractionListener mListener;


    private CategoryListingRecyclerViewAdapter mAdapter;

    @Override
    public void searchByText(String query) {
        searchQuery = query;
        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
//        mPresenter.resetCurrentPagingValues();
//        mPresenter.loadCategoryList(query, filterCategoryName);
    }

    @Override
    public void requestFilter(String filter) {
        if (filter == "category") {
            filterCategoryName = mPresenter.getUserFacultyId();
        } else {
            filterCategoryName = null;
        }

        if (mAdapter != null) {
            mAdapter.clearDataset();
        }
//        mPresenter.resetCurrentPagingValues();
//        mPresenter.loadCategoryList(searchQuery, filterCategoryName);
    }

    public interface OnFragmentInteractionListener {
        void onEventClicked(CategoryModel news);
    }

    public static MainCategoryFragment newInstance() {
        MainCategoryFragment fragment = new MainCategoryFragment();
        return fragment;
    }

    public static Fragment newInstance(@Nullable String query, @Nullable String filterCategoryName) {
        Bundle bundle = new Bundle();
        bundle.putString(SEARCH_QUERY, query);
        bundle.putString(FILTER_CATEGORY_NAME, filterCategoryName);
        Fragment fragment = new MainCategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            searchQuery = getArguments().getString(SEARCH_QUERY);
            filterCategoryName = getArguments().getString(FILTER_CATEGORY_NAME);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_listing_fragment, container, false);
        ButterKnife.bind(this, view);
        mPresenter.loadCategoryList(searchQuery, filterCategoryName);

        return view;
    }

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
//        if (mAdapter == null) {
//            mAdapter = new CategoryListingRecyclerViewAdapter(items.getMessage(), onCategoryClickListener);
//            LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//            categoryRecyclerView.setLayoutManager(manager);
//            categoryRecyclerView.setAdapter(mAdapter);
//            categoryRecyclerView.setOnScrolledToEndListener(onCategoriesRecyclerViewScrolledToEndListener);
//        } else {
//            mAdapter.addAll(items.getMessage());
//        }
//        mPresenter.LoadImages("african");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mPresenter.loadCategoryList(searchQuery, filterCategoryName);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    LoadMoreRecyclerView.OnScrolledToEndListener onCategoriesRecyclerViewScrolledToEndListener = new LoadMoreRecyclerView.OnScrolledToEndListener() {
        @Override
        public void reachedEnd() {
//            mPresenter.loadCategoryList(searchQuery, filterCategoryName);
        }
    };


    View.OnClickListener onCategoryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = categoryRecyclerView.getChildLayoutPosition(v);
//            CategoryModel selectedNewsItem = mAdapter.getItemByPosition(position);

            String categoryModel = mAdapter.getItemByPosition(position);
            Toast.makeText(getContext(), "categoryModel" + categoryModel, Toast.LENGTH_SHORT).show();
//
//            // Start MSANotificationsDetailsActivity
//            Intent intent = new Intent(getContext(), NewsDetailsActivity.class);
//            intent.putExtra(SELECTED_NEWS_DETAILS_MODEL, selectedNewsItem);
//            startActivity(intent);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
//            mPresenter.onDestroy();
        }
    }
}
