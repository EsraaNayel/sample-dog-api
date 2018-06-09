package com.esraanayel.sampledogapi.mainlist.presenter;

import android.content.Context;
import android.util.Log;

import com.esraanayel.sampledogapi.mainlist.interactor.CategoryListingInteractor;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.view.CategoryListingView;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Esraa on 6/6/2018.
 */

public class CategoryListingPresenterImpl implements CategoryListingPresenter {
    private CategoryListingView mView;
    private CategoryListingInteractor mInteractor;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private Context mContext;

    public CategoryListingPresenterImpl(CategoryListingView mView, CategoryListingInteractor mInteractor, Context mContext) {
        this.mInteractor = mInteractor;
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void loadOfflineCategories() {

    }

    @Override
    public void loadCategoryList() {
        mCompositeDisposable.add(mInteractor.getCategoryList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<CategoryModel>>() {
                    @Override
                    public void accept(@NonNull Result<CategoryModel> listResult) throws Exception {

                        if (listResult.response().body().getMessage() != null) {
                            Log.d("RESPONSE", new Gson().toJson(listResult.response().body()));
                            ArrayList<String> categoryTypeList = new ArrayList<>();

                            categoryTypeList = (ArrayList<String>) listResult.response().body().getMessage();

                            Log.d("categoryTypeList", categoryTypeList.toString());
                            String categoryType = null;
                            for (int i = 0; i < categoryTypeList.size(); i++) {
                                categoryType = categoryTypeList.get(i);
                            }
                            mView.updateData(listResult.response().body());
                        } else {
                            Log.d("ERROR", "ERROR");

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError();

//                        hideProgress();

                    }
                }));
    }

    @Override
    public void LoadImages() {

        mCompositeDisposable.add(mInteractor.getCategoryImagesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<CategoryModel>>() {
                    @Override
                    public void accept(@NonNull Result<CategoryModel> listResult) throws Exception {

                        if (listResult.response().body() != null) {
//                             listResult.response().body();
                            Log.d("RESPONSE_IMAGE", new Gson().toJson(listResult.response().body().getMessage()));
                            mView.updateData(listResult.response().body());

                        } else {
                            Log.d("Imag_null", "ERROR");

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError();
//                        setLoading(false);
//                        hideProgress();

                    }
                }));

    }

    private void disposeRequest() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

}
