package com.aite.a.activity.li.fragment.locationFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.valy.baselibrary.bean.BaseConstant;
import com.aite.a.activity.li.activity.ChoiceActivity;
import com.aite.a.activity.li.adapter.PopAdapter;
import com.aite.a.activity.li.bean.AroundareaBean;
import com.aite.a.activity.li.bean.NearChoiceTypeBean;
import com.aite.a.activity.li.util.LogUtils;
import com.aite.a.activity.li.util.PopupWindowUtil;
import com.aite.a.base.Mark;
import com.aite.a.parse.NetRun;
import com.aite.a.utils.BeanConvertor;

import com.google.android.gms.maps.model.LatLng;
import com.jiananshop.a.BR;
import com.jiananshop.a.R;
import com.jiananshop.a.databinding.FragmentLocationMainBinding;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * @Auther: valy
 * @datetime: 2020-01-10
 * @desc:
 */
public class LocationFragment extends BaseFragment<FragmentLocationMainBinding, LocationFragmentViewHolder> {
    private Context context;
    private String area_name = "深圳市";

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_location_main;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
//        showDialog("正在加载中......");
        viewModel.onGetAroundShopData(Mark.State.UserKey);
        binding.SearchEdit.setOnClickListener(v -> {
            startActivity(ChoiceActivity.class);
        });
        binding.tvDistance.setOnClickListener(v -> {
            initAwayOption();
        });
        binding.tvCity.setOnClickListener(v -> {
            initNearAreaChoice();
        });
        binding.tvClassification.setOnClickListener(v -> {
            initNearTypeChoice();
        });
        applyLocationpermission();


    }

    private void initNearAreaChoice() {
        NetRun netRun = new NetRun(context);
        netRun.onNearAreaChoice(area_name, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtils.d(responseInfo.result);
                final AroundareaBean aroundareaBean = BeanConvertor.convertBean(responseInfo.result, AroundareaBean.class);
                if (aroundareaBean == null) return;
                if (aroundareaBean.getError_code() != 0) return;
                final List<String> list = new ArrayList<>();
                list.add(getString(R.string.wholecity));
                if (aroundareaBean.getDatas().getAreaList() == null) return;
                for (int i = 0; i < aroundareaBean.getDatas().getAreaList().size(); i++) {
                    list.add(aroundareaBean.getDatas().getAreaList().get(i).getArea_name());
                }
                PopupWindowUtil.getmInstance().setNearbottomPop(context, binding.tvDistance, list, new PopAdapter.OnclickInterface() {
                    @Override
                    public void getPostion(int postion) {
                        if (!viewModel.orderItemList.isEmpty()) viewModel.orderItemList.clear();
                        if (postion == 0) initReset();
                        else
                            viewModel.area_id.set(aroundareaBean.getDatas().getAreaList().get(postion - 1).getArea_id());
                        viewModel.class_id.set("");
                        viewModel.mCurrentPage = 1;
                        viewModel.onGetShopListData(Mark.State.UserKey, viewModel.points.get());
                        binding.nearbyDistrinctTv.setText(postion == 0 ? getString(R.string.wholecity) : list.get(postion));
                        PopupWindowUtil.getmInstance().dismissPopWindow();

                    }
                });

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void initNearTypeChoice() {
        NetRun netRun = new NetRun(context);
        netRun.onNearTypeChoice(new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtils.d(responseInfo.result);
                final NearChoiceTypeBean nearChoiceTypeBean = BeanConvertor.convertBean(responseInfo.result, NearChoiceTypeBean.class);
                final List<String> title = new ArrayList<>();
                title.add(getString(R.string.AllCategories));
                for (int i = 0; i < nearChoiceTypeBean.getDatas().size(); i++) {
                    title.add(nearChoiceTypeBean.getDatas().get(i).getSc_name());
                }
                PopupWindowUtil.getmInstance().setNearbottomPop(context, binding.tvDistance, title, new PopAdapter.OnclickInterface() {
                    @Override
                    public void getPostion(int postion) {
                        viewModel.sort_dis_type.set(String.valueOf(postion));
                        if (!viewModel.orderItemList.isEmpty()) viewModel.orderItemList.clear();
                        if (postion == 0) initReset();
                        else
                            viewModel.class_id.set(nearChoiceTypeBean.getDatas().get(postion - 1).getSc_id());
                        viewModel.area_id.set("");
                        viewModel.mCurrentPage = 1;
                        viewModel.onGetShopListData(Mark.State.UserKey, viewModel.points.get());
                        binding.nearbyCategoryTv.setText(postion == 0 ? getString(R.string.AllCategories) : title.get(postion));
                        PopupWindowUtil.getmInstance().dismissPopWindow();

                    }
                });

            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    private void initAwayOption() {
        final List<String> list = new ArrayList<>();
        list.add(getString(R.string.nearby));
        list.add(getString(R.string.Within500m));
        list.add("500m-1km");
        list.add("1km-2km");
        list.add("2km-5km");
        list.add(getString(R.string.Over5km));
        PopupWindowUtil.getmInstance().setNearbottomPop(context, binding.tvDistance, list, new PopAdapter.OnclickInterface() {
            @Override
            public void getPostion(int postion) {
                if (!viewModel.orderItemList.isEmpty()) viewModel.orderItemList.clear();
                if (postion == 0) initReset();
                else
                    viewModel.sort_dis_type.set(String.valueOf(postion));
                viewModel.mCurrentPage = 1;
                viewModel.onGetShopListData(Mark.State.UserKey, viewModel.points.get());
                binding.nearbyDistanceTv.setText(postion == 0 ? getString(R.string.distance) : list.get(postion));
                PopupWindowUtil.getmInstance().dismissPopWindow();

            }
        });
    }

    private void initReset() {
        viewModel.area_id.set("");
        viewModel.class_id.set("");
        viewModel.sort_dis_type.set("");
        binding.nearbyDistanceTv.setText(getString(R.string.distance));
        binding.nearbyCategoryTv.setText(getString(R.string.wholecity));
        binding.nearbyCategoryTv.setText(getString(R.string.Category));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * binding.map
     * https://developers.google.com/maps/documentation/android-sdk/location
     */
    @SuppressLint("MissingPermission")
    private void initGoogleLocation() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location myLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (myLocation == null) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            String provider = lm.getBestProvider(criteria, true);
            myLocation = lm.getLastKnownLocation(provider);
        }
        if (myLocation != null) {
            viewModel.points.set(myLocation.getLongitude() + "," + myLocation.getLatitude());
            LogUtils.d(myLocation.getLatitude() + "********" + myLocation.getLongitude());
            viewModel.refresh();
            binding.locationTvName.setText(area_name = geoAddress(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())));
//            area_name = myLocation.getCity();
        }


    }

    public String geoAddress(LatLng latLng) {
        try {
            Geocoder geocoder = new Geocoder(context);
//            Geocoder geocoder = new Geocoder(context, Locale.CHINA);
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 10);
            if (addresses != null) {
                Address address = addresses.get(0);
                //1市 2区 3详细地址+ address.getSubLocality() + address.getThoroughfare();
                String location = address.getLocality();
                LogUtils.d(location);
                return location;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";


    }

    @SuppressLint("CheckResult")
    protected void applyLocationpermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            RxPermissions rxPermissions = new RxPermissions(LocationFragment.this);
            rxPermissions.setLogging(true);
            rxPermissions
                    .requestEach(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(permission -> { // will emit 2 Permission objects
                        if (permission.granted) {
                            // `permission.name` is granted !
                            LogUtils.d("ACCESS_COARSE_LOCATION权限同意");
                            initGoogleLocation();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // Denied permission without ask never again
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("ACCESS_COARSE_LOCATION权限被拒绝");
                        } else {
                            // Denied permission with ask never again
                            // Need to go to the settings
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                            startActivityForResult(intent, BaseConstant.PERMISSION.OVERLAY_PERMISSION_REQ_CODE);
                            LogUtils.e("ACCESS_COARSE_LOCATION权限被拒绝");

                        }
                    });
        }
    }


}
