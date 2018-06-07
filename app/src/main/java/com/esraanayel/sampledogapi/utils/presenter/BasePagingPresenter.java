package com.esraanayel.sampledogapi.utils.presenter;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface BasePagingPresenter {
    void requestMoreItems();

    void onDestroy();

    void resetCurrentPagingValues();
}
