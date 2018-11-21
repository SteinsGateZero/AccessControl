package com.vdin.accesscontrol.contract;

public interface FunctionFragmentContract {

    interface IFunctionFragmentView extends IBaseSelectFrag{
        
    }

    interface IFunctionFragmentModel {

    }

    interface IFunctionFragmentPresenter {
        /**
         * 刷新
         */
        void refreshList();

        /**
         * 加载更多
         */
        void loadMore();

        /**
         * 改变list展示内容开门或者考勤列表
         */
        void changeListType();
    }
}
