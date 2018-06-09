package com.esraanayel.sampledogapi.mainlist.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageButton;

import com.esraanayel.sampledogapi.mainlist.interactor.CategoryListingInteractor;
import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.network.CategoryService;
import com.esraanayel.sampledogapi.mainlist.view.CategoryListingView;
import com.esraanayel.sampledogapi.utils.ImageLoader;
import com.esraanayel.sampledogapi.utils.NetworkHelper;
import com.esraanayel.sampledogapi.utils.presenter.BasePagingPresenterImp;
import com.google.gson.Gson;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
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
    private String queryText = null;
    private String categoryName = null;
    private String categoryImages = null;
    String ImageURL;
    String URLPath;


    public CategoryListingPresenterImpl(CategoryListingView mView, CategoryListingInteractor mInteractor, Context mContext) {
//        super(mView, limit);
        this.mInteractor = mInteractor;
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void loadOfflineCategories() {

    }

    @Override
    public void loadCategoryList(@Nullable String query, @Nullable String categoryName) {
        this.queryText = query;
        this.categoryName = categoryName;

        mCompositeDisposable.add(mInteractor.getCategoryList(queryText, categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<CategoryModel>>() {
                    @Override
                    public void accept(@NonNull Result<CategoryModel> listResult) throws Exception {

                        if (listResult.response().body() != null) {
                            Log.d("RESPONSE", new Gson().toJson(listResult.response().body()));

                            ImageURL = listResult.response().body().getMessage().toString();
                            Log.d("ImageURL", ImageURL);

                                mView.updateData(listResult.response().body());

                                LoadImages(ImageURL);
                        } else {
                            Log.d("ERROR", "ERROR");

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

    @Override
    public void LoadImages(String categoryImages) {
        this.categoryImages = categoryImages;

        mCompositeDisposable.add(mInteractor.getCategoryImagesList(ImageURL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Result<CategoryModel>>() {
                    @Override
                    public void accept(@NonNull Result<CategoryModel> listResult) throws Exception {

                        if (listResult.response().body() != null) {
//                             listResult.response().body();
                            Log.d("RESPONSE_IMAGE",new Gson().toJson(listResult.response().body()));
//                            mView.updateData(listResult.response().body());

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

    @Override
    public String getUserFacultyId() {
        return null;
    }

//    @Override
//    public void destroy() {
//        disposeRequest();
//    }
//
//    @Override
//    public void loadMoreItems() {
//        if (!NetworkHelper.isNetworkAvailable(mContext)) {
//            if (getOffset() == 0 && TextUtils.isEmpty(queryText)) {
////                loadOfflineNews();
//            } else if (!TextUtils.isEmpty(queryText)) {
////                mView.showOfflineNoData();
////                mView.showEmptyScreen();
//            }
//            return;
//        }
//        final int originalOffset = getOffset();
//
//        setLoading(true);
//        disposeRequest();
////        showProgressByOffsetNumber();
//
////        mView.showProgress();
//        mCompositeDisposable.add(mInteractor.getCategoryList(queryText,categoryName)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Result<CategoryModel>>() {
//                    @Override
//                    public void accept(@NonNull Result<CategoryModel> listResult) throws Exception {
//
//                        if (listResult.response().body() != null) {
//                            Log.d("RESPONSE", listResult.response().body().toString());
//                            mView.updateData(listResult.response().body());
//
//                        } else {
//
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        mView.showError();
//                        setLoading(false);
//                        hideProgress();
//
//                    }
//                }));
//
//    }

    private void disposeRequest() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

}
