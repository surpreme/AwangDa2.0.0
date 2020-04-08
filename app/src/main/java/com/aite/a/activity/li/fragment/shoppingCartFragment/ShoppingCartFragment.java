package com.aite.a.activity.li.fragment.shoppingCartFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aite.a.activity.OrderSureActivity;
import com.aite.a.activity.li.activity.login.LogInActivity;
import com.aite.a.activity.li.data.DataConstant;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.adapter.MvvmShoppingCartAdapter;
import com.aite.a.base.Mark;
import com.aite.a.parse.NetRun;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.FragmentShoppingCartMainBinding;
import com.valy.baselibrary.adpter.BaseItemDecoration;

import java.util.Map;

import level3.util.WheelUtils;
import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.bus.Messenger;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class ShoppingCartFragment extends BaseFragment<FragmentShoppingCartMainBinding, ShoppingCartViewHolder> {
    private Context context;
    private MvvmShoppingCartAdapter shoppingCartAdapter;
    private boolean ispickall = false;//是否全选
    private NetRun netRun;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("DefaultLocale")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /**
             * 选中价格
             */
            switch (msg.what) {
                case Mark.TOTAL_PRICE:
                    if (msg.obj != null) {
                        try {
                            Map<String, Object> map = (Map<String, Object>) msg.obj;
                            float price = (float) map.get("price");
                            int num = (int) map.get("num");
                            boolean isall = (boolean) map.get("isall");
                            if (!binding.tvManagement.getText().toString().equals(getString(R.string.complete))) {
                                binding.tvSettlement.setText(String.format("%s（%d）", getString(R.string.balance), num));
                            }
                            binding.tvPrice.setText(String.format("￥%s", price));
                            binding.ivAll.setImageResource(isall ? R.drawable.check_red_2 : R.drawable.check_red_1);
                            ispickall = isall;
                        } catch (Exception e) {
                            LogUtils.e(e);
                        }


                    }
                    break;
                case Mark.getcart_list2_err:
                    Toast.makeText(context, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case Mark.up_cart_num_id://商品修改数量
                    if (msg.obj != null) {
                        Map<String, String> map = (Map<String, String>) msg.obj;
                        String error = map.get("error");
                        if (error == null) {
                            shoppingCartAdapter.setNumber(map);
                        } else {
                            Toast.makeText(context, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case Mark.up_cart_num_err:
                    Toast.makeText(context, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case Mark.drop_cart_id://删除
                    if (msg.obj.equals("1")) {
                        viewModel.getShoppingCard(Mark.State.UserKey);
                        Toast.makeText(context, getString(R.string.act_del_success), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, getString(R.string.act_del_fail), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Mark.drop_cart_err:
                    Toast.makeText(context, getString(R.string.systembusy), Toast.LENGTH_SHORT).show();
                    break;
                case Mark.SETCART_NUMBER://修改数量
                    if (msg.obj != null) {
                        Map<String, String> map = (Map<String, String>) msg.obj;
                        String goodsnumber = map.get("num");
                        String cart_id = map.get("cart_id");
                        netRun.upCartGoodsNum(cart_id, goodsnumber);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_shopping_cart_main;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!Mark.State.isUnLogin){
            initData();
        }
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @SuppressLint("DefaultLocale")
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
                            Intent intent = new Intent(getActivity(), LogInActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            getActivity().finish();

                        }
                    }
            );
            return;
        }
        initView();
        initMessenger();
        initNet();

    }


    private void initMessenger() {
        Messenger.getDefault().register(this, DataConstant.TOKEN_GET_SHOPPINGCARD_SUCCESS, ShoppingCardBean.class, shoppingCardBean -> {
            if (shoppingCardBean.getCart_list() != null) {
                if (shoppingCardBean.getCart_list().size() == 0) {
                    viewModel.llNullVisibility.set(View.VISIBLE);
                    viewModel.tvManagementVisibility.set(View.GONE);
                    viewModel.tvTitleString.set(getString(R.string.my_shopping_cart));
                } else {
                    viewModel.llNullVisibility.set(View.GONE);
                    viewModel.tvManagementVisibility.set(View.VISIBLE);
                    if (shoppingCartAdapter == null) {
                        shoppingCartAdapter = new MvvmShoppingCartAdapter(context, shoppingCardBean.getCart_list(), handler);
                        binding.lvList.setAdapter(shoppingCartAdapter);
                    } else {
                        shoppingCartAdapter.refreshData(shoppingCardBean.getCart_list());
                    }
                    int num = 0;
                    for (int i = 0; i < shoppingCardBean.getCart_list().size(); i++) {
                        num += shoppingCardBean.getCart_list().get(i).getGoods_list().size();
                    }
                    //String.format("%s（%d）", num
                    viewModel.tvTitleString.set(getString(R.string.my_shopping_cart));
                    shoppingCartAdapter.getTotalPrice();
                }

            } else {
                viewModel.llNullVisibility.set(View.VISIBLE);
                viewModel.tvManagementVisibility.set(View.GONE);
                viewModel.tvTitleString.set(getString(R.string.my_shopping_cart));
            }


        });
    }

    private void initView() {
        binding.llPickall.setOnClickListener(v -> {
            if (shoppingCartAdapter != null) {
                shoppingCartAdapter.pickAll(!ispickall);
                ispickall = !ispickall;
            }
        });
        binding.tvManagement.setOnClickListener(v -> {
            if (binding.tvManagement.getText().toString().equals(getString(R.string.find_reminder334))) {
                binding.tvManagement.setText(getString(R.string.complete));
                binding.tvGl.setVisibility(View.GONE);
                binding.tvPrice.setVisibility(View.GONE);
                binding.tvSettlement.setText(getString(R.string.delete));
            } else {
                binding.tvManagement.setText("" + getString(R.string.find_reminder334));
                binding.tvGl.setVisibility(View.VISIBLE);
                binding.tvPrice.setVisibility(View.VISIBLE);
                binding.tvSettlement.setText(getString(R.string.balance) + "（0）");
                shoppingCartAdapter.getTotalPrice();
            }
        });
        binding.tvSettlement.setOnClickListener(v -> {
            if (binding.tvManagement.getText().toString().equals(getString(R.string.complete))) {//删除
                String pickGoodsId = shoppingCartAdapter.getPickGoodsId();
                if (pickGoodsId == null) {
                    Toast.makeText(context, getString(R.string.find_reminder337), Toast.LENGTH_SHORT).show();
                    return;
                }
                netRun.delCartGoods(pickGoodsId);
            } else {//结算
                String pickGoodsId = shoppingCartAdapter.getPickGoodsId2();
                if (pickGoodsId == null) {
                    Toast.makeText(context, getString(R.string.find_reminder337), Toast.LENGTH_SHORT).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("cart_id", pickGoodsId);
                bundle.putString("ifcart", "1");
                startActivity(OrderSureActivity.class, bundle);
            }
        });
        binding.recyclerView.addItemDecoration(
                new BaseItemDecoration(
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 10),
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        WheelUtils.dip2px(context, 0), WheelUtils.dip2px(context, 0),
                        ContextCompat.getColor(context, R.color.gray), context,
                        2f, null));

    }


    private void initNet() {
        viewModel.getHouseUI(Mark.State.UserKey);
        netRun = new NetRun(context, handler);
        viewModel.getShoppingCard(Mark.State.UserKey);

    }


}
