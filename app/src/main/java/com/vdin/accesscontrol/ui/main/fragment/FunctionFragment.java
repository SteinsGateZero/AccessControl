package com.vdin.accesscontrol.ui.main.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.adapter.FunctionPagerAdapter;
import com.vdin.accesscontrol.base.netbase.NetBaseFragment;
import com.vdin.accesscontrol.contract.FunctionFragmentContract;
import com.vdin.accesscontrol.contract.IBaseSelectFrag;
import com.vdin.accesscontrol.model.FunctionFragmentModel;
import com.vdin.accesscontrol.presenter.FunctionFragmentPresenter;
import com.vdin.accesscontrol.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by new1 on 2018/10/24.
 * 功能fragment
 */

public class FunctionFragment extends NetBaseFragment<FunctionFragmentModel, FunctionFragmentContract.IFunctionFragmentView, FunctionFragmentPresenter> implements FunctionFragmentContract.IFunctionFragmentView, View.OnClickListener {

    @BindView(R.id.frag_function_viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private List<NetBaseFragment> list;
    private FunctionPagerAdapter adapter;
    private MainPresenter mainPresenter;
    private int currentPage = -1;
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            doSwitch(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {
        }
    };

    public final FunctionFragment setMainPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_function, container, false);
        unbinder = ButterKnife.bind(this, view);
        initPage();
        initTitle();
        return view;
    }

    /**
     * 初始化viewpager
     */
    private void initPage() {
        list = new ArrayList<>();
        list.add(new DoorFragment());//添加开门列表fragment
        list.add(new AttendanceFragment());//添加考勤列表fragment
        adapter = new FunctionPagerAdapter(getFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(pageChangeListener);
    }

    /**
     * 设置titleBar与相应点击事件
     */
    private void initTitle() {
        if (mainPresenter == null) {
            return;
        }
        mainPresenter.getMainView().setTitle1(getString(R.string.main_title_function_1)).setTitle1Listener(this);
        mainPresenter.getMainView().setTitle2(getString(R.string.main_title_function_2)).setTitle2Listener(this).setTitleRightImg(R.mipmap.icon_screening, this);
        doSwitch(viewPager.getCurrentItem());
    }

    /**
     * @param toPage 要切换到的页
     * @return 切换到的页是否和当前页不同
     */
    private boolean doSwitch(int toPage) {
        //判断当前页是否相同，如果相同不做处理
        if (currentPage == toPage) {
            return false;
        }
        currentPage = toPage;
        if (currentPage == 0) {
            mainPresenter.getMainView().setTitle1Color(R.color.main_title_color).setTitle2Color(R.color.main_title_color_unSelect).setTitle1Img(R.mipmap.di).setTitle2Img(0);
        } else {
            mainPresenter.getMainView().setTitle2Color(R.color.main_title_color).setTitle1Color(R.color.main_title_color_unSelect).setTitle2Img(R.mipmap.di).setTitle1Img(0);
        }
        //子fragment的选中事件
        ((IBaseSelectFrag) list.get(currentPage)).selected();
        return true;
    }

    @Override
    protected FunctionFragmentPresenter createPresenter() {
        return new FunctionFragmentPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        list.clear();
        viewPager = null;
        adapter = null;
        mainPresenter.getMainView().setTitle2Listener(null).setTitle1Listener(null).setTitleRightImg(0, null);
    }

    /**
     * 被首页导航栏选中相应模块时回调
     */
    @Override
    public void selected() {
        currentPage = -1;//导航栏选中，重置currentPage，刷新title视图
        initTitle();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_title_1:
                //避免重复点击
                if (!doSwitch(0)) return;
                viewPager.setCurrentItem(0);
                // TODO: 2018/10/30 开门列表请求加载
                break;
            case R.id.main_title_2:
                //避免重复点击
                if (!doSwitch(1)) return;
                viewPager.setCurrentItem(1);
                // TODO: 2018/10/30 考勤列表请求加载
                break;
            case R.id.main_title_img:
                // TODO: 2018/10/30 筛选操作
                break;
        }
    }
}
