package com.brandonhogan.weathertracker.ui.contracts;

import com.brandonhogan.weathertracker.model.DarkSkyResponse;
import com.brandonhogan.weathertracker.ui.contracts.base.PresenterBaseContract;

/**
 * Created by Brandon on 4/10/2017.
 * Description :
 */

public interface HomeControllerContract {
    interface View {
        void onLoad(DarkSkyResponse response);
    }

    interface Presenter extends PresenterBaseContract {
        void setView(HomeControllerContract.View view);
        void onRefresh();
    }
}
