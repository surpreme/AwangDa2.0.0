package com.aite.a.activity.li.item.mainHouseRecy;

import android.content.Intent;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import android.graphics.Paint;
import androidx.annotation.NonNull;
import com.aite.a.activity.ProductDetailsActivity;
import com.aite.a.activity.li.fragment.houseFragment.HouseUIBean;
import com.aite.a.activity.li.fragment.houseFragment.MainHouseFragentViewHolder;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.base.Mark;
import com.aite.awangdalibrary.ui.activity.productdetails.ProductDetailsKotlinActivity;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class MainHouseGoodsListRecyViewHolder extends ItemViewModel<MainHouseFragentViewHolder> {
    private HouseUIBean.GoodsBean.ItemBeanXX mGoodsBean;

    public MainHouseGoodsListRecyViewHolder(@NonNull MainHouseFragentViewHolder viewModel, HouseUIBean.GoodsBean.ItemBeanXX goodsBean) {
        super(viewModel);
        goodListIv.set(goodsBean.getGoods_image());
        goodName.set(goodsBean.getGoods_name());
        mTextFlags.set(Paint.STRIKE_THRU_TEXT_FLAG);
        goodPrice.set(String.format("￥%s", goodsBean.getGoods_price()));
        goodElderPrice.set(String.format("￥%s", goodsBean.getGoods_marketprice()));
        this.mGoodsBean = goodsBean;

    }

    public ObservableInt mTextFlags = new ObservableInt();

    public ObservableField<String> goodListIv = new ObservableField<>();
    public ObservableField<String> goodName = new ObservableField<>();
    public ObservableField<String> goodPrice = new ObservableField<>();
    public ObservableField<String> goodElderPrice = new ObservableField<>();
    public BindingCommand onLookInformation = new BindingCommand(new BindingAction() {
        @Override
        public void call() {

            Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(),
                    ProductDetailsKotlinActivity.class);  intent.putExtra("key",
                    Mark.State.UserKey);
            intent.putExtra("goods_id", mGoodsBean.getGoods_id());
            ActivityManager.getInstance().getCurrentActivity().startActivity(intent);

        }
    });
}
