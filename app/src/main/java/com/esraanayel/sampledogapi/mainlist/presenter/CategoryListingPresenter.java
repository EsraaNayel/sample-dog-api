package com.esraanayel.sampledogapi.mainlist.presenter;

import com.esraanayel.sampledogapi.utils.presenter.BasePagingPresenter;

import io.reactivex.annotations.Nullable;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryListingPresenter {

    void loadOfflineCategories();
    void loadCategoryList(@Nullable String query, @Nullable String category);
    void LoadImages(@Nullable String categoryName);

    String getUserFacultyId();
}
