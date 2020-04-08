package com.aite.a.activity.li.fragment.shoppingCartFragment;

import android.content.Intent;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import android.view.View;

import com.aite.a.activity.ProductDetailsActivity;
import com.aite.a.activity.li.bean.GuessLikeBean2;
import com.aite.a.activity.li.util.ActivityManager;
import com.aite.a.base.Mark;
import com.aite.awangdalibrary.ui.activity.productdetails.ProductDetailsKotlinActivity;

import me.goldze.mvvmhabit.base.ItemViewModel;

/**
 * @Auther: valy
 * @datetime: 2020-01-13
 * @desc:
 */
public class GuessLikeItemViewModel extends ItemViewModel<ShoppingCartViewHolder> {
    private String good_id;

    public GuessLikeItemViewModel(@NonNull ShoppingCartViewHolder viewModel, GuessLikeBean2 datasBean) {
        super(viewModel);
        iconUrl.set(datasBean.getGoods_image());
        guseeLikeName.set(datasBean.getGoods_name());
        guseeLikePrice.set(String.format("$%s", datasBean.getGoods_price()));
        this.good_id = datasBean.getGoods_id();

    }

    public ObservableField<String> iconUrl = new ObservableField<>("");
    public ObservableField<String> guseeLikeName = new ObservableField<>("");
    public ObservableField<String> guseeLikePrice = new ObservableField<>("");
    public View.OnClickListener LookInforamationOnClick = view -> {
        Intent intent = new Intent(ActivityManager.getInstance().getCurrentActivity(),
                ProductDetailsKotlinActivity.class);
        intent.putExtra("goods_id", good_id);  intent.putExtra("key",
                Mark.State.UserKey);
        ActivityManager.getInstance().getCurrentActivity().startActivity(intent);
    };


}
