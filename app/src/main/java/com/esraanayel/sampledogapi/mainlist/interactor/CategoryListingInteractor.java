package com.esraanayel.sampledogapi.mainlist.interactor;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryListingInteractor {

    Single<Result<CategoryModel>> getCategoryList();

    boolean saveCategory(List<CategoryModel> items);
    Single<List<CategoryModel>> getCategory();
    Single<List<CategoryModel>> getCategoryById(String id);

}
