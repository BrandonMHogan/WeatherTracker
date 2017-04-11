package com.brandonhogan.weathertracker.ui.contracts;

import android.os.Bundle;

import com.brandonhogan.weathertracker.model.DarkSkyResponse;
import com.brandonhogan.weathertracker.ui.contracts.base.PresenterBaseContract;

public interface HomeControllerContract {
    interface View {
        void onLoad(DarkSkyResponse response);
        void onLoadFail();
        void onLocationFound();
    }

    interface Presenter extends PresenterBaseContract {
        void setView(HomeControllerContract.View view);
        void onRefresh();
        Bundle getState();
        String getStateKey();
        void setState(Bundle state);
    }
}
