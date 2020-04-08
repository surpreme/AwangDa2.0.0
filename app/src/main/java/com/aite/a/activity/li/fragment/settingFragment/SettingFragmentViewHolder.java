package com.aite.a.activity.li.fragment.settingFragment;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonToken;

import androidx.annotation.NonNull;

import com.aite.a.activity.BalanceTxActivity;
import com.aite.a.activity.IntegralInfoActivity;
import com.aite.a.activity.MyMoneyActivity;
import com.aite.a.activity.PersonalInformationActivity;
import com.aite.a.activity.SettingActivity;
import com.aite.a.activity.VouchersListActivity;
import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.activity.BaseData;
import com.aite.a.activity.li.activity.QrCodeActivity;
import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.bean.GuessLikeBean2;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.fragment.houseFragment.MvvMViewAdapter;
import com.aite.a.activity.li.mvp.ErrorBean;
//import com.valy.baselibrary.retrofit.RetrofitClient;

import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.base.Mark;
import com.google.gson.Gson;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RetrofitClients;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONArray;
import org.json.JSONObject;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.BR;
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
public class SettingFragmentViewHolder extends BaseViewModel<MvvMViewAdapter> {
    public SettingFragmentViewHolder(@NonNull Application application) {
        super(application);
    }

    private Boolean isFirst = false;
    public ObservableField<String> userIconUrl = new ObservableField<>();
    public ObservableField<String> userIconBackGroundUrl = new ObservableField<>();
    public ObservableField<String> userName = new ObservableField<>();


    public BindingCommand onGoSettingActivity = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(SettingActivity.class);
        }
    });
    public BindingCommand onGoQrCodeActivity = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            startActivity(QrCodeActivity.class);
        }
    });
    public BindingCommand onLookPersonMsg = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putString("icon", Mark.State.UserIcon);
            startActivity(PersonalInformationActivity.class, bundle);
        }
    });
    //余额
    public BindingCommand onLookOverAgePrice = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            // 余额
            Bundle bundle = new Bundle();
            bundle.putBoolean("isfreeze", false);
            startActivity(MyMoneyActivity.class, bundle);
        }
    });

    //冻结
    public BindingCommand onLookFreezePrice = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isfreeze", true);
            startActivity(MyMoneyActivity.class, bundle);
        }
    });
    //积分
    public BindingCommand onLookIntegral = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            startActivity(IntegralInfoActivity.class);
        }
    });
    //代金券
    public BindingCommand onLookVoucher = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(VouchersListActivity.class);
        }
    });

    //提现
    public BindingCommand onLookWithdraw = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //
            Bundle bundle = new Bundle();
            bundle.putString("predepoit", Mark.State.predepoit);
            startActivity(BalanceTxActivity.class, bundle);
        }
    });

    //订单
    public ObservableList<SettingOrderItemViewModel> orderItemList = new ObservableArrayList<>();
    public ItemBinding<SettingOrderItemViewModel> itemBinding = ItemBinding.of(new OnItemBind<SettingOrderItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, SettingOrderItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_settingfragment_order_icon);
        }
    });
    //卖家中心s
    public ObservableList<SettingSellerCenterItemViewModel> sellerCenterItemList = new ObservableArrayList<>();
    public ItemBinding<SettingSellerCenterItemViewModel> sellerCenteritemBinding = ItemBinding.of(new OnItemBind<SettingSellerCenterItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, SettingSellerCenterItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_settingfragment_seller_center_icon);
        }
    });
    //会员福利
    public ObservableList<SettingMemberBenfitsItemViewModel> memberBenefitsItemList = new ObservableArrayList<>();
    public ItemBinding<SettingMemberBenfitsItemViewModel> memberBenefitsItemBinding = ItemBinding.of(new OnItemBind<SettingMemberBenfitsItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, SettingMemberBenfitsItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_settingfragment_member_icon);
        }
    });

    //平台服务
    public ObservableList<SettingPlatformServiceItemViewModel> platformServiceItemList = new ObservableArrayList<>();
    public ItemBinding<SettingPlatformServiceItemViewModel> platformServiceItemBinding = ItemBinding.of(new OnItemBind<SettingPlatformServiceItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, SettingPlatformServiceItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_settingfragment_platform_service_icon);
        }
    });

    //平台应用
    public ObservableList<SettingPlatformApplicationItemViewModel> platformApplicationItemList = new ObservableArrayList<>();
    public ItemBinding<SettingPlatformApplicationItemViewModel> platformApplicationItemBinding = ItemBinding.of(new OnItemBind<SettingPlatformApplicationItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, SettingPlatformApplicationItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_settingfragment_platform_appliction_icon);
        }
    });

    //猜你喜欢
    public ObservableList<GuessLikeSettingItemViewModel> guessLikeItemList = new ObservableArrayList<>();
    public ItemBinding<GuessLikeSettingItemViewModel> guessLikeItemBinding = ItemBinding.of(new OnItemBind<GuessLikeSettingItemViewModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, int position, GuessLikeSettingItemViewModel item) {
            itemBinding.set(BR.viewModel, R.layout.item_guesslike_mvvm_setting_layout);
        }
    });

    @SuppressLint("CheckResult")
    public void getSettingDatas(String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetSettingData(key)
                .compose(RxScheduler.Flo_io_main())
                .filter(settingDataBeanBaseData -> {
                    if (!settingDataBeanBaseData.isSuccessed()) {
                        ToastUtils.showShort(settingDataBeanBaseData.getDatas().getError());
                        LogUtils.e(settingDataBeanBaseData.getDatas().getError());
                        dismissDialog();
                        return false;
                    } else return true;
                })
                .map(BaseData::getDatas)
                .filter(settingDataBeanBaseData -> {
                    if (settingDataBeanBaseData.getError() != null) {
                        ToastUtils.showShort(settingDataBeanBaseData.getError());
                        LogUtils.e(settingDataBeanBaseData.getError());
                        dismissDialog();
                        return false;
                    } else {
                        return true;
                    }

                }).subscribe(settingDataBean -> {
            dismissDialog();
            Messenger.getDefault().send(settingDataBean, DataConstant.TOKEN_GET_SETTING_DATA_SUCCESS);
            if (!isFirst)
                initViews(settingDataBean);
            Mark.State.UserIcon = settingDataBean.getMember_info().getAvator();
            Mark.State.predepoit = settingDataBean.getMember_info().getPredepoit();
            Mark.State.UserName = settingDataBean.getMember_info().getUser_name();
            Mark.State.member_rank = settingDataBean.getMember_info().getMember_rank();


        }, throwable -> {
            ToastUtils.showShort(throwable.getMessage());
            LogUtils.e(throwable.getMessage());
            dismissDialog();
        });
    }

    /**
     * 重新登录
     */
    @SuppressLint("CheckResult")
    private void againLogIn() {
        if (BaseConstant.LOGINAWAY == null) return;
        if (BaseConstant.USERNAME == null) return;
        if (BaseConstant.USERPASSWORD == null) return;
        if (BaseConstant.DEVICEID == null) return;
        if (BaseConstant.LOGINAWAY.equals("isMobile")) {
            RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                    .onPostLogInAccount2(BaseConstant.USERNAME, BaseConstant.USERPASSWORD, BaseConstant.AREACODE, BaseConstant.LOGINAWAY, BaseConstant.CLIENT, BaseConstant.CURRENTLANGUAGE, BaseConstant.USERTYPE, BaseConstant.DEVICEID)
                    .compose(RxScheduler.Flo_io_main())
                    .filter(logInBeanBaseData -> {
                        if (!logInBeanBaseData.isSuccessed()) {
                            LogUtils.e(logInBeanBaseData.getDatas().getError());
                            ActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(ActivityManager.getInstance().getCurrentActivity(), LogInActivity.class));
                            return false;
                        } else return true;
                    })
                    .map(BaseData::getDatas)
                    .filter(logInBean -> {
                        if (logInBean.getError() != null) {
                            ActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(ActivityManager.getInstance().getCurrentActivity(), LogInActivity.class));
                            LogUtils.e(logInBean.getError());
                            return false;
                        } else {
                            return true;
                        }

                    }).subscribe(logInBean -> {
                Mark.State.UserKey = logInBean.getKey();
                Mark.State.UserName = logInBean.getUsername();
                Mark.State.Member_id = logInBean.getConfig().getMember_id();
            }, throwable -> {
                LogUtils.e(throwable.getMessage());
            });
        } else if (BaseConstant.LOGINAWAY.equals("isAccount")) {
            RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                    .onPostLogInAccount(BaseConstant.USERNAME, BaseConstant.USERPASSWORD, BaseConstant.LOGINAWAY, BaseConstant.CLIENT, BaseConstant.CURRENTLANGUAGE, BaseConstant.USERTYPE, BaseConstant.DEVICEID)
                    .compose(RxScheduler.Flo_io_main())
                    .filter(logInBeanBaseData -> {
                        if (!logInBeanBaseData.isSuccessed()) {
                            LogUtils.e(logInBeanBaseData.getDatas().getError());
                            return false;
                        } else return true;
                    })
                    .map(BaseData::getDatas)
                    .filter(logInBean -> {
                        if (logInBean.getError() != null) {
                            LogUtils.e(logInBean.getError());
                            return false;
                        } else {
                            return true;
                        }

                    }).subscribe(logInBean -> {
                Mark.State.UserKey = logInBean.getKey();
                Mark.State.UserName = logInBean.getUsername();
                Mark.State.Member_id = logInBean.getConfig().getMember_id();
            }, throwable -> {
                LogUtils.e(throwable.getMessage());
            });
        }
    }

    @SuppressLint("CheckResult")
    public void getGuessLikeDatas(String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetGuessLike(key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("error_code");
                        String login = jsonObject.optString("login");
                        String token_expired = jsonObject.optString("token_expired");
                        if (login != null && login.equals("0") || token_expired != null && token_expired.equals("true")) {
                            PopupWindowUtil.getmInstance().showDialogPopupWindow(
                                    ActivityManager.getInstance().getCurrentActivity(), "下线通知",
                                    "您的账号在另一台手机登录了该账号，如非本人操作，则密码可能已经泄露，建议修改密码",
                                    "重新登录",
                                    "退出",
                                    v -> {
                                        PopupWindowUtil.getmInstance().dismissPopWindow();
                                        againLogIn();

                                    }
                                    , v -> {
                                        PopupWindowUtil.getmInstance().dismissPopWindow();
                                        ActivityManager.getInstance().getCurrentActivity().startActivity(new Intent(ActivityManager.getInstance().getCurrentActivity(), LogInActivity.class));
                                    }
                            );
                            return;
                        }
                        if (code.equals("0")) {
                            JSONArray datasArry = jsonObject.optJSONArray("datas");
                            for (int i = 0; i < datasArry.length(); i++) {
                                GuessLikeBean2 guessLikeBean2 = new Gson().fromJson(datasArry.get(i).toString(), GuessLikeBean2.class);
                                guessLikeItemList.add(new GuessLikeSettingItemViewModel(SettingFragmentViewHolder.this, guessLikeBean2));
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
    public void onPeopleInformation(String lang_type, String key) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onPeopleInformation(lang_type, key)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        String login = jsonObject.optString("login");
                        String token_expired = jsonObject.optString("token_expired");

                        if (code != null && code.equals("200")) {
                            JSONObject datasObject = jsonObject.optJSONObject("datas");
                            JSONObject memberInfoObject = datasObject.optJSONObject("member_info");
                            String member_name = memberInfoObject.optString("member_truename");
                            if (member_name.isEmpty() || member_name.equals("null")) {
                                member_name = "piikMall";
                            }
                            String member_avatar = memberInfoObject.optString("member_avatar");
                            userIconUrl.set(member_avatar);
                            userName.set(member_name);
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


    private void initViews(SettingDataBean settingDataBean) {
        isFirst = true;
        /**
         * 订单
         */
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu1icon1,
                R.string.distribution_center46, settingDataBean.getMember_info().getORDER_STATE_NEW()));
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu1icon2,
                R.string.storehome39, settingDataBean.getMember_info().getORDER_STATE_PAY()));
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu1icon3,
                R.string.delivered, settingDataBean.getMember_info().getORDER_STATE_SEND()));
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon4,
                R.string.my_shopping_cart, null));
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon1,
                R.string.buyer_orders, null));
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu1icon4,
                R.string.member_centre6, settingDataBean.getMember_info().getORDER_STATE_SUCCESS()));

        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.serviceorder,
                R.string.virtualorders, null));
        orderItemList.add(new SettingOrderItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.refunds,
                R.string.member_centre7, null));
        //卖家中心 退款
        // **收藏宝贝
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon14,
                R.string.collectionfcf
        ));
        //**收藏店铺
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.favoriteshop,
                R.string.collectionfcf2
        ));
        //**收货地址
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon19,
                R.string.address_manage
        ));
        //**comment
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.evaluation,
                R.string.myevaluation
        ));
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon5,
                R.string.my_footprint
        ));

        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon6,
                R.string.personal_info
        ));
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon9,
                R.string.distribution_center25
        ));
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon16,
                R.string.distribution_center27
        ));

//        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
//                SettingFragmentViewHolder.this,
//                R.drawable.me_menu2icon8,
//                R.string.distribution
//        ));
        sellerCenterItemList.add(new SettingSellerCenterItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2iconchongzhi,
                R.string.RechargeOnline
        ));
        //会员福利
        memberBenefitsItemList.add(new SettingMemberBenfitsItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.report,
                R.string.pointsStep
        ));
        //平台服务
        platformServiceItemList.add(new SettingPlatformServiceItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.comment,
                R.string.messageNotification
        ));
        platformServiceItemList.add(new SettingPlatformServiceItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.tousu,
                R.string.tradecomplaint
        ));
        platformServiceItemList.add(new SettingPlatformServiceItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.tousu,
                R.string.distribution_center28
        ));
        platformServiceItemList.add(new SettingPlatformServiceItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon17,
                R.string.integral_prompt19
        ));
        //平台应用
        platformApplicationItemList.add(new SettingPlatformApplicationItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.brand,
                R.string.Brand
        ));
        platformApplicationItemList.add(new SettingPlatformApplicationItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.snapup,
                R.string.panic_buying
        ));
        platformApplicationItemList.add(new SettingPlatformApplicationItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.spike,
                R.string.goodsdatails_reminder12
        ));
        platformApplicationItemList.add(new SettingPlatformApplicationItemViewModel(
                SettingFragmentViewHolder.this,
                R.drawable.me_menu2icon10,
                R.string.integrals
        ));

    }


}
