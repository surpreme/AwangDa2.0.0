package com.aite.a.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aite.a.activity.li.Retrofitinterface.RetrofitInterface;
import com.aite.a.activity.li.mvp.ErrorBean;
import com.valy.baselibrary.retrofit.RetrofitClients;

import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.StatusBarUtils;
import com.aite.a.adapter.ChoiceAddressRecyAdapter;
import com.aite.a.base.Mark;
import com.aite.a.bean.ChoiceAddressBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiananshop.a.R;
import com.valy.baselibrary.retrofit.RxScheduler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.ResponseBody;

/**
 * @Auther: valy
 * @datetime: 2020/3/2
 * @desc:
 */
public class ChoiceAddressActivity extends AppCompatActivity {
    @BindView(R.id.choice_address_recy)
    RecyclerView choiceAddressRecy;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.choice_address_tv)
    TextView choiceAddressTv;
    private Context context;
    private Unbinder unbinder;
    private ChoiceAddressRecyAdapter choiceAddressRecyAdapter;
    private List<ChoiceAddressBean.AreaListBean> areaListBeansList = new ArrayList<>();
    private List<String> areaCode = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_address);
        unbinder = ButterKnife.bind(this);
        initEtras();
        initViews();
        initDatas();
    }

    private void initEtras() {
        context = this;
        StatusBarUtils.setColor(context, getResources().getColor(R.color.white));


    }

    private void initDatas() {
        getAreaList(Mark.State.UserKey, "85917", "zh_cn");
        areaCode.add("85917");
    }

    @SuppressLint("CheckResult")
    private void getAreaList(String key, String area_id, String lang_type) {
        RetrofitClients.getInstance().getRetrofit().create(RetrofitInterface.class)
                .onGetAreaListData(key, area_id, lang_type)
                .compose(RxScheduler.Flo_io_main())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        String code = jsonObject.optString("code");
                        JSONObject dataObject = jsonObject.optJSONObject("datas");
                        if (code != null && code.equals("200")) {
                            JSONArray area_list = dataObject.optJSONArray("area_list");
                            if (area_list != null) {
                                List<ChoiceAddressBean.AreaListBean> areaObjct = new Gson().fromJson(
                                        area_list.toString(),
                                        new TypeToken<List<ChoiceAddressBean.AreaListBean>>() {
                                        }.getType());
                                if (!areaListBeansList.isEmpty()) areaListBeansList.clear();
                                areaListBeansList.addAll(areaObjct);
                                if (areaListBeansList != null && !areaListBeansList.isEmpty())
                                    choiceAddressRecyAdapter.notifyDataSetChanged();
                            }


                        } else {
                            ErrorBean errorBean = new Gson().fromJson(dataObject.toString(), ErrorBean.class);
                            if (errorBean != null && errorBean.getError() != null) {
                                ToastUtils.showShort(errorBean.getError());
                                LogUtils.e(errorBean.getError());
                            }
                        }
                    }
                }, throwable -> {
                    LogUtils.e(throwable.getMessage());
                });
    }

    private void initViews() {
        choiceAddressRecyAdapter = new ChoiceAddressRecyAdapter(context, areaListBeansList);
        choiceAddressTv.setText("柬埔寨");
        choiceAddressRecyAdapter.setOnThingClickInterface(ttt -> {
            if (!areaListBeansList.isEmpty()) {
                areaCode.add(areaListBeansList.get((Integer) ttt).getArea_id());
                getAreaList(Mark.State.UserKey, areaListBeansList.get((Integer) ttt).getArea_id(), "zh_cn");
                choiceAddressTv.setText(String.format("%s|%s", choiceAddressTv.getText().toString(), areaListBeansList.get((Integer) ttt).getArea_name()));
            } else {
                Intent intent = getIntent();
                intent.putExtra("country_id", "85917");
                intent.putExtra("province_id", areaCode.size() >= 2 ? areaCode.get(1) : "0");
                intent.putExtra("city_id", areaCode.size() >= 3 ? areaCode.get(2) : "0");
                intent.putExtra("area_id", areaCode.size() >= 4 ? areaCode.get(3) : "0");
                intent.putExtra("choice_end_name", choiceAddressTv.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }

        });
        choiceAddressRecy.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        choiceAddressRecy.setAdapter(choiceAddressRecyAdapter);
        tvTitle.setText(R.string.chooseaddress);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
