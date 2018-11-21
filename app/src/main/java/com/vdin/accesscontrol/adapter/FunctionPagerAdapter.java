package com.vdin.accesscontrol.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vdin.accesscontrol.base.netbase.NetBaseFragment;

import java.util.List;

/**
 * Created by new1 on 2018/10/29.
 * 首页的功能模块viewPager
 */

public class FunctionPagerAdapter extends FragmentPagerAdapter {
    private List<NetBaseFragment> list;

    public FunctionPagerAdapter(FragmentManager fm, List<NetBaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
