package com.aite.a.activity.li.activity.choicearea;

import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class ChoiceAreaItemViewModel extends ItemViewModel<ChoiceAreaViewModel> {
    private int mTitleId = 0;

    public ChoiceAreaItemViewModel(@NonNull ChoiceAreaViewModel viewModel, int iconId, int titleId, String num) {
        super(viewModel);

    }


    public ObservableField<String> iconTitle = new ObservableField();

    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {


        }
    });
}
