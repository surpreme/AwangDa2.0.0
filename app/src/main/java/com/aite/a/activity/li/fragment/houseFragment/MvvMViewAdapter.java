package com.aite.a.activity.li.fragment.houseFragment;

import android.content.Context;

import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;

import com.google.android.material.tabs.TabLayout;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import me.goldze.mvvmhabit.base.BaseModel;

/**
 * @Auther: liziyang
 * @datetime: 2020-01-11
 * @desc:
 */
public final class MvvMViewAdapter extends BaseModel {
    /**
     * 设置中划线
     * textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
     *
     * @param textView
     * @param paintType
     */
    @BindingAdapter(value = {"flags"}, requireAll = false)
    public static void setTextViewFlags(TextView textView, int paintType) {
        if (paintType != 0)
            textView.getPaint().setFlags(paintType);


    }

    /**
     * tablayout添加tab
     * user activity
     * viewModel.tabList.add("宝贝");
     * viewModel.tabList.add("收藏");
     * user vm
     * public ArrayList<String> tabList = new ArrayList<>();
     * user xml
     * <com.google.android.material.tabs.TabLayout
     * android:id="@+id/tab_layout"
     * android:layout_width="match_parent"
     * android:layout_height="wrap_content"
     * binding:tab="@{viewModel.tabList}"/>
     *
     * @param tabLayout
     * @param list
     */
    @BindingAdapter(value = {"tab"}, requireAll = false)
    public static void addTab(TabLayout tabLayout, ArrayList<String> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                tabLayout.addTab(tabLayout.newTab().setText(list.get(i)));

            }

        }


    }

    /**
     * ViewGroup.MarginLayoutParams linearParams = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
     *
     * @param linearLayout
     * @param context
     * @param linearParams
     * @param list , "context"
     */
    @BindingAdapter(value = {"addImage", "linearParams", "scaleType","bitmapsList"}, requireAll = false)
    public static void addImage(LinearLayout linearLayout, Context context, ViewGroup.MarginLayoutParams linearParams, ImageView.ScaleType scaleType, ArrayList<Bitmap> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (context!=null){
                    ImageView imageView = new ImageView(context);
                    imageView.setImageBitmap(list.get(i));
                    if (scaleType != null)
                        imageView.setScaleType(scaleType);
                    if (linearParams != null)
                        linearLayout.addView(imageView, linearParams);
                    else linearLayout.addView(imageView);
                }


            }

        }


    }

    @BindingAdapter(value = {"imageUrl", "imagePlaceholderRes", "isCircle"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes, boolean isCircle) {
        if (!TextUtils.isEmpty(url)) {
            //使用Glide框架加载图片
            if (isCircle) {
                Glide.with(imageView.getContext())
                        .load(url)
                        .apply(new RequestOptions().placeholder(placeholderRes))
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            } else {
                Glide.with(imageView.getContext())
                        .load(url)
                        .apply(new RequestOptions().placeholder(placeholderRes))
                        .into(imageView);
            }

        }
    }
}
