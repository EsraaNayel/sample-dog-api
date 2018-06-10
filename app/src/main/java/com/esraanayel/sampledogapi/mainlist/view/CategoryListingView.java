package com.esraanayel.sampledogapi.mainlist.view;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.utils.view.BasePagingView;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryListingView {

    void updateData(CategoryModel items);

    //    void updateImages(CategoryModel items);
    void showProgress();

    void showError();

    void hideProgress();

}
