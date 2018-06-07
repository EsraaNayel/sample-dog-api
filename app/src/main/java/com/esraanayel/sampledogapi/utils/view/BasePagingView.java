package com.esraanayel.sampledogapi.utils.view;

/**
 * Created by Esraa on 6/6/2018.
 */

public interface BasePagingView {
    void showProgress();

    void showLoadingMoreProgress();

    void showError();

    void hideProgress();

    void hideLoadingMoreProgress();
}
