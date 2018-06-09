package com.esraanayel.sampledogapi.mainlist.interactor;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryListingInteractor {
    public static final String QUERY_KEY = "q";
    public static final String CATEGORY_NAME_KEY = "category_name";
    public static final String CATEGORY_IMAGE_KEY = "category_image";

    Single<Result<CategoryModel>> getCategoryList(String query,String categoryName);
    Single<Result<CategoryModel>>getCategoryImagesList(String categoryName);

    boolean saveCategory(List<CategoryModel> items);
    Single<List<CategoryModel>> getCategory();
    Single<List<CategoryModel>> getCategoryById(String id);

}
