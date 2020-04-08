package com.aite.a.activity.li.activity.choicelanguage;

import android.os.Bundle;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.ActivityChoiceLanguageBinding;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * @author: liziyang
 * @Date: 2020/01/07
 * @description:
 */
public class ChoiceLanguageActivity extends BaseActivity<ActivityChoiceLanguageBinding, ChoiceLanguageViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_choice_language;
    }

    @Override
    public int initVariableId() {
        return com.jiananshop.a.BR.viewModel;
    }

    @Override
    public void initData() {
        binding.tvTitle.setText(getString(R.string.changeLauguage));
        binding.tvChinese.setText(getString(R.string.Chinese));
        binding.tvEnglish.setText(getString(R.string.English));
        binding.tvThailand.setText(getString(R.string.Yasufumi));
        binding.ivReturn.setOnClickListener(viewModel.onBackOnClick);
        binding.radioChinese.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (binding.radioEnglish.isChecked()) {
                    binding.radioEnglish.setChecked(false);

                }
                if (binding.radioThailand.isChecked()) {
                    binding.radioThailand.setChecked(false);
                }

            }
        });
        binding.radioEnglish.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                if (binding.radioChinese.isChecked()) {
                    binding.radioChinese.setChecked(false);
                }
                if (binding.radioThailand.isChecked()) {
                    binding.radioThailand.setChecked(false);
                }
            }
        }));
        binding.radioThailand.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked) {
                if (binding.radioChinese.isChecked()) {
                    binding.radioChinese.setChecked(false);
                }
                if (binding.radioEnglish.isChecked()) {
                    binding.radioEnglish.setChecked(false);
                }
            }
        }));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
