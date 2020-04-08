package com.aite.a.activity.li.fragment.shoppingCartFragment;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.annotation.NonNull;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.bean.GuessLikeBean2;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.fragment.houseFragment.MvvMViewAdapter;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.bus.Messenger;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class ShoppingCartViewHolder extends BaseViewModel<MvvMViewAdapter> {
    public ShoppingCartViewHolder(@NonNull Application application) {
        super(application);
    }

    public ObservableInt llNullVisibility = new ObservableInt();
    public ObservableInt tvManagementVisibility = new ObservableInt();
    public ObservableField<String> tvTitleString = new ObservableField<>("");

    public ObservableList<GuessLikeItemViewModel> observableList = new ObservableArrayList<>();
    public ItemBinding<GuessLikeItemViewModel> guessLikeitemBinding = ItemBinding.of(new OnItemBind<GuessLikeItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, GuessLikeItemViewModel item) {
            itemBinding.set(com.jiananshop.a.BR.viewModel, R.layout.mvvm_item_guesslike_layout);
        }
    });
    public BindingCommand goshoppingOnClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Messenger.getDefault().send("JUMP_TO_MAIN", DataConstant.TOKEN_JUMPMAINFRAGMENT_BAR);
        }
    });

    @SuppressLint("CheckResult")
    public void getHouseUI(String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetGuessLike(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("error_code");
                        if (code.equals("0")) {
                            JSONArray datasArry = jsonObject.optJSONArray("datas");
                            for (int i = 0; i < datasArry.length(); i++) {
                                GuessLikeBean2 guessLikeBean2 = new Gson().fromJson(datasArry.get(i).toString(), GuessLikeBean2.class);
                                observableList.add(new GuessLikeItemViewModel(ShoppingCartViewHolder.this, guessLikeBean2));
                            }
                        } else {
                            JSONObject errorObject = jsonObject.optJSONObject("data");
                            ErrorBean errorBean = new Gson().fromJson(errorObject.toString(), ErrorBean.class);
                            if (errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                                dismissDialog();
                            }
                        }
                    }
                }, throwable -> {
                    ToastUtils.showShort(throwable.getMessage());
                    LogUtils.e(throwable.getMessage());
                    dismissDialog();
                });

    }

    @SuppressLint("CheckResult")
    public void getShoppingCard(String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetShoppingCardList(key)
                .compose(RxScheduler.Flo_io_main())
                .filter(shoppingCardBeanBaseData -> {
                    if (!shoppingCardBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(shoppingCardBeanBaseData.getDatas().getError());
                        LogUtils.e(shoppingCardBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(shoppingCardBeanBaseData -> {
                    if (shoppingCardBeanBaseData.getError() != null) {
                        ToastUtils.showShort(shoppingCardBeanBaseData.getError());
                        LogUtils.e(shoppingCardBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(shoppingCardBean -> {
            Messenger.getDefault().send(shoppingCardBean, DataConstant.TOKEN_GET_SHOPPINGCARD_SUCCESS);

        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });
    }

}



