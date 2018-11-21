package com.vdin.accesscontrol.contract;

public interface DoorFragmentContract {

    interface IDoorFragmentView extends IBaseSelectFrag{


    }

    interface IDoorFragmentModel {

    }

    interface IDoorFragmentPresenter {
        /**
         * 刷新
         */
        void refreshList();

        /**
         * 加载更多
         */
        void loadMore();

    }
}
