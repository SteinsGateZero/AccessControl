package com.vdin.accesscontrol.contract;

import android.view.View;

public interface MainContract {

    interface IMainView {

        void tabTo(int page);

        IMainView setTitle1(String title1);

        IMainView setTitle2(String title2);

        IMainView setTitle1Listener(View.OnClickListener listener);

        IMainView setTitle2Listener(View.OnClickListener listener);

        IMainView setTitle1Color(int color);

        IMainView setTitle2Color(int color);

        IMainView setTitle1Img(int imgRes);

        IMainView setTitle2Img(int imgRes);

        IMainView setTitleRightImg(int res, View.OnClickListener listener);
    }

    interface IMainModel {

    }

    interface IMainPresenter {
        IMainView getMainView();

        void doDestroy();
    }
}
