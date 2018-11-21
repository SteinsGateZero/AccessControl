package com.vdin.accesscontrol.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.vdin.accesscontrol.R;
import com.vdin.accesscontrol.base.netbase.NetBaseActivity;
import com.vdin.accesscontrol.contract.IBaseSelectFrag;
import com.vdin.accesscontrol.contract.MainContract;
import com.vdin.accesscontrol.eventbus.LogoutEvent;
import com.vdin.accesscontrol.model.MainModel;
import com.vdin.accesscontrol.presenter.MainPresenter;
import com.vdin.accesscontrol.ui.main.fragment.BoxFragment;
import com.vdin.accesscontrol.ui.main.fragment.FunctionFragment;
import com.vdin.accesscontrol.ui.main.fragment.GroupFragment;
import com.vdin.accesscontrol.ui.main.fragment.MyFragment;
import com.vdin.accesscontrol.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by new1 on 2018/10/24.
 * 首页
 */

public class MainActivity extends NetBaseActivity<MainModel, MainContract.IMainView, MainPresenter> implements MainContract.IMainView {
    @BindView(R.id.main_nvBar)
    BottomNavigationBar nvBar;
    @BindView(R.id.main_title_1)
    TextView title1;
    @BindView(R.id.main_title_1_img)
    ImageView title1Img;
    @BindView(R.id.main_title_2)
    TextView title2;
    @BindView(R.id.main_title_2_img)
    ImageView title2Img;
    @BindView(R.id.main_title_img)
    ImageView titleRightImg;

    private Thread clickThread;
    private int clickTwice = 0;
    private int currentPosition = 0;
    private List<Fragment> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    private void init() {
        EventBus.getDefault().register(this);
        initNavigationBar();
        initFragment();
    }

    private void initFragment() {
        list = new ArrayList<>();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        //添加显示第一个fragment
        Fragment fragment = new FunctionFragment().setMainPresenter(mPresenter);
        list.add(fragment);
        fm.add(R.id.main_container, fragment);

        //添加并隐藏fragment
        fragment = new GroupFragment().setMainPresenter(mPresenter);
        list.add(fragment);
        fm.add(R.id.main_container, fragment);
        fm.hide(fragment);

        fragment = new BoxFragment().setMainPresenter(mPresenter);
        list.add(fragment);
        fm.add(R.id.main_container, fragment);
        fm.hide(fragment);

        fragment = new MyFragment().setMainPresenter(mPresenter);
        list.add(fragment);
        fm.add(R.id.main_container, fragment);
        fm.hide(fragment);

        fm.commitAllowingStateLoss();

    }

    /**
     * 初始化底部导航栏
     */
    private void initNavigationBar() {
        //1.设置底部栏模式
        nvBar.setMode(BottomNavigationBar.MODE_FIXED);
        //2.设置BackgroundStyle
        nvBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //3.设置背景色
        nvBar.setBarBackgroundColor(R.color.colorPrimary);
        //4.设置每个Item并初始化
        addNavigationBar();
        //默认选中第一个
        nvBar.setFirstSelectedPosition(0);
        //隐藏toolbar
        //hideToolBar();
        //添加监听
        nvBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                pageTo(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        nvBar.initialise();
    }

    /**
     * 添加底部导航栏item
     */
    private void addNavigationBar() {
        nvBar.addItem(new BottomNavigationItem(R.mipmap.tab_function_selected, R.string.main_nv_work).setInactiveIconResource(R.mipmap.tab_functon_default).setInActiveColorResource(R.color.main_nv_color).setActiveColorResource(R.color.colorAccent));
        nvBar.addItem(new BottomNavigationItem(R.mipmap.tab_group_selected, R.string.main_nv_group).setInactiveIconResource(R.mipmap.tab_group_default).setInActiveColorResource(R.color.main_nv_color).setActiveColorResource(R.color.colorAccent));
        nvBar.addItem(new BottomNavigationItem(R.mipmap.tab_box_selected, R.string.main_nv_box).setInactiveIconResource(R.mipmap.tab_box_default).setInActiveColorResource(R.color.main_nv_color).setActiveColorResource(R.color.colorAccent));
        nvBar.addItem(new BottomNavigationItem(R.mipmap.tab_my_selected, R.string.main_nv_my).setInactiveIconResource(R.mipmap.tab_my_default).setInActiveColorResource(R.color.main_nv_color).setActiveColorResource(R.color.colorAccent));
    }

    /**
     * @param page 切换到的fragment页
     */
    private void pageTo(int page) {
        if (currentPosition != page) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(list.get(currentPosition));
            currentPosition = page;
            Fragment fragment = list.get(page);
            transaction.show(fragment);
            ((IBaseSelectFrag) fragment).selected();//更新title
            transaction.commitNowAllowingStateLoss();
        }
    }

    /**
     * @param page 导航栏选择页面
     */
    @Override
    public void tabTo(int page) {
        nvBar.selectTab(page);
    }

    /**
     * @param title1 设置title
     * @return 视图接口
     */
    @Override
    public MainContract.IMainView setTitle1(String title1) {
        if (TextUtils.isEmpty(title1)) {
            this.title1.setVisibility(View.GONE);
        } else {
            this.title1.setText(title1);
            this.title1.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * @param title2 设置title2，如果为空则隐藏(默认是隐藏的)
     * @return 视图接口
     */
    @Override
    public MainContract.IMainView setTitle2(String title2) {
        if (!TextUtils.isEmpty(title2)) {
            this.title2.setVisibility(View.VISIBLE);
        } else {
            this.title2.setVisibility(View.GONE);
            title1.setOnClickListener(null);
        }
        this.title2.setText(title2);
        return this;
    }

    /**
     * @param listener title点击事件
     * @return 视图接口
     */
    @Override
    public MainContract.IMainView setTitle1Listener(View.OnClickListener listener) {
        title1.setOnClickListener(listener);
        return this;
    }

    @Override
    public MainContract.IMainView setTitle2Listener(View.OnClickListener listener) {
        title2.setOnClickListener(listener);
        return this;
    }

    @Override
    public MainContract.IMainView setTitle1Color(int color) {
        title1.setTextColor(getResources().getColor(color));
        return this;
    }

    @Override
    public MainContract.IMainView setTitle2Color(int color) {
        title2.setTextColor(getResources().getColor(color));
        return this;
    }

    @Override
    public MainContract.IMainView setTitle1Img(int imgRes) {
        if (imgRes == 0) {
            title1Img.setVisibility(View.GONE);
            return this;
        }
        title1Img.setImageResource(imgRes);
        title1Img.setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public MainContract.IMainView setTitle2Img(int imgRes) {
        if (imgRes == 0) {
            title2Img.setVisibility(View.GONE);
            return this;
        }
        title2Img.setImageResource(imgRes);
        title2Img.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * @param res 设置titleBar靠右图片,如果为0则隐藏(默认是隐藏的)
     * @return 视图接口
     */
    @Override
    public MainContract.IMainView setTitleRightImg(int res, View.OnClickListener listener) {
        if (res != 0) {
            titleRightImg.setVisibility(View.VISIBLE);
            titleRightImg.setImageResource(res);
            titleRightImg.setOnClickListener(listener);
        } else {
            titleRightImg.setVisibility(View.GONE);
            titleRightImg.setOnClickListener(null);
        }
        return this;
    }

    @Override
    public void onBackPressed() {
        clickTwice++;
        if (clickTwice == 1) {
            ToastUtils.showToast("再按一次退出");
            clickThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        finish();
                    }
                    clickTwice--;
                }
            });
            clickThread.start();
            return;
        }
        clickThread.interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenter = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void logout(LogoutEvent event) {
        finish();
    }
}
