package com.esraanayel.sampledogapi.mainlist.presenter;

import android.content.Context;
import android.util.Log;

import com.esraanayel.sampledogapi.mainlist.interactor.CategoryListingInteractor;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.view.CategoryListingView;
import com.esraanayel.sampledogapi.utils.presenter.BasePagingPresenterImp;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Esraa on 6/6/2018.
 */

public class CategoryListingPresenterImpl extends BasePagingPresenterImp implements CategoryListingPresenter {
    private CategoryListingView mView;
    private CategoryListingInteractor mInteractor;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Context mContext;
    private String queryText = null;
    private String facultyId = null;


    public CategoryListingPresenterImpl(CategoryListingView mView, CategoryListingInteractor mInteractor, int limit, Context mContext) {
        super(mView, limit);
        this.mInteractor = mInteractor;
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void loadOfflineCategories() {

    }

    @Override
    public void requestMoreItemsWithQueryFilters(/*@Nullable String query, @Nullable String facultyId*/) {
        requestMoreItems();
    }

    @Override
    public String getUserFacultyId() {
        return null;
    }

    @Override
    public void destroy() {
        disposeRequest();
    }

    @Override
    public void loadMoreItems() {
//        if (!NetworkHelper.isNetworkAvailable(mContext)) {
//            if (getOffset() == 0 && TextUtils.isEmpty(queryText)) {
//                loadOfflineNews();
//            } else if (!TextUtils.isEmpty(queryText)) {
//                mView.showOfflineNoData();
//                mView.showEmptyScreen();
//            }
//            return;
//        }
        final int originalOffset = getOffset();

        setLoading(true);
        disposeRequest();
//        showProgressByOffsetNumber();

//        mView.showProgress();
        mCompositeDisposable.add(mInteractor.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<CategoryModel>>() {
                    @Override
                    public void accept(@NonNull Result<CategoryModel> listResult) throws Exception {

                        if (listResult.response().body() != null) {
                            Log.d("RESPONSE", listResult.response().body().toString());
                            mView.updateData(listResult.response().body());

                        } else {

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError();
                        setLoading(false);
                        hideProgress();

                    }
                }));

    }

    private void disposeRequest() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

}
