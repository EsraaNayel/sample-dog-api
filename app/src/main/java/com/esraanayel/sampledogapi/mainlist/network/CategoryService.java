package com.esraanayel.sampledogapi.mainlist.network;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;

import io.reactivex.Single;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryService {
    @GET("list")
    Single<Result<CategoryModel>> getAllCategory();
}
