package com.aite.a.activity.li.fragment;

import android.app.Activity;
import android.content.Context;

import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * @Auther: valy
 * @datetime: 2020-01-16
 * @desc: mvvm
 */
public abstract class BaseListFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<V, VM> {
    protected Context context;
    private SmartRefreshLayout smartRefreshLayout;

    protected abstract int getLayoutResId();

    protected abstract int getVMType();

    protected abstract SmartRefreshLayout getSmartRefreshLayout();

    protected abstract void onSmartData();

    protected abstract boolean isRefresh();

    protected int mCurrentPage = 1;

    protected boolean hasMore = false;

    protected abstract ObservableList getRecyDatas();

    @Override

    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getLayoutResId();
    }

    @Override
    public int initVariableId() {
        return getVMType();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        smartRefreshLayout = getSmartRefreshLayout();
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setEnableAutoLoadMore(isRefresh());
            smartRefreshLayout.setRefreshHeader(new WaterDropHeader(context));
            mCurrentPage = 1;
            smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
                mCurrentPage = 1;
                onSmartData();
                if (getRecyDatas() != null && !getRecyDatas().isEmpty()) {
                    getRecyDatas().clear();
                }
                smartRefreshLayout.finishRefresh(1000/*,false*/);//传入false表示刷新失败
            });
            smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
                if (hasMore) {
                    mCurrentPage++;
                    onSmartData();
                    smartRefreshLayout.finishLoadMore(1000/*,false*/);//传入false表示加载失败
                } else {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();

                }


            });
        }

    }

    @Override
    public void initData() {


    }

}
