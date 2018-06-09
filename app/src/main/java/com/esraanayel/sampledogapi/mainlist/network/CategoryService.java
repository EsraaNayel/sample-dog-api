package com.esraanayel.sampledogapi.mainlist.network;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryService {
    @GET("list")
    Single<Result<CategoryModel>> getAllCategory();

    @GET("image/random/20")
    Single<Result<CategoryModel>> getAllCategoryImages();

}
