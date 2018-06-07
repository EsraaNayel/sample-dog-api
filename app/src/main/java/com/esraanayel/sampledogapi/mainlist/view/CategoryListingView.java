package com.esraanayel.sampledogapi.mainlist.view;

import com.esraanayel.sampledogapi.mainlist.model.CategoryModel;
import com.esraanayel.sampledogapi.utils.view.BasePagingView;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface CategoryListingView extends BasePagingView{

    void updateData(CategoryModel items);

}
