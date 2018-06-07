package com.esraanayel.sampledogapi.mainlist.di;

import com.esraanayel.sampledogapi.mainlist.MainCategoryFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Esraa on 6/6/2018.
 */
@Singleton
@Component(modules = CategoryModule.class)
public interface CategoryListingComponent {
    void inject(MainCategoryFragment target);

}
