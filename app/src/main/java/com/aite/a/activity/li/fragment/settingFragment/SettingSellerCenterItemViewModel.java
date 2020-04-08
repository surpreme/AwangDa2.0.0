package com.aite.a.activity.li.fragment.settingFragment;

import android.content.Intent;
import androidx.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.widget.Toast;

import com.aite.a.activity.AddressManageActivity;
import com.aite.a.activity.ChangePassword;
import com.aite.a.activity.ComplaintsListActivity;
import com.aite.a.activity.CustomerServiceActtivity;
import com.aite.a.activity.DistributionCenterActivity;
import com.aite.a.activity.ExchangeRecordActivity;
import com.aite.a.activity.FavoriteListFargmentActivity;
import com.aite.a.activity.IdentityActivity;
import com.aite.a.activity.InformationActivity;
import com.aite.a.activity.IntegralInfoActivity;
import com.aite.a.activity.IntegralShopActivity;
import com.aite.a.activity.MoreActivity;
import com.aite.a.activity.MyCalendarActivity;
import com.aite.a.activity.MyStoreActivity;
import com.aite.a.activity.Myevaluation;
import com.aite.a.activity.MyfootprintActivity;
import com.aite.a.activity.OnlineTopUpActivity;
import com.aite.a.activity.PersonalInformationActivity;
import com.aite.a.activity.PhoneCertificationActivity;
import com.aite.a.activity.RedPackageActivityList;
import com.aite.a.activity.RefundActivity;
import com.aite.a.activity.SellerOrderActivity;
import com.aite.a.activity.ServicehomeActivity;
import com.aite.a.activity.StationLetterListActivity;
import com.aite.a.activity.WebActivity;
import com.aite.a.activity.li.activity.onlinerecharge.OnlineRechargeKotlinActivity;
import com.aite.a.activity.li.activity.truename.TrueNameKotlinActivity;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.base.Mark;
import com.aite.awangdalibrary.ui.activity.collectlist.CollectListKotlinActivity;
import com.aite.awangdalibrary.ui.activity.foot.FootKotlinActivity;
import com.community.activity.TabActivity;
import com.jiananshop.a.R;

import hotel.HotelHomeActivity;
import hotel.HotelOrderListActivity;
import livestream.activity.LiveStreamTabActivity;
import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-15
 * @desc:
 */
public class SettingSellerCenterItemViewModel extends ItemViewModel<SettingFragmentViewHolder> {
    private int mTitleId = 0;

    public SettingSellerCenterItemViewModel(@NonNull SettingFragmentViewHolder viewModel, int icon, int titleId) {
        super(viewModel);
        iconDrawable.set(ActivityManager.getInstance().getCurrentActivity().getResources().getDrawable(icon));
        iconTitle.set(ActivityManager.getInstance().getCurrentActivity().getString(titleId));
        mTitleId = titleId;
    }

    public ObservableField<Drawable> iconDrawable = new ObservableField();
    public ObservableField<String> iconTitle = new ObservableField();
    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (mTitleId == R.string.discover) {//发现
                Intent intent0 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , TabActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent0);
            } else if (mTitleId == R.string.buyer_orders) {//订单管理
                       /* Intent intent1 = new Intent(ActivityManager.getInstance().getCurrentActivity(), BuyerOrderFgActivity.class);
                       ActivityManager.getInstance().getCurrentActivity().startActivity(intent1);*/
            } else if (mTitleId == R.string.virtualorders) {//虚拟订单
                       /* Intent intentvr = new Intent(                ActivityManager.getInstance().getCurrentActivity(), WebActivity.class);
                        intentvr.putExtra("url", "http://aitecc.com/wap/index.php?act=member_vr_order");ActivityManager.getInstance().getCurrentActivity().startActivity(intentvr);*/
            } else if (mTitleId == R.string.gif2) {//兑换记录
                Intent intent3 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , ExchangeRecordActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent3);
            } else if (mTitleId == R.string.my_shopping_cart) {//购物车
                        /*Bundle bundle1 = new Bundle();
                        bundle1.putString("shoopping", "shoopping");
                        Intent intent4 = new Intent(ActivityManager.getInstance().getCurrentActivity(), CartActivity.class);
                        intent4.putExtras(bundle1);
                                        ActivityManager.getInstance().getCurrentActivity().startActivity(intent4);*/
            } else if (mTitleId == R.string.my_footprint) {//我的足迹
                Intent intent5 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , FootKotlinActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent5);
            } else if (mTitleId == R.string.address_manage) {//地址管理
                Intent intent6 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , AddressManageActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent6);
            } else if (mTitleId == R.string.personal_info) {//个人资料
                Bundle bundle2 = new Bundle();
                bundle2.putString("icon", Mark.State.UserIcon);
                Intent intent7 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , PersonalInformationActivity.class);
                intent7.putExtras(bundle2);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent7);
//                        Intent intent18 = new Intent(                ActivityManager.getInstance().getCurrentActivity()
//,WebActivity.class);
//                        intent18.putExtra("url", "https://aitecc.com/wap/index.php?act=member_information&op=member");
//                ActivityManager.getInstance().getCurrentActivity()
//                        .startActivity(intent18);
            } else if (mTitleId == R.string.update_password) {//修改密码
                Intent intent8 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , ChangePassword.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent8);
            } else if (mTitleId == R.string.collectionfcf2) {//收藏店铺
                Intent intent9 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , CollectListKotlinActivity.class);
                intent9.putExtra("viewPager", 2);
                intent9.putExtra("IsShowBackBtn", "false");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent9);
            } else if (mTitleId == R.string.collectionfcf) {//收藏宝贝
                Intent intent10 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , CollectListKotlinActivity.class);
                intent10.putExtra("viewPager", 1);
                intent10.putExtra("IsShowBackBtn", "false");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent10);
            } else if (mTitleId == R.string.myevaluation) {//comment
                Intent intent11 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , Myevaluation.class);
                intent11.putExtra("touxiang", Mark.State.UserIcon);
                intent11.putExtra("names", Mark.State.UserName);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent11);
            } else if (mTitleId == R.string.distribution_center5) {//分销中心
                if (Mark.State.member_rank == null
                        || Mark.State.member_rank.equals("0")) {
                    Toast.makeText(ActivityManager.getInstance().getCurrentActivity()
                            , ActivityManager.getInstance().getCurrentActivity()
                                    .getString(R.string.distribution_center11), Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent12 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                            , DistributionCenterActivity.class);
                    ActivityManager.getInstance().getCurrentActivity()
                            .startActivity(intent12);
                }
            } else if (mTitleId == R.string.integral_mall) {//积分商城
                Intent intent13 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , IntegralShopActivity.class);
                intent13.putExtra("person_in", "1");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent13);
            } else if (mTitleId == R.string.distribution_center27) {//会员认证
                Intent intent14 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , TrueNameKotlinActivity.class);
//                IdentityActivity
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent14);
            } else if (mTitleId == R.string.distribution_center25) {//账户安全
                Intent intent15 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , PhoneCertificationActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent15);
            } else if (mTitleId == R.string.stand_inside_letter) {//站内信
                Intent intent16 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , StationLetterListActivity.class);
                intent16.putExtra("type", "ordinary");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent16);
            } else if (mTitleId == R.string.integrall) {//积分
                Intent intent17 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , IntegralInfoActivity.class);
                intent17.putExtra("person_in", "1");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent17);
            } else if (mTitleId == R.string.news) {//新闻
                Intent intent18 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , WebActivity.class);
                intent18.putExtra("url", "http://aitecc.com/wap/index.php?act=news");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent18);
            } else if (mTitleId == R.string.shequ) {//社区
                Intent intent19 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , InformationActivity.class);
                intent19.putExtra("person_in", "2");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent19);
            } else if (mTitleId == R.string.weifaxian) {//微发现
                Intent intent20 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , WebActivity.class);
                intent20.putExtra("url", "http://aitecc.com/wap/index.php?act=weifaxian");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent20);
            } else if (mTitleId == R.string.find_reminder207) {//品牌
                Intent intent20 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , WebActivity.class);
                intent20.putExtra("url", "https://aitecc.com/wap/index.php?act=brand");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent20);
            } else if (mTitleId == R.string.distribution_center38) {//圈子
                Intent intent20 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , WebActivity.class);
                intent20.putExtra("url", "https://aitecc.com/wap/index.php?act=circle");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent20);
            } else if (mTitleId == R.string.discover) {//发现
                Intent intent20 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , WebActivity.class);
                intent20.putExtra("url", "https://aitecc.com/wap/index.php?act=find");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent20);
            } else if (mTitleId == R.string.sundrying) {//晒单
                Intent intent20 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , WebActivity.class);
                intent20.putExtra("url", "https://aitecc.com/wap/index.php?act=weifaxian");
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent20);
            } else if (mTitleId == R.string.hotel) {//酒店
                Intent intent21 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , HotelHomeActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent21);
            } else if (mTitleId == R.string.redpackageactivity) {//红包活动
                Intent intent22 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , RedPackageActivityList.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent22);
            } else if (mTitleId == R.string.hotel_order) {//酒店订单
                Intent intent23 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , HotelOrderListActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent23);
            } else if (mTitleId == R.string.service) {//服务
                Intent intent24 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , ServicehomeActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent24);
            } else if (mTitleId == R.string.RechargeOnline) {//在线充值
//                        Intent intent24 = new Intent(                ActivityManager.getInstance().getCurrentActivity()
//,TopUpActivity.class);
                Intent intent24 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , OnlineRechargeKotlinActivity.class);
//                        , OnlineTopUpActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent24);
            } else if (mTitleId == R.string.order_reminder163) {//直播
                Intent intent25 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , LiveStreamTabActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent25);
            } else if (mTitleId == R.string.signin) {//签到
                Intent intent26 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , MyCalendarActivity.class);
                intent26.putExtra("name", Mark.State.UserName);
                intent26.putExtra("icoon", Mark.State.UserIcon);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent26);
            } else if (mTitleId == R.string.integral_prompt19) {//平台客服
                Intent intent27 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , CustomerServiceActtivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent27);
            } else if (mTitleId == R.string.tradecomplaint) {//交易投诉
                Intent intent28 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , ComplaintsListActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent28);
            } else if (mTitleId == R.string.distribution_center24) {//退款退货
                Intent intent29 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , RefundActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent29);
            } else if (mTitleId == R.string.storehome29) {//我的店铺
                Intent intent30 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , MyStoreActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent30);
            } else if (mTitleId == R.string.merchant_order) {//商家订单
                Intent intent31 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , SellerOrderActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent31);
            } else if (mTitleId == R.string.set_up) {//设置
                Intent intent32 = new Intent(ActivityManager.getInstance().getCurrentActivity()
                        , MoreActivity.class);
                ActivityManager.getInstance().getCurrentActivity()
                        .startActivity(intent32);
            }


        }
    });
}
