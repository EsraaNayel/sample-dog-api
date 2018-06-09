package com.esraanayel.sampledogapi.mainlist.interactor;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.mainlist.network.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Esraa on 6/6/2018.
 */

public class CategoryListingInteractorImpl implements CategoryListingInteractor {
    Retrofit mRetrofit;

    public CategoryListingInteractorImpl(Retrofit mRetrofit) {
        this.mRetrofit = mRetrofit;
    }

    @Override
    public Single<Result<CategoryModel>> getCategoryList(String query, String categoryName) {

        Map<String, String> map = new HashMap<>();
        if (query != null && query != "") {
            map.put(QUERY_KEY, query);
        }
        if (categoryName != null && categoryName != "") {
            map.put(CATEGORY_NAME_KEY, categoryName);
        }
        return mRetrofit.create(CategoryService.class).getAllCategory(map);
    }

    @Override
    public Single<Result<CategoryModel>> getCategoryImagesList(String categoryImage) {

        return mRetrofit.create(CategoryService.class).getAllCategoryImages(categoryImage);
    }

    @Override
    public boolean saveCategory(List<CategoryModel> items) {
        return false;
    }

    @Override
    public Single<List<CategoryModel>> getCategory() {
        return null;
    }

    @Override
    public Single<List<CategoryModel>> getCategoryById(String id) {
        return null;
    }
}
