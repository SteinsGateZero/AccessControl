package com.vdin.accesscontrol.contract;

public interface AttendanceFragmentContract {

    interface IAttendanceFragmentView extends IBaseSelectFrag{


    }

    interface IAttendanceFragmentModel {

    }

    interface IAttendanceFragmentPresenter {
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
