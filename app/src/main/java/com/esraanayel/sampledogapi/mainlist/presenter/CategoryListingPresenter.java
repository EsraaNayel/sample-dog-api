package com.esraanayel.sampledogapi.mainlist.presenter;

import com.esraanayel.sampledogapi.utils.presenter.BasePagingPresenter;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryListingPresenter extends BasePagingPresenter {

    void loadOfflineCategories();
    void requestMoreItemsWithQueryFilters(/*@Nullable String query, @Nullable String facultyId*/);
    String getUserFacultyId();
}
