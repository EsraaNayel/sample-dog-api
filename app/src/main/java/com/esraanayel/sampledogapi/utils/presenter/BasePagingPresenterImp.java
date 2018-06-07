package com.esraanayel.sampledogapi.utils.presenter;

import com.esraanayel.sampledogapi.utils.view.BasePagingView;

import okhttp3.Headers;

/**
 * Created by Esraa on 6/6/2018.
 */

public abstract class BasePagingPresenterImp implements BasePagingPresenter {
    private static final String HEADER_TOTAL_COUNT = "total";
    private int limit;
    private int mTotalItemsCount;
    private boolean isLoading = false;
    private int offset = 0;
    BasePagingView view;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }


    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public BasePagingView getView() {

        return view;
    }

    public void setView(BasePagingView view) {
        this.view = view;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public BasePagingPresenterImp(BasePagingView view, int limit) {
        this.view = view;
        this.limit = limit;
    }

    @Override
    public void requestMoreItems() {

//        if (!isLoading && hasMoreItems()) {
            loadMoreItems();
//        }

    }

    public void resetCurrentPagingValues() {
        offset = 0;
        mTotalItemsCount = 0;
        // cancel current operation in case it was getting data from the server
        isLoading = false;
    }

    private boolean hasMoreItems() {
        if (offset == 0) {
            return true;
        }
        if (mTotalItemsCount > offset) {
            return true;
        }
        return false;
    }

    protected void hideProgress() {
        view.hideProgress();
        view.hideLoadingMoreProgress();
    }

    protected void showProgressByOffsetNumber() {
        if (offset == 0) {
            view.showProgress();
        } else {
            view.showLoadingMoreProgress();
        }
    }


    protected void updatePagingValues(Headers headers, int newItemsCount) {
        if (headers != null && headers.names().contains(HEADER_TOTAL_COUNT)) {
            mTotalItemsCount = Integer.parseInt(headers.get(HEADER_TOTAL_COUNT));

        } else {
        }
        offset += newItemsCount;

    }

    protected void updatePagingValuesForOffline(int newItemsCount) {
        mTotalItemsCount = offset = newItemsCount;

    }

    @Override
    public void onDestroy() {
        destroy();
    }

    public abstract void destroy();

    public abstract void loadMoreItems();
}
