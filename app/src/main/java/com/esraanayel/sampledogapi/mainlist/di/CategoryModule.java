package com.esraanayel.sampledogapi.mainlist.di;

import android.content.Context;

import com.esraanayel.sampledogapi.mainlist.interactor.CategoryListingInteractor;
import com.esraanayel.sampledogapi.mainlist.interactor.CategoryListingInteractorImpl;
import com.esraanayel.sampledogapi.mainlist.presenter.CategoryListingPresenter;
import com.esraanayel.sampledogapi.mainlist.presenter.CategoryListingPresenterImpl;
import com.esraanayel.sampledogapi.mainlist.view.CategoryListingView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Esraa on 6/6/2018.
 */
@Module
public class CategoryModule {
    private CategoryListingView mView;
    private String mBaseUrl;
    private int mLimit;
    private Context mContext;

    public CategoryModule(CategoryListingView mView, String mBaseUrl, Context mContext) {
        this.mView = mView;
        this.mBaseUrl = mBaseUrl;
        this.mLimit = mLimit;
        this.mContext = mContext;
    }

    @Singleton
    @Provides
    public Context providesContext() {
        return this.mContext;
    }


    // provides news listing view
    @Singleton
    @Provides
    public CategoryListingView providesEventsListingView() {
        return this.mView;
    }



    // provides gson converter factory for retrofit
    @Singleton
    @Provides
    public Converter.Factory providesConverterFactory() {
        return GsonConverterFactory.create();
    }

    // provides Rx call adapter factory for retrofit
    @Singleton
    @Provides
    public CallAdapter.Factory providesCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    // provides retrofit for events listing interactor
    @Singleton
    @Provides
    public Retrofit providesRetrofit(Converter.Factory converter, CallAdapter.Factory adapter) {
        return new Retrofit.Builder().baseUrl(mBaseUrl)
                .addConverterFactory(converter)
                .addCallAdapterFactory(adapter)
                .build();
    }


    // provides events listing interactor
    @Singleton
    @Provides
    public CategoryListingInteractor providesEventsInteractor(Retrofit retrofit) {
        return new CategoryListingInteractorImpl(retrofit);
    }

    // provides events listing presenter
    @Singleton
    @Provides
    public CategoryListingPresenter providesEventsListingPresenter(CategoryListingView view,
                                                                   CategoryListingInteractor interactor, Context context) {
        return new CategoryListingPresenterImpl(view, interactor, context);
    }

}
