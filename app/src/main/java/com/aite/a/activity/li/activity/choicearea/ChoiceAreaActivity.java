package com.aite.a.activity.li.activity.choicearea;

import com.aite.a.activity.li.mvvm.MVVMBaseActivity;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.ActivityChoiceAreaBinding;

/**
 * @Auther: valy
 * @datetime: 2020-02-19
 * @desc:
 */
public class ChoiceAreaActivity extends MVVMBaseActivity<ActivityChoiceAreaBinding, ChoiceAreaViewModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_choice_area;
    }

    @Override
    public int getViewModelType() {
        return BR.viewModel;
    }

    @Override
    public void initParam() {
        super.initParam();


    }
}
