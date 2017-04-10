package com.brandonhogan.weathertracker.ui.contracts;

import com.brandonhogan.weathertracker.ui.contracts.base.PresenterBaseContract;

public interface MainActivityContract {

    interface View {
    }

    interface Presenter extends PresenterBaseContract {
        void setView(MainActivityContract.View view);
    }
}
