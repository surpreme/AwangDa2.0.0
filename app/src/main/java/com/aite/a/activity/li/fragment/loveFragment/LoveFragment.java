package com.aite.a.activity.li.fragment.loveFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.base.Mark;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aite.a.fargment.FavoriteFargment;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.FragmentLoveMainBinding;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class LoveFragment extends BaseFragment<FragmentLoveMainBinding, LoveFramgentViewHolder> {
    private Context context;
//    private View[] views;

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_love_main;
    }

    @Override
    public void initParam() {
        context = this.getActivity();
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        if (Mark.State.UserKey == null) {
            PopupWindowUtil.getmInstance().showSureDialogPopupWindow(
                    getActivity(),
                    null,
                    "您还没有登录 请登录！",
                    null, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PopupWindowUtil.getmInstance().dismissPopWindow();
                            Intent intent = new Intent(getActivity(), LogInActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().finish();

                        }
                    }
            );
            return;
        }
        viewModel.tabList.add(getString(R.string.Items));
        viewModel.tabList.add(getString(R.string.Stores));
//        views = new View[2];
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        views[0] = layoutInflater.inflate(R.layout.favorite_list_fragment, null);
//        views[1] = layoutInflater.inflate(R.layout.favorite_list_fragment, null);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FavoriteFargment().newInstance(1));
        fragments.add(new FavoriteFargment().newInstance(2));
        binding.favoriteListViewpager.setAdapter(new LoveViewPagerAdapter(this.getChildFragmentManager(), fragments));
        binding.favoriteListViewpager.setOffscreenPageLimit(binding.tabLayout.getTabCount());
        binding.favoriteListViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.favoriteListViewpager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
